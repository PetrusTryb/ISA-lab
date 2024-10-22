package com.trybisz.isa_partner.function;

import com.trybisz.isa_partner.dto.GetPartnerResponse;
import com.trybisz.isa_partner.entity.Partner;
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
