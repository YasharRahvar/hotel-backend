package com.hotel.purchasing.mapper;

import com.hotel.purchasing.dto.PurchaseDto;
import com.hotel.purchasing.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseDto mapToPurchaseDto(Purchase purchase);
    Purchase mapToPurchase(PurchaseDto purchaseDto);
}
