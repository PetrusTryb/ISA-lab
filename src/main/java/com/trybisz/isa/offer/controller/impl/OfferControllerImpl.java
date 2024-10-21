package com.trybisz.isa.offer.controller.impl;

import com.trybisz.isa.offer.controller.api.OfferController;
import com.trybisz.isa.offer.dto.CreateOrUpdateOffer;
import com.trybisz.isa.offer.dto.GetOfferResponse;
import com.trybisz.isa.offer.dto.GetOffersResponse;
import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.offer.function.OfferToResponseFunction;
import com.trybisz.isa.offer.function.OffersToResponseFunction;
import com.trybisz.isa.offer.function.RequestToOfferFunction;
import com.trybisz.isa.offer.service.api.OfferService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class OfferControllerImpl implements OfferController {
    private final OfferService offerService;
    private final OfferToResponseFunction offerToResponseFunction;
    private final OffersToResponseFunction offersToResponseFunction;
    private final RequestToOfferFunction requestToOfferFunction;

    @Autowired
    public OfferControllerImpl(OfferService offerService, OfferToResponseFunction offerToResponseFunction, OffersToResponseFunction offersToResponseFunction, RequestToOfferFunction requestToOfferFunction) {
        this.offerService = offerService;
        this.offerToResponseFunction = offerToResponseFunction;
        this.offersToResponseFunction = offersToResponseFunction;
        this.requestToOfferFunction = requestToOfferFunction;
    }

    @Override
    public GetOffersResponse getAllOffers() {
        return offersToResponseFunction.apply(offerService.findAll());
    }

    @Override
    public GetOfferResponse getOfferById(UUID uuid) {
        return offerService.findById(uuid)
                .map(offerToResponseFunction)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetOffersResponse getOffersByPartner(UUID uuid) {
        return offerService.findAllByPartner(uuid)
                .map(offersToResponseFunction)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetOfferResponse putOffer(UUID uuid, CreateOrUpdateOffer requestBody) {
        try {
            Offer offer = requestToOfferFunction.apply(uuid, requestBody);
            offerService.save(offer);
            return getOfferById(offer.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public GetOfferResponse patchOffer(UUID uuid, CreateOrUpdateOffer requestBody) {
        Offer offer = offerService.findById(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Offer modifiedOffer = requestToOfferFunction.apply(offer.getPartner().getId(), requestBody);
        modifiedOffer.setId(offer.getId());
        offerService.save(modifiedOffer);
        return getOfferById(offer.getId());
    }

    @Override
    public void deleteOffer(UUID uuid) {
        try {
            offerService.delete(uuid);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
