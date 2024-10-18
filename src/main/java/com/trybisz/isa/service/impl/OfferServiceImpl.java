package com.trybisz.isa.service.impl;

import com.trybisz.isa.entity.Offer;
import com.trybisz.isa.entity.Partner;
import com.trybisz.isa.repository.OfferRepository;
import com.trybisz.isa.service.api.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
}
