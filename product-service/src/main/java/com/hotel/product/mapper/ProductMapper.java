package com.hotel.product.mapper;

import com.hotel.product.dto.ProductDto;
import com.hotel.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToProduct(ProductDto productDto);
    ProductDto mapToProductDto(Product product);
    List<ProductDto> mapToProductDtoList(List<Product> products);
}
