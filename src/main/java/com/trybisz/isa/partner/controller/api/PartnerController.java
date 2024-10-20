package com.trybisz.isa.partner.controller.api;

import com.trybisz.isa.partner.dto.CreateOrUpdatePartner;
import com.trybisz.isa.partner.dto.GetPartnerResponse;
import com.trybisz.isa.partner.dto.GetPartnersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface PartnerController {
    @GetMapping("/partners")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPartnersResponse getAllPartners();

    @GetMapping("/partners/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPartnerResponse getPartnerById(@PathVariable UUID uuid);

    @PutMapping("/partners")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    GetPartnerResponse putPartner(@RequestBody CreateOrUpdatePartner requestBody);

    @PatchMapping("/partners/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    GetPartnerResponse patchPartner(@PathVariable UUID uuid, @RequestBody CreateOrUpdatePartner requestBody);

    @DeleteMapping("/partners/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePartner(@PathVariable UUID uuid);
}
