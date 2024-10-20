package com.trybisz.isa.partner.dto;

import lombok.Builder;
import lombok.Value;

import java.net.URL;
import java.util.UUID;

@Value
@Builder
public class GetPartnerResponse {
    UUID id;
    String name;
    URL website;
    int sinceYear;
}
