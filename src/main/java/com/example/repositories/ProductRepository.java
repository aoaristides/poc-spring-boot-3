package com.example.repositories;

import com.example.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author aaristides
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
