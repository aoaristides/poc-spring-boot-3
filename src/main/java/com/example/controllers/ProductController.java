package com.example.controllers;

import com.example.dtos.ProductRecordDTO;
import com.example.models.ProductModel;
import com.example.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable UUID id) {
        var opProduct = productRepository.findById(id);
        if (opProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(opProduct.get());
    }

    @PostMapping
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var product = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var opProduct = productRepository.findById(id);
        if (opProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        var product = opProduct.get();
        BeanUtils.copyProperties(productRecordDTO, product);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        var opProduct = productRepository.findById(id);
        if (opProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        productRepository.delete(opProduct.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successsfully.");
    }

}
