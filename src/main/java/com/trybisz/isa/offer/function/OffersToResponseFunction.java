package com.trybisz.isa.offer.function;

import com.trybisz.isa.offer.dto.GetOffersResponse;
import com.trybisz.isa.offer.entity.Offer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class OffersToResponseFunction implements Function<List<Offer>, GetOffersResponse> {
    @Override
    public GetOffersResponse apply(List<Offer> offers) {
        return GetOffersResponse.builder()
                .offers(
                        offers.stream().map(
                                offer -> GetOffersResponse.Offer.builder()
                                        .title(offer.getTitle())
                                        .id(offer.getId())
                                        .build()
                        ).toList()
                )
                .build();
    }
}
