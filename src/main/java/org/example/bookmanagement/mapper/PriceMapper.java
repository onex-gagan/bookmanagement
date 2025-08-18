package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.model.Price;
import org.example.bookmanagement.model.PriceCreateRequest;
import org.example.bookmanagement.model.PriceUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;

public class PriceMapper {

    private PriceMapper() {
        // Private constructor to prevent instantiation
    }
    public static Price toApiPrice(PriceEntity entity) {
        if (entity == null) return null;

        Price apiPrice = new Price();
        apiPrice.setPriceId(entity.getId() != null ? entity.getId().intValue() : null);
        apiPrice.setPrice(entity.getPrice() != null ? entity.getPrice().floatValue() : null);
        return apiPrice;
    }

    public static List<Price> toApiPriceList(List<PriceEntity> entities) {
        return entities.stream()
                .map(PriceMapper::toApiPrice)
                .collect(Collectors.toList());
    }

    public static PriceEntity toEntity(PriceCreateRequest request) {
        if (request == null) return null;

        return PriceEntity.builder()
                .price(request.getPrice() != null ? request.getPrice().doubleValue() : null)
                .build();
    }

    public static void updateEntityFromRequest(PriceEntity entity, PriceUpdateRequest request) {
        if (request.getPrice() != null) {
            entity.setPrice(request.getPrice().doubleValue());
        }
    }
}
