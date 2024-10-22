package com.trybisz.isa_partner.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.net.URL;
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
}
