package com.trybisz.isa_offer.service.impl;

import com.trybisz.isa_offer.entity.Partner;
import com.trybisz.isa_offer.repository.PartnerRepository;
import com.trybisz.isa_offer.service.api.PartnerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Optional<Partner> findById(UUID id) {
        return partnerRepository.findById(id);
    }

    @Override
    public void save(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public void delete(UUID id) {
        partnerRepository.findById(id).ifPresentOrElse(partnerRepository::delete, ()->{throw new IllegalArgumentException();});
    }
}
