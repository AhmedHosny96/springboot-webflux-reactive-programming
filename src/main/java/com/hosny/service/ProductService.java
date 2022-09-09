package com.hosny.service;


import com.hosny.dto.ProductsDto;
import com.hosny.repository.ProductsRepository;
import com.hosny.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductsRepository repository;

    public Flux<ProductsDto> getAllProducts() {
        return repository.findAll().map(AppUtils::entityToDto).delayElements(
                Duration.ofSeconds(2)
        );
    }

    public Mono<ProductsDto> getProduct(int id) {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductsDto> getProductByPriceRange(double min, double max) {
        return repository.findByPriceRange(min, max);
    }

    public Mono<ProductsDto> saveProduct(Mono<ProductsDto> productsDto) {
        log.info("Service method called {}");

        return productsDto.map(AppUtils::dtoToEntity)
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductsDto> updateProduct(Mono<ProductsDto> productsDto, int id) {

        return repository.findById(id)
                .flatMap(product -> productsDto.map(AppUtils::dtoToEntity))
                .doOnNext(p -> p.setId(id))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(int id){
        return repository.deleteById(id);
    }

}
