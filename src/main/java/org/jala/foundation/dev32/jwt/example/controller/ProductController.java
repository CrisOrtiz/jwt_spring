package org.jala.foundation.dev32.jwt.example.controller;

import org.jala.foundation.dev32.jwt.example.dto.Message;
import org.jala.foundation.dev32.jwt.example.dto.ProductDto;
import org.jala.foundation.dev32.jwt.example.entity.Product;
import org.jala.foundation.dev32.jwt.example.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> list(){
        List<Product> products = productService.list();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("Does not exist."), HttpStatus.NOT_FOUND);
        Product product = productService.getOne(id).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        if(StringUtils.isBlank(productDto.getNombre()))
            return new ResponseEntity(new Message("name is required"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrecio()==null || productDto.getPrecio()<0 )
            return new ResponseEntity(new Message("price must be bigger than 0"), HttpStatus.BAD_REQUEST);
        if(productService.existsByNombre(productDto.getNombre()))
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Product product = new Product(productDto.getNombre(), productDto.getPrecio());
        productService.save(product);
        return new ResponseEntity(new Message("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductDto productDto){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        if(productService.existsByNombre(productDto.getNombre()) && productService.getByNombre(
            productDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productDto.getNombre()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrecio()==null || productDto.getPrecio()<0 )
            return new ResponseEntity(new Message("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Product product = productService.getOne(id).get();
        product.setNombre(productDto.getNombre());
        product.setPrecio(productDto.getPrecio());
        productService.save(product);
        return new ResponseEntity(new Message("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity(new Message("producto eliminado"), HttpStatus.OK);
    }
}
