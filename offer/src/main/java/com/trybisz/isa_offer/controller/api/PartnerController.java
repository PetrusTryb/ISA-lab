package com.trybisz.isa_offer.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface PartnerController {
    @PutMapping("/partners/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putPartner(@PathVariable UUID uuid);

    @DeleteMapping("/partners/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePartner(@PathVariable UUID uuid);
}
