package com.trybisz.isa.offer.service.impl;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.offer.repository.OfferRepository;
import com.trybisz.isa.offer.service.api.OfferService;
import com.trybisz.isa.partner.repository.PartnerRepository;
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
    private final PartnerRepository partnerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, PartnerRepository partnerRepository){
        this.offerRepository = offerRepository;
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Optional<Offer> findById(UUID id) {
        return this.offerRepository.findById(id);
    }

    @Override
    public Optional<List<Offer>> findAllByPartner(UUID partnerId) {
        return this.partnerRepository.findById(partnerId).map(offerRepository::findAllByPartner);
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
