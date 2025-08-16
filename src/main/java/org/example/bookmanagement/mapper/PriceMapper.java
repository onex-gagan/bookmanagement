package org.example.bookmanagement.mapper;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.model.Price;
import org.example.bookmanagement.model.PriceCreateRequest;
import org.example.bookmanagement.model.PriceUpdateRequest;
import org.example.bookmanagement.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PriceMapper {
    public static Price toApiPrice(PriceEntity entity) {
        Price apiPrice = new Price();

        apiPrice.setPriceId(entity.getId().intValue());
        apiPrice.setPrice(entity.getPrice().floatValue());

        // Skipping borrowedBooks for simplicity
        return apiPrice;
    }

    public static List<Price> toApiPriceList(List<PriceEntity> entities) {
        return entities.stream()
                .map(PriceMapper::toApiPrice)
                .collect(Collectors.toList());
    }

    public  static PriceEntity toEntity(PriceCreateRequest priceCreateRequest) {
        if (priceCreateRequest == null) {
            return null;
        }

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(priceCreateRequest.getPrice().doubleValue());

        // Skipping borrowedBooks for simplicity
        return priceEntity;
    }

    public static void updateEntityFromRequest(PriceEntity entity, PriceUpdateRequest request) {
        if (request.getPrice() != null) entity.setPrice(request.getPrice().doubleValue());
    }
}
