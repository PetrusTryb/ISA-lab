package com.trybisz.isa.offer.function;

import com.trybisz.isa.offer.dto.GetOfferResponse;
import com.trybisz.isa.offer.entity.Offer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OfferToResponseFunction implements Function<Offer, GetOfferResponse> {

    @Override
    public GetOfferResponse apply(Offer offer) {
        return GetOfferResponse.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .validFrom(offer.getValidFrom())
                .validTo(offer.getValidTo())
                .partner(
                        GetOfferResponse.Partner.builder()
                                .id(offer.getPartner().getId())
                                .name(offer.getPartner().getName())
                                .build()
                )
        .build();
    }
}
