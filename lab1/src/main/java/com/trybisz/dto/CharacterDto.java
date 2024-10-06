package com.trybisz.dto;
import com.trybisz.entity.Character;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CharacterDto implements Comparable<CharacterDto> {
    String name;
    int level;
    String profession;

    public CharacterDto(Character character) {
        this.name = character.getName();
        this.level = character.getLevel();
        this.profession = character.getProfession().getName();
    }

    @Override
    public int compareTo(CharacterDto o) {
        return this.name.compareTo(o.name);
    }
}
