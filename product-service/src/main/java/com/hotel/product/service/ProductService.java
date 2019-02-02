package com.hotel.product.service;

import com.hotel.product.dto.ProductDto;
import com.hotel.product.exception.ProductNotFoundException;
import com.hotel.product.mapper.ProductMapper;
import com.hotel.product.model.Product;
import com.hotel.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public Product createProduct(ProductDto productDto, Long hotelId) {
        Product product = productMapper.mapToProduct(productDto);
        product.setHotelId(hotelId);
        return productRepository.save(product);

    }

    public List<ProductDto> getAllProductsByHotelId(Long hotelId) {
        return productMapper.mapToProductDtoList(productRepository.getAllByHotelId(hotelId));
    }

    public ProductDto findById(Long productId, Long hotelId) {

        Product product = productRepository.findByIdAndHotelId(productId, hotelId);
        if (product == null) {
            throw new ProductNotFoundException();
        } else {
            return productMapper.mapToProductDto(product);
        }
    }
}
