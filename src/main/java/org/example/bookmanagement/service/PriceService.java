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

    public PriceEntity updatePrice(PriceEntity priceEntity){
        if (priceEntity == null || priceEntity.getId() == null) {
            return null; // or throw an exception
        }
        return priceRepository.save(priceEntity);
    }

    public boolean deletePrice(Long id) {
        if (priceRepository.existsById(id)) {
            priceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PriceEntity createPrice(PriceEntity priceEntity) {
        return priceRepository.save(priceEntity);
    }

}
