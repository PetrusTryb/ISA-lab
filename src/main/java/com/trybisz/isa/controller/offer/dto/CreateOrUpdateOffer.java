package com.trybisz.isa.controller.offer.dto;

import lombok.*;

import java.util.Date;

@Value
@Builder
public class CreateOrUpdateOffer {
    String title;
    String description;
    Date validFrom;
    Date validTo;
}
