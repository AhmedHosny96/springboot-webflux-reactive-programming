package com.hosny.controller;


import com.hosny.dto.ProductsDto;
import com.hosny.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@AllArgsConstructor
@Slf4j
public class ProductsController {

    private final ProductService service;

    @GetMapping
    public Flux<ProductsDto> getProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductsDto> getProductById(@PathVariable("id") int id){
        return service.getProduct(id);
    }

    @GetMapping("/price-range")
    public Flux<ProductsDto> getProductsByPriceRange(@RequestParam("min") double min , @RequestParam("max") double max){
        return service.getProductByPriceRange(min,max);
    }

    @PostMapping
    public Mono<ProductsDto> getProductById(@RequestBody Mono<ProductsDto> productsDto){
        log.info("Controller method called {}");
        return service.saveProduct(productsDto);
    }

    @PutMapping("/{id}")
    public Mono<ProductsDto> getProductById(@RequestBody Mono<ProductsDto> productsDto , @PathVariable("id") int id){
        return service.updateProduct(productsDto , id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") int id){
        return service.deleteProduct(id);
    }

}
