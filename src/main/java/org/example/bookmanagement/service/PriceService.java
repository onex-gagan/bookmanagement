package org.example.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.exception.ResourceNotFoundException;
import org.example.bookmanagement.model.PriceUpdateRequest;
import org.example.bookmanagement.repository.PriceRepository;
import org.example.bookmanagement.mapper.PriceMapper;
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
        return priceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price not found with id: " + id));
    }

    public PriceEntity createPrice(PriceEntity priceEntity) {
        return priceRepository.save(priceEntity);
    }

    public PriceEntity updatePrice(Long id, PriceUpdateRequest request) {
        PriceEntity existingPrice = getPriceById(id);
        PriceMapper.updateEntityFromRequest(existingPrice, request);
        return priceRepository.save(existingPrice);
    }

    public void deletePrice(Long id) {
        if (!priceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Price not found with id: " + id);
        }
        priceRepository.deleteById(id);
    }
}
