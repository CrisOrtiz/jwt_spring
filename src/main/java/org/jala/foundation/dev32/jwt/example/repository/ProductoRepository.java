package org.jala.foundation.dev32.jwt.example.repository;

import org.jala.foundation.dev32.jwt.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
