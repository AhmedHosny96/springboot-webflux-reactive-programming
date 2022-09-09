package com.hosny.utils;


import com.hosny.dto.ProductsDto;
import com.hosny.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

public class AppUtils {


    public static ProductsDto entityToDto(Product product){
        ProductsDto productsDto = new ProductsDto();
        BeanUtils.copyProperties(product , productsDto);
        return productsDto;
    }

    public static Product dtoToEntity(ProductsDto productsDto){
        Product product = new Product();
        BeanUtils.copyProperties(productsDto , product);
        return product;
    }


}
