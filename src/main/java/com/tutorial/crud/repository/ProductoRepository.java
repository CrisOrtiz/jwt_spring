package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
