package com.trybisz.isa.controller.partner.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class GetPartnersRequest {
    List<Partner> partners;

    @Value
    @Builder
    public static class Partner {
        UUID id;
        String name;
    }
}
