package com.trybisz.isa_offer.controller.impl;

import com.trybisz.isa_offer.controller.api.PartnerController;
import com.trybisz.isa_offer.entity.Partner;
import com.trybisz.isa_offer.service.api.PartnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class PartnerControllerImpl implements PartnerController {
    private final PartnerService partnerService;

    public PartnerControllerImpl(PartnerService partnerService) {
        this.partnerService = partnerService;
    }
    @Override
    public void putPartner(UUID uuid) {
        partnerService.save(Partner.builder().id(uuid).build());
    }

    @Override
    public void deletePartner(UUID uuid) {
        try{
            partnerService.delete(uuid);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
