package com.trybisz.isa.service.impl;

import com.trybisz.isa.entity.Partner;
import com.trybisz.isa.repository.PartnerRepository;
import com.trybisz.isa.service.api.PartnerService;
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

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
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
    }

    @Override
    public void delete(UUID id) {
        this.partnerRepository.findById(id).ifPresentOrElse(this.partnerRepository::delete, ()->{throw new IllegalArgumentException();});
    }
}
