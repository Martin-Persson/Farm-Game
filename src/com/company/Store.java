package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Store {
    ArrayList<Animal> animalList = new ArrayList<>(Arrays.asList(new Cow("", "female"),
            new Cat("", "female"),
            new Chicken("", "female"),
            new Pig("", "female"),
            new Sheep("", "female")));
    ArrayList<Food> foodList = new ArrayList<>();
    
    int menuChoice;
    
    public void buyAnimals(Player player) {
        Scanner input = new Scanner(System.in);
        
        for(Animal animal : animalList){
            System.out.print(animal.getClass().getSimpleName() + " " + animal.getPrice());
            System.out.println();
        }
        
        System.out.println("Välkommen till Jöns defekta djur!\n");
        while (true) {//TODO Formatting table
            System.out.println("Detta är djuren vi har att erbjuda:\n");
            System.out.println("    |   Sort    |     Pris    |    Foder");
            System.out.println("-----------------------------------------");
            System.out.println("""
                    [1] | Ko        |   1000kr    |     Gräs
                    [2] | Katt      |   300kr     |     Möss
                    [3] | Kyckling  |   400kr     |     Frön
                    [4] | Gris      |   600kr     |     Foder
                    [5] | Får       |   700kr     |     Foder
                    [6] [Tilllbaka]
                    """);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            do {
                System.out.println("-= Vilket djur vill du köpa? =-");
                try {
                    menuChoice = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Något gick fel, försök igen");
                }
            } while (!(menuChoice > 0 && menuChoice < 7));
            switch (menuChoice) {
                case 1:
                    if (player.money > animalList.get(0).getPrice()) {
                        player.myAnimals.add(new Cow(namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 2:
                    if (player.money > animalList.get(1).getPrice()) {
                        player.myAnimals.add(new Cat(namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 3:
                    if (player.money > animalList.get(2).getPrice()) {
                        player.myAnimals.add(new Chicken(namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 4:
                    if (player.money > animalList.get(3).getPrice()) {
                        player.myAnimals.add(new Pig(namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 5:
                    if (player.money > animalList.get(4).getPrice()) {
                        player.myAnimals.add(new Sheep(namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 6:
                    return;
            }
        }
    }
    
    public void sellAnimals(Player player){
    
    }
    
    public void breedAnimal(Player player){
    
    }
    
    public void buyFood(Player player){
    
    }
    
    public void feedAnimal(Player player){
    
    }
    
    public String namingAnimal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vad ska ditt djur heta?");
        return input.nextLine();
    }
    
    public String genderOfAnimal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Och vilket kön önskas (skriv hane eller hona)?");
        String gender = input.nextLine();
        if (gender.equalsIgnoreCase("HANE")) {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }
        return gender;
    }
    

    public int pay(Player player){
        for(Animal animal : animalList){
            if(player.myAnimals.get(player.myAnimals.size()-1).getClass().getSimpleName()
                    .equalsIgnoreCase(animal.getClass().getSimpleName())){
                
                player.money -= animal.getPrice();
            }
            else{
                System.out.println();
            }
        }
        return player.money;
    }
}