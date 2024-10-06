package com.trybisz.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Character implements Serializable {
    String name;
    int level;
    Profession profession;
}
