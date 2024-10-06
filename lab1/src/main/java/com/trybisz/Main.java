package com.trybisz;

import com.trybisz.dto.CharacterDto;
import com.trybisz.entity.Character;
import com.trybisz.entity.Profession;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var professions = generateData();

        System.out.println("TASK 1,2");
        professions.forEach(
                profession -> {
                    System.out.println(profession);
                    profession.getCharacters().forEach(System.out::println);
                }
        );
        System.out.println("TASK 3");
        Set<Character> allCharacters = professions.stream()
                .map(Profession::getCharacters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        allCharacters.forEach(System.out::println);

        System.out.println("TASK 4");
        allCharacters.stream()
                .filter(character -> character.getProfession().getBaseArmor()>50)
                .sorted(Comparator.comparing(Character::getLevel))
                .forEach(System.out::println);

        System.out.println("TASK 5");
        List<CharacterDto> dtoList = allCharacters.stream()
                .map(CharacterDto::new)
                .sorted().toList();

        dtoList.forEach(System.out::println);

        System.out.println("TASK 6");
        serializeData(professions,"data.bin");
        var deserialized = deserializeData("data.bin");
        deserialized.forEach(
                profession -> {
                    System.out.println(profession);
                    profession.getCharacters().forEach(System.out::println);
                }
        );

        System.out.println("TASK 7");
        try(ForkJoinPool fjp = new ForkJoinPool(8)){
            Runnable job = () -> professions.parallelStream().forEach(profession -> {
                System.out.println(profession);
                profession.getCharacters().forEach(character -> {
                    try {
                        System.out.println(character);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
            fjp.submit(job);
        }
    }

    private static ArrayList<Profession> generateData(){
        var professions = new ArrayList<Profession>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            var profession = Profession.builder()
                    .name("Profession" + i)
                    .baseArmor(random.nextInt(100))
                    .characters(new ArrayList<>())
                    .build();
            professions.add(profession);
        }

        for (int i = 0; i < 10; i++) {
            var professionId = random.nextInt(5);
            var character = Character.builder()
                    .name("Character" + i)
                    .level(random.nextInt(100))
                    .profession(professions.get(professionId))
                    .build();
            professions.get(professionId).getCharacters().add(character);
        }
        return professions;
    }

    private static void serializeData(ArrayList<Profession> professions, String filename){
        try(FileOutputStream fos = new FileOutputStream(filename)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(professions);
            oos.close();
        } catch (IOException e) {
            System.err.println("Failed to open output stream: "+e);
        }
    }

    private static ArrayList<Profession> deserializeData(String filename){
        ArrayList<Profession> professions = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(filename)){
            ObjectInputStream ois = new ObjectInputStream(fis);
            professions = (ArrayList<Profession>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to open input stream: "+e);
        }
        return professions;
    }
}