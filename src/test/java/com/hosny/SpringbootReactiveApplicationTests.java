package com.hosny;

import com.hosny.controller.ProductsController;
import com.hosny.dto.ProductsDto;
import com.hosny.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductsController.class)
@AutoConfigureWebTestClient
class SpringbootReactiveApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService service;

    @Test
    public void addProductTest() {
        // create  object
        Mono<ProductsDto> productsDtoMono = Mono.just(new ProductsDto(1, "keyboard", 1, 500));

        when(service.saveProduct(productsDtoMono)).thenReturn(productsDtoMono);

        webTestClient.post().uri("/products")
                .body(productsDtoMono, ProductsDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getAllProductsTest() {
        Flux<ProductsDto> productsDtoFlux = Flux.just(new ProductsDto(1, "keyboard", 1, 500)
                , new ProductsDto(1, "Mouse", 2, 300), new ProductsDto(1, "Headphone", 1, 500));

        when(service.getAllProducts()).thenReturn(productsDtoFlux);

        Flux<ProductsDto> responseBody = webTestClient.get().uri("/products").exchange()
                .expectStatus().isOk()
                .returnResult(ProductsDto.class)
                .getResponseBody();

        // TODO: verifying onNext() , onComplete

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductsDto(1, "keyboard", 1, 500))
                .expectNext(new ProductsDto(1, "Mouse", 2, 300))
                .expectNext(new ProductsDto(1, "Headphone", 1, 500))
                .expectComplete();
    }

    @Test
    public void getProductByIdTest(){
        Mono<ProductsDto> productsDtoMono = Mono.just(new ProductsDto(1, "keyboard", 1, 500));

        when(service.getProduct(anyInt())).thenReturn(productsDtoMono);

        Flux<ProductsDto> responseBody = webTestClient.get().uri("/products/1")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductsDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(product -> product.getName().equals("Keyboard"))
                .expectComplete();

    }

    @Test
    public void updateProductTest(){

        Mono<ProductsDto> productsDtoMono = Mono.just(new ProductsDto(1 , "sunglasses" , 3 , 1500));

        when(service.updateProduct(productsDtoMono , 1)).thenReturn(productsDtoMono);

        Flux<ProductsDto> responseBody = webTestClient.put().uri("/products/1")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductsDto.class)
                .getResponseBody();

    }

    @Test
    public void deleteProductTest(){
        Mono<ProductsDto> productsDtoMono = Mono.just(new ProductsDto(1 , "sunglasses" , 3 , 1500));

        when(service.deleteProduct(1)).thenReturn(Mono.empty());

        webTestClient.delete().uri("/products/1")
                .exchange()
                .expectStatus().isOk();

    }


}
