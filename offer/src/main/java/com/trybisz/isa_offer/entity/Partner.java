package com.trybisz.isa_offer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Entity(name = "partners")
public class Partner implements Serializable {
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @OneToMany(mappedBy = "partner")
    private List<Offer> offers;
}
