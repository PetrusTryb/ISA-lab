package com.trybisz.isa_partner.event.api;

import java.util.UUID;

public interface PartnerEventRepository {
    void create(UUID id);
    void delete(UUID id);
}
