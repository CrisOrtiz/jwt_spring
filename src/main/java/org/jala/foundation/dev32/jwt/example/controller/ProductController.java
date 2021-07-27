package org.jala.foundation.dev32.jwt.example.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jala.foundation.dev32.jwt.example.dto.Message;
import org.jala.foundation.dev32.jwt.example.dto.ProductDto;
import org.jala.foundation.dev32.jwt.example.entity.Product;
import org.jala.foundation.dev32.jwt.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping()
  public ResponseEntity<List<Product>> list() {
    List<Product> products = productService.list();
    return new ResponseEntity(products, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable("id") int id) {
    if (!productService.existsById(id)) {
      return new ResponseEntity(new Message("Does not exist."), HttpStatus.NOT_FOUND);
    }
    Product product = productService.getOne(id).get();
    return new ResponseEntity(product, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody ProductDto productDto) {
    if (StringUtils.isBlank(productDto.getName())) {
      return new ResponseEntity(new Message("name is required"), HttpStatus.BAD_REQUEST);
    }
    if (productDto.getPrice() == null || productDto.getPrice() < 0) {
      return new ResponseEntity(new Message("price must be bigger than 0"),
          HttpStatus.BAD_REQUEST);
    }
    if (productService.existsByNombre(productDto.getName())) {
      return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
    }
    Product product = new Product(productDto.getName(), productDto.getPrice());
    productService.save(product);
    return new ResponseEntity(new Message("producto creado"), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductDto productDto) {
    if (!productService.existsById(id)) {
      return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
    }
    if (productService.existsByNombre(productDto.getName()) && productService.getByNombre(
        productDto.getName()).get().getId() != id) {
      return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isBlank(productDto.getName())) {
      return new ResponseEntity(new Message("el nombre es obligatorio"),
          HttpStatus.BAD_REQUEST);
    }
    if (productDto.getPrice() == null || productDto.getPrice() < 0) {
      return new ResponseEntity(new Message("el precio debe ser mayor que 0"),
          HttpStatus.BAD_REQUEST);
    }

    Product product = productService.getOne(id).get();
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    productService.save(product);
    return new ResponseEntity(new Message("producto actualizado"), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") int id) {
    if (!productService.existsById(id)) {
      return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
    }
    productService.delete(id);
    return new ResponseEntity(new Message("producto eliminado"), HttpStatus.OK);
  }
}
