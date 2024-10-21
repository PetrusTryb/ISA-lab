package com.trybisz.isa.offer.repository;

import com.trybisz.isa.offer.entity.Offer;
import com.trybisz.isa.partner.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @NonNull
    Optional<Offer> findById(@NonNull UUID id);
    List<Offer> findAllByPartner(Partner partner);
    @NonNull
    List<Offer> findAll();
}
