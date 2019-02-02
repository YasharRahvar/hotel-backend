package com.hotel.purchasing.PurchaseService;

import com.hotel.purchasing.dto.ProductDto;
import com.hotel.purchasing.model.Purchase;
import com.hotel.purchasing.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {

        this.purchaseRepository = purchaseRepository;
    }

    public Purchase purchase(ProductDto productDto, Long userId) {

        Purchase purchase = new Purchase();
        purchase.setProductId(productDto.getId());
        purchase.setUserId(userId);
        return purchaseRepository.save(purchase);
    }
}
