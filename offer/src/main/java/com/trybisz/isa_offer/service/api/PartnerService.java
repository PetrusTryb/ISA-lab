package com.trybisz.isa_offer.service.api;

import com.trybisz.isa_offer.entity.Partner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface PartnerService {
    Optional<Partner> findById(UUID id);
    void save(Partner partner);
    void delete(UUID id);
}
