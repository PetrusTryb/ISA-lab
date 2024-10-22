package com.trybisz.isa_partner.function;

import com.trybisz.isa_partner.dto.CreateOrUpdatePartner;
import com.trybisz.isa_partner.entity.Partner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestToPartnerFunction implements Function<CreateOrUpdatePartner, Partner> {

    @Override
    public Partner apply(CreateOrUpdatePartner createOrUpdatePartner) {
        return Partner.builder()
                .Name(createOrUpdatePartner.getName())
                .SinceYear(createOrUpdatePartner.getSinceYear())
                .Website(createOrUpdatePartner.getWebsite())
                .build();
    }
}
