package com.trybisz.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Character {
    String name;
    int level;
    Profession profession;
}
