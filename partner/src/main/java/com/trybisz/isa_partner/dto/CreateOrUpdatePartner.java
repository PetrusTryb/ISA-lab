package com.trybisz.isa_partner.dto;

import lombok.Builder;
import lombok.Value;

import java.net.URL;

@Value
@Builder
public class CreateOrUpdatePartner {
    String name;
    URL website;
    int sinceYear;
}
