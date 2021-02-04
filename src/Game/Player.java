package Game;

import Animals.*;
import Food.Food;

import java.io.Serializable;
import java.util.ArrayList;
import static Game.HelperClass.*;

public class Player implements Serializable {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    ArrayList<Food> myFood = new ArrayList<>();
    ArrayList<Animal> deadAnimals = new ArrayList<>();
    
    private boolean isActive = true;
    private final String name;
    public int money = 2500;
    private String typeOfAnimalToBreed;
    private boolean madeMove = false;
    Game game;
    
    public Player(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setGame(Game game) {
        this.game = game;
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
    
    public void setMoney(int money) {
        this.money = money;
    }
    
    public void checkIfPlayerIsActive(){
        if(myAnimals.size() == 0 && this.getMoney() < 300){
            setActive(false);
        }
    }
    
    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }
    
    public void printInventory(Game game){
        System.out.printf("-".repeat(10) + "=".repeat(5) + " Runda %d av %d " + "=".repeat(5) +
                "-".repeat(10), game.currentRound, game.rounds);
        System.out.printf("\nNu är det %ss tur.\t Pengar: %dKr\n",
                getName(), getMoney());
    
        printFood();
        printAnimals();
        System.out.println("-".repeat(45));
    }
    
    public void printAnimals(){
        
        if (myAnimals.size() > 0) {
            System.out.println("Dina djur:");
            System.out.format("%-10s%-10s%-11s%-8s%s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
            
            for (Animal animal : myAnimals) {
                System.out.format("%-10s%-10s%s/%-2s%10.0f%s%9s",
                        HelperClass.translateAnimals(animal.getClass().getSimpleName()),
                        animal.getName(), animal.getAge(), animal.getMaxAge(), animal.getHealth(), "%",
                        animal.getGender().toString().equalsIgnoreCase("male") ? "Hane\n": "Hona\n");
            }
        }   else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    public void printFood(){
        if (myFood.size() > 0) {
            System.out.println("Din mat:");
            System.out.format("%-10s%-10s\n", "Typ", "Mängd");
            for (Food food : myFood) {
                System.out.format("%-10s%-10s\n", food.getClass().getSimpleName(), food.getAmountOfFood());
            }
            System.out.println("-".repeat(45));
        } else {
            System.out.println("Du äger ingen mat.");
        }
    }
    
    public void printFeedFood(){
        int counter = 1;
        if (myFood.size() > 0) {
            System.out.println("Din mat:");
            System.out.format("%-10s%-10s\n", "Typ", "Mängd");
            for (Food food : myFood) {
                System.out.format("[%d] %-10s%-10s\n", counter, food.getClass().getSimpleName(), food.getAmountOfFood());
                counter++;
            }
            System.out.println("-".repeat(45));
        } else {
            System.out.println("Du äger ingen mat.");
        }
    }
    
    public void breedFeedSellPrint(){
        if (myAnimals.size() > 0) {
            int counter = 1;
            System.out.println("Dina djur:");
            System.out.format("%7s%10s%12s%11s%6s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
        
            for (Animal animal : myAnimals) {
                System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%s%9s",
                        counter, translateAnimals(animal.getClass().getSimpleName()),
                        animal.getName(), animal.getAge(), animal.getMaxAge(), animal.getHealth(), "%",
                        animal.getGender().toString().equalsIgnoreCase("male") ? "Hane\n": "Hona\n");
                counter++;
            }
            System.out.printf("[%d]", counter);
        }   else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    public void breedAnimal(){
        int animalToBreed1, animalToBreed2;
        
        clear();
        if(myAnimals.size() < 2){
            System.out.println("Två djur är en bra förutsättning. köp fler djur först.");
            System.out.println("Tryck Enter...");
            prompt("");
            return;
        }
        System.out.println("\nVilka djur skulle du vilja försöka para?");
        breedFeedSellPrint();
        System.out.println(getMadeMove() ? " Avsluta rundan" : " Backa");
        
        animalToBreed1 = promptInt("\nVälj det första djuret du vill para: ", 1, myAnimals.size()+1) ;
        if(animalToBreed1 == myAnimals.size() + 1){
            setMadeMove(false);
            return;
        }
        animalToBreed2 = promptInt("\nVälj nu det andra djuret: ", 1, myAnimals.size());
        
        if(myAnimals.get(animalToBreed1 -1).getClass().equals(myAnimals.get(animalToBreed2 -1).getClass()) &&
                myAnimals.get(animalToBreed1 -1).getGender() != myAnimals.get(animalToBreed2 -1).getGender()){
            
            typeOfAnimalToBreed = myAnimals.get(animalToBreed1 -1).getClass().getSimpleName();
            
            boolean breedingSuccess = randomBoolean();
            if(breedingSuccess){
                addingNewBorneAnimals();
                this.setMadeMove(true);
            }
            else{
                System.out.println("Tyvärr, parningen lyckades inte.");
                System.out.println("Tryck Enter för att avsluta rundan...");
                prompt("");
                clear();
                this.setMadeMove(true);
            }
        }
        else{
            System.out.println("Det går inte att para olika arter eller djur av samma kön.");
            System.out.println("Tryck Enter för att fortsätta rundan...");
            prompt("");
            this.setMadeMove(false);
        }
    }
    
    public void feedAnimal(){
        boolean running = true;
        clear();
        while(running) {
            if(myFood.size() == 0){
                System.out.println("Du har inget att mata med...");
                System.out.println("Tryck Enter för att fortsätta...");
                prompt("");
                break;
            }
            breedFeedSellPrint();
            System.out.println(getMadeMove()?" Avsluta rundan" : " Backa");
            int choice = promptInt("Villket djur skulle du vilja mata?", 1, myAnimals.size() + 1);
            if(choice == myAnimals.size() + 1){
                return;
            }else if(myAnimals.get(choice - 1).getHealth() >= 100){
                System.out.println("Djuret behöver inte äta.");
                continue;
            }
            printFeedFood();
            int choice2 = promptInt("Vad vill du mata med?", 1, getMyFood().size());
            clear();
            if(myAnimals.get(choice - 1).getEatenFood().getClass().getSimpleName().equals(myFood.get(choice2 - 1).getClass().getSimpleName())){
                if(myFood.get(choice2 - 1).getAmountOfFood() > 0){
                    System.out.println("Han käkar");
                    myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth() + 10));
                    System.out.printf("%ss hälsa är nu %.0f%s\n", myAnimals.get(choice - 1).getName(),
                            myAnimals.get(choice - 1).getHealth(), "%");
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
                System.out.println("Du fick tre kalvar.");
                myAnimals.add(new Cow(randomGender(), namingAnimal()));
                myAnimals.add(new Cow(randomGender(), namingAnimal()));
                myAnimals.add(new Cow(randomGender(), namingAnimal()));
            }
            case "Cat" -> {
                System.out.println("Du fick sex kattungar.");
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
                myAnimals.add(new Cat(randomGender(), namingAnimal()));
            }
            case "Chicken" -> {
                System.out.println("Du fick fyra kycklingar");
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                myAnimals.add(new Chicken(randomGender(), namingAnimal()));
            }
            case "Pig" -> {
                System.out.println("Du fick fem kultingar.");
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
                myAnimals.add(new Pig(randomGender(), namingAnimal()));
            }
            case "Sheep" -> {
                System.out.println("Du fick fyra lamm.");
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
                myAnimals.add(new Sheep(randomGender(), namingAnimal()));
            }
            default -> System.out.println("Ingen aning vad som hände...");
        }
    }
    
    public String namingAnimal() {
        return prompt("Vad ska ditt djur heta?");
    }
    
    public String randomGender(){
        if(randomBoolean()){
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
