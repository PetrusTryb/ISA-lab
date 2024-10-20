package com.trybisz.isa.partner.function;

import com.trybisz.isa.partner.dto.GetPartnerResponse;
import com.trybisz.isa.partner.entity.Partner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PartnerToResponseFunction implements Function<Partner, GetPartnerResponse> {

    @Override
    public GetPartnerResponse apply(Partner partner) {
        return GetPartnerResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .website(partner.getWebsite())
                .sinceYear(partner.getSinceYear())
        .build();
    }
}
