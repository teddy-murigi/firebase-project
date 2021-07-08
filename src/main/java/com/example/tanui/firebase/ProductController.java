package com.example.tanui.firebase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProductDetails")
    public Product getProduct(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return productService.getProductDetailsByName(name);
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestBody Product product ) throws InterruptedException, ExecutionException {
        return productService.saveProductDetails(product);
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product  ) throws InterruptedException, ExecutionException {
        return productService.updateProductDetails(product);
    }

     @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam String name){
        return productService.deleteProduct(name);
    }
}