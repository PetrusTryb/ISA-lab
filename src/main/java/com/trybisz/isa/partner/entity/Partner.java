package com.trybisz.isa.partner.entity;
import com.trybisz.isa.offer.entity.Offer;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
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
    @Column(name = "partner_id")
    private UUID id = UUID.randomUUID();
    private String Name;
    private URL Website;
    private int SinceYear;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Offer> Offers = new ArrayList<>();
}
