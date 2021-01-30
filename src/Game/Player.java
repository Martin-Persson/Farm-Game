package Game;

import Animals.*;
import Food.Food;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    ArrayList<Food> myFood = new ArrayList<>();
    ArrayList<Animal> deadAnimals = new ArrayList<>();
    Random rand = new Random();
    public boolean isActive = true;
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
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public boolean getMadeMove() {
        return madeMove;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void checkIfPlayerIsActive(){
        if(myAnimals.size() == 0 && this.getMoney() < 300){
            setActive(false);
        }
    }
    
    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }
    
    public void printInventory(){
        System.out.printf("\nPengar =  %dkr.\n", money);
        System.out.println();
        printFood();
        int counter = 1;
        if (myAnimals.size() > 0) {
            System.out.println("Dina djur:");
            System.out.format("%7s%12s%9s%11s%7s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
            System.out.println("-".repeat(40));
            for (Animal animal : myAnimals) {
                if (animal.getGender().name().equalsIgnoreCase("MALE")) {
                    System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%9s",
                            counter, HelperClass.translateAnimals(animal.getClass().getSimpleName()),
                            animal.name, animal.getAge(), animal.getMAX_AGE(), animal.getHealth(), "hane\n");
                } else {
                    System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%9s",
                            counter, HelperClass.translateAnimals(animal.getClass().getSimpleName()),
                            animal.name, animal.getAge(), animal.getMAX_AGE(), animal.getHealth() ,"hona\n");
                }
                counter++;
            }
        } else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    public void printAnimals(){
        int counter = 1;
        if (myAnimals.size() > 0) {
            System.out.println("Dina djur:");
            System.out.format("%7s%12s%9s%11s%7s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
            System.out.println("-".repeat(40));
            for (Animal animal : myAnimals) {
                if (animal.getGender().name().equalsIgnoreCase("MALE")) {
                    System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%9s\n",
                            counter, HelperClass.translateAnimals(animal.getClass().getSimpleName()),
                            animal.name, animal.getAge(), animal.getMAX_AGE(), animal.getHealth(), "hane");
                } else {
                    System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%9s\n",
                            counter, HelperClass.translateAnimals(animal.getClass().getSimpleName()),
                            animal.name, animal.getAge(), animal.getMAX_AGE(), animal.getHealth() ,"hona");
                }
                counter++;
            }
            if(!(this.getMadeMove())){
                System.out.format("[%d] %-10s\n", counter, "Backa");
            }
            else{
                System.out.format("[%d] %-10s\n", counter, "Avsluta rundan");
            }
        } else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    public void printFood(){
        int counter = 1;
        if (myFood.size() > 0) {
            System.out.println("Din mat:");
            System.out.format("%-10s%-10s\n", "Typ", "Mängd");
            System.out.println("-".repeat(40));
            for (Food food : myFood) {
                System.out.format("[%d] %-10s%-10s\n", counter, food.getClass().getSimpleName(), food.getAmountOfFood());
                counter++;
            }
            System.out.println("-".repeat(40));
        } else {
            System.out.println("Du äger ingen mat.");
        }
    }
    
    public void breedAnimal(){
        Random rand = new Random();
        int animalToBreed1, animalToBreed2;
        Scanner input = new Scanner(System.in);
        HelperClass.clear();
        if(myAnimals.size() < 2){
            System.out.println("Två djur är en bra förutsättning. köp fler djur först.");
            System.out.println("Tryck Enter...");
            input.nextLine();
            return;
        }
        System.out.println("Vilka djur skulle du vilja försöka para?");
        
        printAnimals();
        
        animalToBreed1 = HelperClass.promptInt("\nVälj det första djuret du vill para: ", 1, myAnimals.size());
        if(animalToBreed1 == myAnimals.size() + 1){
            setMadeMove(false);
            return;
        }
        animalToBreed2 = HelperClass.promptInt("\nVälj nu det andra djuret: ", 1, myAnimals.size());
        
        if(myAnimals.get(animalToBreed1 -1).getClass().equals(myAnimals.get(animalToBreed2 -1).getClass()) &&
                myAnimals.get(animalToBreed1 -1).getGender() != myAnimals.get(animalToBreed2 -1).getGender()){
            //Sucsess! komma ihåg klassen på djuren
            typeOfAnimalToBreed = myAnimals.get(animalToBreed1 -1).getClass().getSimpleName();
            //random chans
            breedingSuccess = rand.nextBoolean();
            if(breedingSuccess){
                addingNewBorneAnimals();
                this.setMadeMove(true);
            }
            else{
                System.out.println("Tyvärr, parningen lyckades inte.");
                System.out.println("Tryck Enter för att avsluta rundan...");
                input.nextLine();
                HelperClass.clear();
                this.setMadeMove(true);
            }
        }
        else{
            System.out.println("Det går inte att para olika arter eller djur av samma kön.");
            System.out.println("Tryck Enter för att fortsätta rundan...");
            input.nextLine();
            this.setMadeMove(false);
        }
    }
    
    public void feedAnimal(){
        boolean running = true;
        HelperClass.clear();
        while(running) {
            printAnimals();
            int choice = HelperClass.promptInt("Villket djur skulle du vilja mata?", 1, myAnimals.size() + 1);
            if(choice == myAnimals.size() + 1){
                return;
            }else if(myAnimals.get(choice - 1).getHealth() >= 100){
                System.out.println("Djuret behöver inte äta.");
            }
            printFood();
            int choice2 = HelperClass.promptInt("Vad vill du mata med?", 1, getMyFood().size());
            
            if(myAnimals.get(choice - 1).getEatenFood().getClass().getSimpleName().equals(myFood.get(choice2 - 1).getClass().getSimpleName())){
                if(myFood.get(choice2 - 1).getAmountOfFood() > 0){
                    System.out.println("Han käkar");
                    myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth() + 10));
                    System.out.println(myAnimals.get(choice - 1).getHealth());
                    getMyFood().get(choice2 - 1).setAmountOfFood(getMyFood().get(choice2 - 1).getAmountOfFood() - 1);
                    this.setMadeMove(true);
                }
                else{
                    System.out.println("Du har inte tillräckligt med mat.");
                }
            }
            else{
                System.out.println("Djuret gillar inte " + getMyFood().get(choice2 - 1).getClass().getSimpleName());
            }
        }
    }
    
    public void addingNewBorneAnimals(){
        System.out.println("Grattis! Parningen lyckades.");
        switch (typeOfAnimalToBreed) {
            case "Cow" -> {
                System.out.println("Du fick en liten kalv.");
                myAnimals.add(new Cow(randomGender(), namingAnimal()));
            }
            case "Cat" -> {
                System.out.println("Du fick två kattungar.");
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
            }
            case "Chicken" -> {
                System.out.println("Du fick tre kycklingar");
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
            }
            case "Pig" -> {
                System.out.println("Du fich en liten kulting.");
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
            }
            case "Sheep" -> {
                System.out.println("Du fick två lamm.");
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
            }
            default -> System.out.println("Ingen aning vad som hände...");
        }
    }
    
    public String namingAnimal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vad ska ditt djur heta?");
        return input.nextLine();
    }
    
    public String randomGender(){
        if(rand.nextBoolean()){
            System.out.println("Grattis det blev en pojke!");
            return "MALE";
        }
        else{
            System.out.println("Grattis det blev en flicka!");
            return "FEMALE";
        }
        
    }
    
    public ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }
    
    public ArrayList<Food> getMyFood() {
        return myFood;
    }
}
