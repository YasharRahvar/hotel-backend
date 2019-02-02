package com.hotel.product.repository;

import com.hotel.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByHotelId(Long hotelId);

    Product findByIdAndHotelId(Long id, Long hotelId);
}
