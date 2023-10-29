package com.juliogomes.SaloonHairSystem.controllers;

import com.juliogomes.SaloonHairSystem.entity.product.Product;
import com.juliogomes.SaloonHairSystem.entity.product.ProductRequestDTO;
import com.juliogomes.SaloonHairSystem.entity.product.ProductResponseDTO;
import com.juliogomes.SaloonHairSystem.infra.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);

    }
}