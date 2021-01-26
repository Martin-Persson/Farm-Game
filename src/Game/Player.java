package Game;


import Animals.*;
import Food.Food;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

public class Player {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    ArrayList<Food> myFood = new ArrayList<>();
    Random rand = new Random();
    
    public String name;
    public int money = 2500;
    String typeOfAnimalToBreed;
    boolean breedingSuccess;
    boolean madeMove = false;
    
    public Player(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public boolean getMadeMove() {
        return madeMove;
    }
    
    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }
    
    public void printInventory(){
        System.out.printf("\nPengar =  %dkr.\n", money);
        System.out.println();
        printAnimals();
    }
    public void printAnimals(){
        int counter = 1;
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
            System.out.println("Du äger inga djur.");
        }
    }
    
    public void printFood(){
        int counter = 1;
        if (myFood.size() > 0) {
            System.out.format("%-10s%-10s\n", "Typ", "Mängd");
            System.out.println("-".repeat(30));
            for (Food food : myFood) {
                System.out.format("%d %-10s%-10s\n", counter, food.getClass().getSimpleName(), food.getAmountOfFood());
                
                counter++;
            }
        } else {
            System.out.println("Du äger ingen mat.");
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
        
        printAnimals();
        try{
            System.out.print("\nVälj det första djuret du vill para: ");
            animalToBreed1 = Integer.parseInt(input.nextLine());
            System.out.print("\nVälj nu det andra djuret: ");
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
                System.out.println("Tryck Enter för att avsluta rundan...");
                input.nextLine();
                System.out.println("\n".repeat(40));
            }
        }
        else{
            System.out.println("Det går inte att para olika arter eller djur av samma kön.");
            return; //TODO en loop här så att man får försöka igen?
        }
        
    }
    
    public void feedAnimal(Store store){
        int counter = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Villket djur skulle du vilja mata?");
        printAnimals();
        int choice = Integer.parseInt(input.nextLine());
        System.out.println("Vad vill du mata med?");
        for(Food food : store.foodList){
            System.out.println(counter + " " + food.getClass().getSimpleName());
            counter++;
        }
        int choice2 = Integer.parseInt(input.nextLine());
        switch(choice2) {
            case 1:
                if (myAnimals.get(choice - 1).isEnsilage()) {
                    if(myAnimals.get(choice - 1).getHealth() == 100){
                        System.out.println("Djuret är redan mätt.");
                    }
                    else{
                        System.out.println("Han käkar");
                        myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth()+10));
                        System.out.println(myAnimals.get(choice - 1).getHealth());
                        
                    }
                } else {
                    System.out.println("Djuret gilla inte " + store.foodList.get(choice2 - 1).getClass().getSimpleName());
                }
                break;
            case 2:
                if(myAnimals.get(choice - 1).isKattmat()){
                    if(myAnimals.get(choice - 1).getHealth() == 100){
                        System.out.println("Djuret är redan mätt");
                    }
                    else{
                        System.out.println("Han äter kattmat");
                        myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth()+10));
                        System.out.println(myAnimals.get(choice - 1).getHealth());
                    }
                }
                else{
                    System.out.println("Djuret gilla inte " + store.foodList.get(choice2 - 1).getClass().getSimpleName());
                }
                break;
            case 3:
                if(myAnimals.get(choice - 1).isKorn()){
                    if(myAnimals.get(choice - 1).getHealth() == 100){
                        System.out.println("Djuret är redan mätt");
                    }
                    else{
                    System.out.println("han käkade korn");
                        myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth()+10));
                        System.out.println(myAnimals.get(choice - 1).getHealth());
                    }
                }
                else{
                    System.out.println("Djuret gilla inte " + store.foodList.get(choice2 - 1).getClass().getSimpleName());
                }
                break;
            case 4:
                if(myAnimals.get(choice - 1).isFoder()){
                    if(myAnimals.get(choice - 1).getHealth() == 100){
                        System.out.println("Djuret är redan mätt");
                    }
                    else{
                    System.out.println("Han käkar foder");
                        myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth()+10));
                        System.out.println(myAnimals.get(choice - 1).getHealth());
                    }
                }
                else{
                    System.out.println("Djuret gilla inte " + store.foodList.get(choice2 - 1).getClass().getSimpleName());
                }
                break;
        }
    }
    
    public void addingNewBorneAnimals(){
        System.out.println("Grattis! Parningen lyckades.");
        switch (typeOfAnimalToBreed){
            case "Cow":
                System.out.println("Du fick en liten kalv.");
                myAnimals.add(new Cow(namingAnimal(), "male"));
                break;
            case "Cat":
                System.out.println("Du fick två kattungar.");
                myAnimals.add(new Cat(namingAnimal(), randomGender()));
                myAnimals.add(new Cat(namingAnimal(), randomGender()));
                break;
            case "Chicken":
                System.out.println("Du fick tre kycklingar");
                myAnimals.add(new Chicken(namingAnimal(), randomGender()));
                myAnimals.add(new Chicken(namingAnimal(), randomGender()));
                myAnimals.add(new Chicken(namingAnimal(), randomGender()));
                break;
            case "Pig":
                System.out.println("Du fich en liten kulting.");
                myAnimals.add(new Pig(namingAnimal(), randomGender()));
                break;
            case "Sheep":
                System.out.println("Du fick två lamm.");
                myAnimals.add(new Sheep(namingAnimal(), randomGender()));
                myAnimals.add(new Sheep(namingAnimal(), randomGender()));
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
