package com.trybisz.isa.partner.controller.impl;

import com.trybisz.isa.partner.controller.api.PartnerController;
import com.trybisz.isa.partner.dto.CreateOrUpdatePartner;
import com.trybisz.isa.partner.dto.GetPartnerResponse;
import com.trybisz.isa.partner.dto.GetPartnersResponse;
import com.trybisz.isa.partner.entity.Partner;
import com.trybisz.isa.partner.function.PartnerToResponseFunction;
import com.trybisz.isa.partner.function.PartnersToResponseFunction;
import com.trybisz.isa.partner.function.RequestToPartnerFunction;
import com.trybisz.isa.partner.service.api.PartnerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class PartnerControllerImpl implements PartnerController {
    private final PartnerService partnerService;
    private final PartnerToResponseFunction partnerToResponseFunction;
    private final PartnersToResponseFunction partnersToResponseFunction;
    private final RequestToPartnerFunction requestToPartnerFunction;

    @Autowired
    public PartnerControllerImpl(PartnerService partnerService, PartnerToResponseFunction partnerToResponseFunction, PartnersToResponseFunction partnersToResponseFunction, RequestToPartnerFunction requestToPartnerFunction) {
        this.partnerService = partnerService;
        this.partnerToResponseFunction = partnerToResponseFunction;
        this.partnersToResponseFunction = partnersToResponseFunction;
        this.requestToPartnerFunction = requestToPartnerFunction;
    }


    @Override
    public GetPartnersResponse getAllPartners() {
        return partnersToResponseFunction.apply(partnerService.findAll());
    }

    @Override
    public GetPartnerResponse getPartnerById(UUID uuid) {
        return partnerService.findById(uuid)
                .map(partnerToResponseFunction)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetPartnerResponse putPartner(CreateOrUpdatePartner requestBody) {
        Partner partner = requestToPartnerFunction.apply(requestBody);
        partnerService.save(partner);
        return partnerToResponseFunction.apply(partner);
    }

    @Override
    public GetPartnerResponse patchPartner(UUID uuid, CreateOrUpdatePartner requestBody) {
        Partner partner = partnerService.findById(uuid)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        partner.setName(requestBody.getName());
        partner.setWebsite(requestBody.getWebsite());
        partner.setSinceYear(requestBody.getSinceYear());
        partnerService.save(partner);
        return partnerToResponseFunction.apply(partner);
    }

    @Override
    public void deletePartner(UUID uuid) {
        try {
            partnerService.delete(uuid);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
