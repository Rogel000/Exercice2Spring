package org.example.exercice2spring.service;

import org.example.exercice2spring.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Map<UUID, Product> products;

    public ProductService(){
        products = new HashMap<>();

        Product product1 = Product.builder()
                .id(UUID.randomUUID())
                .name("smartphone")
                .category("electronics")
                .price(1500.00)
                .build();

        Product product2 = Product.builder()
                .id(UUID.randomUUID())
                .name("laptop")
                .category("electronics")
                .price(2500.00)
                .build();

        Product product3 = Product.builder()
                .id(UUID.randomUUID())
                .name("t-shirt")
                .category("mode")
                .price(15.00)
                .build();

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
    }

    public List<Product> getProducts() {
        return  products.values().stream().toList();
    }

    public Product getProductById(UUID id) {
        return products.get(id);
    }


    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }


    public List<Product> getProductsByMaxPrice(Double maxPrice) {
        return products.values().stream()
                .filter(product -> product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return products.values().stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<Product> getFilteredProducts(Double maxPrice, String category) {
        return products.values().stream()
                .filter(product -> (maxPrice == null || product.getPrice() <= maxPrice))
                .filter(product -> (category == null || product.getCategory().equals(category)))
                .collect(Collectors.toList());
    }



}
