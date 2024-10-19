package com.trybisz.isa.controller.partner.dto;

import lombok.Builder;
import lombok.Value;

import java.net.URL;

@Value
@Builder
public class CreateOrUpdatePartner {
    String name;
    URL Website;
    int SinceYear;
}
