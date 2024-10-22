package com.trybisz.isa_partner.event.impl;

import com.trybisz.isa_partner.event.api.PartnerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class PartnerEventRepositoryImpl implements PartnerEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public PartnerEventRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void create(UUID id) {
        restTemplate.put("/partners/{id}", null, id);
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete("/partners/{id}", id);
    }
}
