package com.trybisz.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Profession {
    String name;
    int baseArmor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Character> characters;
}
