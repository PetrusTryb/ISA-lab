package com.trybisz.isa.offer.service.api;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface OfferService {
    Optional<Offer> findById(UUID id);
    List<Offer> findByPartner(Partner partner);
    List<Offer> findAll();
    void save(Offer offer);
    void delete(UUID id);
}
