package com.trybisz.isa.offer.service.impl;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import com.trybisz.isa.offer.repository.OfferRepository;
import com.trybisz.isa.offer.service.api.OfferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository1){
        this.offerRepository = offerRepository1;
    }

    @Override
    public Optional<Offer> findById(UUID id) {
        return this.offerRepository.findById(id);
    }

    @Override
    public List<Offer> findByPartner(Partner partner) {
        return this.offerRepository.findByPartner(partner);
    }

    @Override
    public List<Offer> findAll() {
        return this.offerRepository.findAll();
    }

    @Override
    public void save(Offer offer) {
        this.offerRepository.save(offer);
    }

    @Override
    public void delete(UUID id) {
        this.offerRepository.findById(id).ifPresentOrElse(this.offerRepository::delete, ()->{throw new IllegalArgumentException();});
    }
}
