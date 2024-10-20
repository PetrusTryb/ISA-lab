package com.trybisz.isa;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import com.trybisz.isa.offer.service.api.OfferService;
import com.trybisz.isa.partner.service.api.PartnerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class DataInitializer {
    private final OfferService offerService;
    private final PartnerService partnerService;

    @Autowired
    public DataInitializer(OfferService offerService, PartnerService partnerService) {
        this.offerService = offerService;
        this.partnerService = partnerService;
    }

    @PostConstruct
    private void generateData(){
        if(!this.offerService.findAll().isEmpty() || !this.partnerService.findAll().isEmpty())
            return;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Partner partner;
            try {
                partner = Partner.builder()
                        .Name("Partner" + i)
                        .Website(new URL("https://partner"+i+".com"))
                        .SinceYear(random.nextInt(2024-2000)+2000)
                        .build();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            this.partnerService.save(partner);

            for(int j = 0; j<3; j++){
                Date now = new Date();
                Offer offer = Offer.builder()
                        .Title("Offer " + i + "/" + j)
                        .Description("Lorem ipsum...")
                        .ValidFrom(now)
                        .ValidTo(new Date(now.getTime()+random.nextInt(365)*(1000 * 60 * 60 * 24)))
                        .partner(partner).build();
                offerService.save(offer);
            }
        }
        Logger.getLogger(this.getClass().getCanonicalName()).info("Data generated successfully");
    }
}
