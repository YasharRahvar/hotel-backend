package com.hotel.purchasing.controller;

import com.hotel.purchasing.PurchaseService.PurchaseService;
import com.hotel.purchasing.dto.ProductDto;
import com.hotel.purchasing.mapper.PurchaseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import static com.hotel.purchasing.security.SecurityUtil.getUserId;

@RestController
public class PurchaseController {

    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {

        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/purchase")
    @ResponseStatus(HttpStatus.OK)
    public void purchase(@RequestBody ProductDto productDto, OAuth2Authentication oAuth2Authentication) {
       purchaseService.purchase(productDto, getUserId(oAuth2Authentication));
    }
}
