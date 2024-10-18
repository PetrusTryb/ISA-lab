package com.trybisz.isa.repository;

import com.trybisz.isa.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID> {
    @NonNull
    Optional<Partner> findById(@NonNull UUID id);
    @NonNull
    List<Partner> findAll();
}
