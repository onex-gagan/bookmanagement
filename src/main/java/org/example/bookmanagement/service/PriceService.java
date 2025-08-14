package org.example.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public List<PriceEntity> getAllPrices() {
        return priceRepository.findAll();
    }

    public PriceEntity getPriceById(Long id) {
        return priceRepository.findById(id).orElse(null);
    }

}
