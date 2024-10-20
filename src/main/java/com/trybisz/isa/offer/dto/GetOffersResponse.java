package com.trybisz.isa.offer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class GetOffersResponse {
    List<Offer> offers;

    @Value
    @Builder
    public static class Offer {
        UUID id;
        String title;
    }
}
