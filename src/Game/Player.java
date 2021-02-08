package Game;

import Animals.*;
import Food.Food;
import java.io.Serializable;
import java.util.ArrayList;
import static Animals.Animal.randomGender;
import static Game.PlayerHelperClass.breedFeedSellPrint;
import static Game.PlayerHelperClass.printFeedFood;
import static Game.ToolsHelperClass.*;

public class Player implements Serializable {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    ArrayList<Food> myFood = new ArrayList<>();
    ArrayList<Animal> deadAnimals = new ArrayList<>();
    
    private boolean isActive = true;
    private final String name;
    public int money = 2500;
    private boolean madeMove = false;
    Game game;
    
    public Player(String name){
        this.name = name;
    }
    
    public void feedAnimal(){
        boolean running = true;
        clear();
        while(running) {
            if(myFood.size() == 0){
                System.out.println("Du har inget att mata med...");
                prompt("Tryck Enter för att fortsätta...");
                break;
            }
            breedFeedSellPrint(this);
            System.out.println(getMadeMove()?" Avsluta rundan" : " Backa");
            int choice = promptInt("Villket djur skulle du vilja mata?", 1, myAnimals.size() + 1);
            if(choice == myAnimals.size() + 1){
                return;
            }else if(myAnimals.get(choice - 1).getHealth() >= 100){
                System.out.println("Djuret behöver inte äta.");
                continue;
            }
            printFeedFood(this);
            int choice2 = promptInt("Vad vill du mata med?", 1, getMyFood().size());
            clear();
            if(myAnimals.get(choice - 1).getEatenFood().getClass().getSimpleName().equals(myFood.get(choice2 - 1).getClass().getSimpleName())){
                if(myFood.get(choice2 - 1).getAmountOfFood() > 0){
                    System.out.println("Han käkar");
                    myAnimals.get(choice - 1).setHealth((int) (myAnimals.get(choice - 1).getHealth() + 10));
                    if(myAnimals.get(choice - 1).getHealth() > 100){
                        myAnimals.get(choice - 1).setHealth(100);
                    }
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
    
    public void addingNewBorneAnimals(String animalToBreed){
        int numberOfAnimals = 0;
        System.out.println("Grattis! Parningen lyckades.");
        switch (animalToBreed) {
            case "Cow" -> {
                numberOfAnimals = randomNum(5, 2);
                System.out.println("Du fick " +numberOfAnimals+ " kalvar.");
                for(int i = 0; i < numberOfAnimals; i++){
                    myAnimals.add(new Cow(randomGender(), namingAnimal()));
                }
            }
            case "Cat" -> {
                numberOfAnimals = randomNum(8, 4);
                System.out.println("Du fick " +numberOfAnimals+ " kattungar.");
                for(int i = 0; i < numberOfAnimals; i++){
                    myAnimals.add(new Cat(randomGender(), namingAnimal()));
                }
            }
            case "Chicken" -> {
                numberOfAnimals = randomNum(6, 2);
                System.out.println("Du fick " +numberOfAnimals+ " kycklingar.");
                for(int i = 0; i < numberOfAnimals; i++){
                    myAnimals.add(new Chicken(randomGender(), namingAnimal()));
                }
            }
            case "Pig" -> {
                numberOfAnimals = randomNum(6, 3);
                System.out.println("Du fick " +numberOfAnimals+ " kultingar.");
                for(int i = 0; i < numberOfAnimals; i++){
                    myAnimals.add(new Pig(randomGender(), namingAnimal()));
                }
            }
            case "Sheep" -> {
                numberOfAnimals = randomNum(6, 2);
                System.out.println("Du fick " +numberOfAnimals+ " lamm.");
                for(int i = 0; i < numberOfAnimals; i++){
                    myAnimals.add(new Sheep(randomGender(), namingAnimal()));
                }
            }
            default -> System.out.println("Ingen aning vad som hände...");
        }
    }
    
    public String namingAnimal() {
        return prompt("Vad ska ditt djur heta?");
    }
    
    //  Getters and setters
    //===================================
    
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
    
    public void setMadeMove(boolean madeMove) {
        this.madeMove = madeMove;
    }
    
    public ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }
    
    public ArrayList<Food> getMyFood() {
        return myFood;
    }
}
