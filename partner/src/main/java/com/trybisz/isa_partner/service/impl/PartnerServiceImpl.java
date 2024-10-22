package com.trybisz.isa_partner.service.impl;

import com.trybisz.isa_partner.entity.Partner;
import com.trybisz.isa_partner.event.api.PartnerEventRepository;
import com.trybisz.isa_partner.repository.PartnerRepository;
import com.trybisz.isa_partner.service.api.PartnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerEventRepository partnerEventRepository;

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository, PartnerEventRepository partnerEventRepository) {
        this.partnerRepository = partnerRepository;
        this.partnerEventRepository = partnerEventRepository;
    }
    @Override
    public Optional<Partner> findById(UUID id) {
        return this.partnerRepository.findById(id);
    }

    @Override
    public List<Partner> findAll() {
        return this.partnerRepository.findAll();
    }

    @Override
    public void save(Partner partner) {
        this.partnerRepository.save(partner);
        this.partnerEventRepository.create(partner.getId());
    }

    @Override
    public void delete(UUID id) {
        this.partnerRepository.findById(id).ifPresentOrElse(this.partnerRepository::delete, ()->{throw new IllegalArgumentException();});
        this.partnerEventRepository.delete(id);
    }
}
