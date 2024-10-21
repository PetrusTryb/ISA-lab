package com.trybisz.isa.offer.function;

import com.trybisz.isa.offer.dto.CreateOrUpdateOffer;
import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToOfferFunction implements BiFunction<UUID, CreateOrUpdateOffer, Offer> {

    @Override
    public Offer apply(UUID partnerUUID,CreateOrUpdateOffer createOrUpdateOffer) {
        return Offer.builder()
                .Title(createOrUpdateOffer.getTitle())
                .Description(createOrUpdateOffer.getDescription())
                .ValidFrom(createOrUpdateOffer.getValidFrom())
                .ValidTo(createOrUpdateOffer.getValidTo())
                .partner(Partner.builder().id(partnerUUID).build())
                .build();
    }
}
