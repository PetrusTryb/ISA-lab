package com.trybisz.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Profession implements Serializable {
    String name;
    int baseArmor;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Character> characters;
}
