package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Player {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    HashMap<Food, Integer> myFood = new HashMap<>();
    Random rand = new Random();
    public String name;
    public int money = 2500;
    String typeOfAnimalToBreed;
    boolean breedingSuccess;
    public Player(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void printInventory(){//TODO Formatting a table
        System.out.printf("\nPengar =  %dkr.\n", money);
        System.out.println();
        if(myAnimals.size() > 0){
            System.out.format("%-10s%-10s%-10s\n", "Typ", "Namn", "Kön");
            System.out.println("-".repeat(30));
            for(Animal animal : myAnimals){
                if(animal.getGender().name().equalsIgnoreCase("MALE")){
                    System.out.format("%-10s%-10s%-10s\n", animal.getClass().getSimpleName(), animal.name, "hane");
                }
                else{
                    System.out.format("%-10s%-10s%-10s\n", animal.getClass().getSimpleName(), animal.name, "hona");
                }
            }
        }
        else{
            System.out.println("Du äger inga djur ännu.");
        }
    }
    
    public void breedAnimal(){
        Random rand = new Random();
        int counter = 1;
        int animalToBreed1 = 0;
        int animalToBreed2 = 0;
        Scanner input = new Scanner(System.in);
        if(myAnimals.size() < 2){
            System.out.println("Två djur är en bra förutsättning. köp fler djur först.");
            return;
        }
        System.out.println("Vilka djur skulle du vilja försöka para?");
        
            if (myAnimals.size() > 0) {
                System.out.format("%-10s%-10s%-10s\n", "Typ", "Namn", "Kön");
                System.out.println("-".repeat(30));
                for (Animal animal : myAnimals) {
                    if (animal.getGender().name().equalsIgnoreCase("MALE")) {
                        System.out.format("%d %-10s%-10s%-10s\n", counter, animal.getClass().getSimpleName(), animal.name, "hane");
                    } else {
                        System.out.format("%d %-10s%-10s%-10s\n", counter, animal.getClass().getSimpleName(), animal.name, "hona");
                    }
                    counter++;
                }
            } else {
                System.out.println("Du äger inga djur ännu.");
            }
            try{
                animalToBreed1 = Integer.parseInt(input.nextLine());
                System.out.println("And now the second one:");
                animalToBreed2 = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                System.out.println("Nu blev det fel, försök igen.");
            }
            if(myAnimals.get(animalToBreed1 -1).getClass().equals(myAnimals.get(animalToBreed2 -1).getClass()) &&
            myAnimals.get(animalToBreed1 -1).getGender() != myAnimals.get(animalToBreed2 -1).getGender()){
                //Sucsess! komma ihåg klassen på djuren
                typeOfAnimalToBreed = myAnimals.get(animalToBreed1 -1).getClass().getSimpleName();
                //random chans
                breedingSuccess = rand.nextBoolean();
                if(breedingSuccess){
                   addingNewBorneAnimals();
                }
                else{
                    System.out.println("Tyvärr, parningen lyckades inte.");
                }
            }
            else{
                System.out.println("Det går inte att para olika arter eller djur av samma kön.");
            }
        
    }
    
    public void addingNewBorneAnimals(){
        System.out.println("Grattis! Parningen lyckades.");
        switch (typeOfAnimalToBreed){
            case "Cow":
                System.out.println("Du fick en liten kalv.");
                myAnimals.add(new Cow(namingAnimal(), "male", 1000));
                break;
            case "Cat":
                System.out.println("Du fick två kattungar.");
                myAnimals.add(new Cat(namingAnimal(), randomGender(), 300));
                myAnimals.add(new Cat(namingAnimal(), randomGender(), 300));
                break;
            case "Chicken":
                System.out.println("Du fick tre kycklingar");
                myAnimals.add(new Chicken(namingAnimal(), randomGender(), 400));
                myAnimals.add(new Chicken(namingAnimal(), randomGender(), 400));
                myAnimals.add(new Chicken(namingAnimal(), randomGender(), 400));
                break;
            case "Pig":
                System.out.println("Du fich en liten kulting.");
                myAnimals.add(new Pig(namingAnimal(), randomGender(), 600));
                break;
            case "Sheep":
                System.out.println("Du fick två lamm.");
                myAnimals.add(new Sheep(namingAnimal(), randomGender(), 700));
                myAnimals.add(new Sheep(namingAnimal(), randomGender(), 700));
                break;
            default:
                System.out.println("Ingen aning vad som hände...");
                break;
        }
    }
    public String namingAnimal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vad ska ditt djur heta?");
        return input.nextLine();
    }
    public String randomGender(){
        if(rand.nextBoolean()){
            return "MALE";
        }
        else{
            return "FEMALE";
        }
    
    }
}
