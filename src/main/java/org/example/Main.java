package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

record StringPet(String name, String species) {}

// The idea of a 'limited type' like an enum is that you cannot accidentally assign a wrong value to a variable of that
// type
// You also get
//   -compile time checks (instead of runtime errors)
//   -autocompletion (in an IDE)
//   -easy access to all valid values (by using .values()), so you never have to update a list in different places in
//    your program anymore. So if I would add a SNAKE, I do not need to update any other place in my code!
enum Species {CAT, DOG, HAMSTER, RABBIT}

record EnumPet(String name, Species species) {}

public class Main {
    public static void main(String[] args) {
        var stringPets = List.of(
                new StringPet("Duchess", "cat"),
                new StringPet("Brutus", "dog"),
                new StringPet("Pluto", "dog"),
                new StringPet("Nero", "cat"),
                new StringPet("Bugs", "wabbit")); // typo (or is it?)
                // if I would add a snake, I would have to remember adding it to two other places as well.

        System.out.println("STRINGPETS SORTED BY SPECIES");
        for (String species : List.of("cat", "rabbit")) { // I forgot a species
            for (StringPet pet : stringPets) {
                if (pet.species().equals(species)) System.out.println(pet);
            }
        }
        System.out.println();

        System.out.print("Please choose a species: ");
        Scanner in = new Scanner(System.in);
        String chosenStringSpecies = in.next();
        if (Stream.of("dog", "hamster", "goldfish", "rabbit").noneMatch(chosenStringSpecies::equalsIgnoreCase))
            System.out.println("We don't have any records on species '" + chosenStringSpecies + "'."); // argh! I don't have any goldfish, and I forgot the cats!

        System.out.println("STRINGPETS OF THE CHOSEN SPECIES");
        for (StringPet pet : stringPets) {
            if (pet.species().equalsIgnoreCase(chosenStringSpecies)) System.out.println(pet); // this does not work for rabbits
        }

        var enumPets = List.of(
                new EnumPet("Duchess", Species.CAT),
                new EnumPet("Brutus", Species.DOG),
                new EnumPet("Pluto", Species.DOG),
                new EnumPet("Nero", Species.CAT),
                new EnumPet("Bugs", Species.RABBIT)); // WABBIT causes compiler error. Nice!

        System.out.println("ENUMPETS SORTED BY SPECIES");
        for (Species species : Species.values()) { // I CANNOT forget a species
            for (EnumPet pet : enumPets) {
                if (pet.species() == species) System.out.println(pet);
            }
        }
        System.out.println();

        System.out.print("Please choose a species: ");
        String chosenEnumSpecies = in.next();

        System.out.println("PETS OF THE CHOSEN SPECIES");
        if (Arrays.stream(Species.values()).noneMatch(species -> chosenEnumSpecies.equalsIgnoreCase(species.name())))
            System.out.println("We don't have any records on species '" + chosenEnumSpecies + "'.");
        for (EnumPet pet : enumPets) {
            if (pet.species().name().equalsIgnoreCase(chosenEnumSpecies)) System.out.println(pet);
        }

        System.out.println("\nEnd");
    }
}