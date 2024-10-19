package com.trybisz.isa.controller.partner.dto;

import lombok.Builder;
import lombok.Value;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class GetPartnerRequest {
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
