package org.example.bookmanagement.api;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.mapper.PriceMapper;
import org.example.bookmanagement.service.PriceService;
import org.example.bookmanagement.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PricesApiDelegateImpl implements PricesApiDelegate {

    private final PriceService priceService;

    @Override
    public ResponseEntity<List<Price>> pricesGet() {
        List<PriceEntity> prices = priceService.getAllPrices();
        return ResponseEntity.ok(PriceMapper.toApiPriceList(prices));
    }
}
