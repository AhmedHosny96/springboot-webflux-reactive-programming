package com.hosny.repository;

import com.hosny.dto.ProductsDto;
import com.hosny.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductsRepository extends ReactiveCrudRepository<Product , Integer> {

    @Query("SELECT p FROM products p WHERE price BETWEEN :min AND :max ")
    Flux<ProductsDto> findByPriceRange(double min , double max);
}
