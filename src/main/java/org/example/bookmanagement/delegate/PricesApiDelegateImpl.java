package org.example.bookmanagement.delegate;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.mapper.PriceMapper;
import org.example.bookmanagement.model.PriceCreateRequest;
import org.example.bookmanagement.model.PriceUpdateRequest;
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

    @Override
    public ResponseEntity<Void> pricesPriceIdDelete(Integer priceId) {
        boolean isDeleted = priceService.deletePrice(priceId.longValue());
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Price> pricesPriceIdGet(Integer priceId) {
        PriceEntity priceEntity = priceService.getPriceById(priceId.longValue());
        if (priceEntity != null) {
            return ResponseEntity.ok(PriceMapper.toApiPrice(priceEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Price> pricesPost(PriceCreateRequest priceCreateRequest) {
        PriceEntity createdPriceEntity  = PriceMapper.toEntity(priceCreateRequest);
        PriceEntity savedPrice = priceService.createPrice(createdPriceEntity);
        if (savedPrice != null) {
            return ResponseEntity.status(201).body(PriceMapper.toApiPrice(savedPrice));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Price> pricesPriceIdPut(Integer priceId, PriceUpdateRequest priceUpdateRequest) {
        if (priceId == null || priceUpdateRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        PriceEntity existingPrice = priceService.getPriceById(priceId.longValue());
        if (existingPrice == null) {
            return ResponseEntity.notFound().build();
        }
        PriceMapper.updateEntityFromRequest(existingPrice, priceUpdateRequest);
        PriceEntity updatedPrice = priceService.updatePrice(existingPrice);
        return ResponseEntity.ok(PriceMapper.toApiPrice(updatedPrice));
    }
}
