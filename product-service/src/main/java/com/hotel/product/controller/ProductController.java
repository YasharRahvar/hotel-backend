package com.hotel.product.controller;

import com.hotel.product.dto.ProductDto;
import com.hotel.product.mapper.ProductMapper;
import com.hotel.product.service.ProductService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hotel.product.security.SecurityUtil.getHotelId;

@RestController
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {

        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto, OAuth2Authentication oAuth2Authentication) {
        return productMapper.mapToProductDto(productService.createProduct(productDto, getHotelId(oAuth2Authentication)));
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProductsByHotelId(OAuth2Authentication oAuth2Authentication) {
        return productService.getAllProductsByHotelId(getHotelId(oAuth2Authentication));
    }

    @GetMapping("/product/{productId}")
    public ProductDto findByIdAndHotelId(@PathVariable Long productId, OAuth2Authentication oAuth2Authentication) {
        return productService.findById(productId, getHotelId(oAuth2Authentication));
    }
}
