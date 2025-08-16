package org.example.bookmanagement.delegate;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.mapper.PriceMapper;
import org.example.bookmanagement.model.Price;
import org.example.bookmanagement.model.PriceCreateRequest;
import org.example.bookmanagement.model.PriceUpdateRequest;
import org.example.bookmanagement.service.PriceService;
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

    @Override
    public ResponseEntity<Price> pricesPriceIdGet(Integer priceId) {
        PriceEntity priceEntity = priceService.getPriceById(priceId.longValue());
        return ResponseEntity.ok(PriceMapper.toApiPrice(priceEntity));
    }

    @Override
    public ResponseEntity<Price> pricesPost(PriceCreateRequest priceCreateRequest) {
        PriceEntity priceEntity = PriceMapper.toEntity(priceCreateRequest);
        PriceEntity savedPrice = priceService.createPrice(priceEntity);
        return ResponseEntity.status(201).body(PriceMapper.toApiPrice(savedPrice));
    }

    @Override
    public ResponseEntity<Price> pricesPriceIdPut(Integer priceId, PriceUpdateRequest priceUpdateRequest) {
        PriceEntity updatedPrice = priceService.updatePrice(priceId.longValue(), priceUpdateRequest);
        return ResponseEntity.ok(PriceMapper.toApiPrice(updatedPrice));
    }

    @Override
    public ResponseEntity<Void> pricesPriceIdDelete(Integer priceId) {
        priceService.deletePrice(priceId.longValue());
        return ResponseEntity.noContent().build();
    }
}
