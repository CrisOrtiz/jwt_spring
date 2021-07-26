package org.jala.foundation.dev32.jwt.example.service;

import java.util.List;
import java.util.Optional;
import org.jala.foundation.dev32.jwt.example.entity.Product;
import org.jala.foundation.dev32.jwt.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  @Autowired
  ProductoRepository productoRepository;

  public List<Product> list() {
    return productoRepository.findAll();
  }

  public Optional<Product> getOne(int id) {
    return productoRepository.findById(id);
  }

  public Optional<Product> getByNombre(String nombre) {
    return productoRepository.findByName(nombre);
  }

  public void save(Product product) {
    productoRepository.save(product);
  }

  public void delete(int id) {
    productoRepository.deleteById(id);
  }

  public boolean existsById(int id) {
    return productoRepository.existsById(id);
  }

  public boolean existsByNombre(String nombre) {
    return productoRepository.existsByName(nombre);
  }
}
