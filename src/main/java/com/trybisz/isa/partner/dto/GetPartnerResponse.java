package com.trybisz.isa.partner.dto;

import lombok.Builder;
import lombok.Value;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class GetPartnerResponse {
    String name;
    URL Website;
    int SinceYear;
    List<Offer> offers;

    @Value
    @Builder
    public static class Offer {
        UUID id;
        String title;
    }
}
