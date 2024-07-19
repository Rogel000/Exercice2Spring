package org.example.exercice2spring.controller;


import org.example.exercice2spring.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        return "homepage";
    }


    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "productList";
    }

    @GetMapping("/detail/{id}")
    public String productDetails(@PathVariable UUID id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productDetails";
    }

    @GetMapping("/detail/category/{category}")
    public String productsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "productList";
    }


    @GetMapping("/detail/price/{price}")
    public String productsByPrice(@PathVariable double price, Model model) {
        model.addAttribute("products", productService.getProductsByMaxPrice(price));
        return "productList";
    }


    @GetMapping("/filter")
    public String filterProducts(@RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "price", required = false) Double price, Model model) {

        model.addAttribute("products", productService.getFilteredProducts(price, category));
        return "productList";
    }
}
