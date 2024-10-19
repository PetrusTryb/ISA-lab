package com.trybisz.isa.controller.offer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class GetOfferRequest {
    UUID id;
    String title;
    String description;
    Date validFrom;
    Date validTo;
    Partner partner;

    @Value
    @Builder
    public static class Partner {
        UUID id;
        String name;
    }
}
