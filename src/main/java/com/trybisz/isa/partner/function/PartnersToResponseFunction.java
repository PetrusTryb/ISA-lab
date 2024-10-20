package com.trybisz.isa.partner.function;

import com.trybisz.isa.partner.dto.GetPartnersResponse;
import com.trybisz.isa.partner.entity.Partner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PartnersToResponseFunction implements Function<List<Partner>, GetPartnersResponse> {
    @Override
    public GetPartnersResponse apply(List<Partner> partners) {
        return GetPartnersResponse.builder()
                .partners(
                        partners.stream().map(
                                partner -> GetPartnersResponse.Partner.builder()
                                        .name(partner.getName())
                                        .id(partner.getId())
                                        .build()
                        ).toList()
                )
                .build();
    }
}
