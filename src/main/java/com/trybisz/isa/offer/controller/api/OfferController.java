package com.trybisz.isa.offer.controller.api;

import com.trybisz.isa.offer.dto.CreateOrUpdateOffer;
import com.trybisz.isa.offer.dto.GetOfferResponse;
import com.trybisz.isa.offer.dto.GetOffersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface OfferController {
    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOffersResponse getAllOffers();

    @GetMapping("/offers/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOfferResponse getOfferById(@PathVariable UUID uuid);

    @GetMapping("/partners/{uuid}/offers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetOffersResponse getOffersByPartner(@PathVariable UUID uuid);

    @PutMapping("/partners/{uuid}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    GetOfferResponse putOffer(@PathVariable UUID uuid, @RequestBody CreateOrUpdateOffer requestBody);

    @PatchMapping("/offers/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    GetOfferResponse patchOffer(@PathVariable UUID uuid, @RequestBody CreateOrUpdateOffer requestBody);

    @DeleteMapping("/offers/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOffer(@PathVariable UUID uuid);
}
