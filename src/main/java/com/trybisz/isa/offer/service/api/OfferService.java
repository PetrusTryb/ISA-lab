package com.trybisz.isa.offer.service.api;

import com.trybisz.isa.offer.entity.Offer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface OfferService {
    Optional<Offer> findById(UUID id);
    Optional<List<Offer>> findAllByPartner(UUID partnerId);
    List<Offer> findAll();
    void save(Offer offer);
    void delete(UUID id);
}
