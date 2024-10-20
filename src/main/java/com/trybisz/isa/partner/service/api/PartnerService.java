package com.trybisz.isa.partner.service.api;

import com.trybisz.isa.partner.entity.Partner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface PartnerService {
    Optional<Partner> findById(UUID id);
    List<Partner> findAll();
    void save(Partner partner);
    void delete(UUID id);
}
