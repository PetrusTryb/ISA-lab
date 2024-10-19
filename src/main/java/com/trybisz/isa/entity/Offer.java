package com.trybisz.isa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Entity(name = "offers")
public class Offer implements Serializable {
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String Title;
    private String Description;
    @Temporal(TemporalType.DATE)
    private Date ValidFrom;
    @Temporal(TemporalType.DATE)
    private Date ValidTo;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
}
