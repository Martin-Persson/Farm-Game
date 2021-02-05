package Animals;

import Food.Food;

import Game.Player;
import java.io.Serializable;
import static Game.PlayerHelperClass.breedFeedSellPrint;
import static Game.ToolsHelperClass.*;

public abstract class Animal implements Serializable {
    
    private final String name;
    private final Gender gender;
    private int health = 100;
    private int age = 0;
    private int maxAge;
    private Food eatenFood;
    private int price;
    private boolean isSick = false;
    private int vetCost;
    
    public Animal(String gender, String name){
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.name = name;
    }
    
    public int sellAgeModifier(){
        int remainingYears = maxAge - age;
        if(((double)remainingYears / maxAge) < 0.3){
            return 80;
        }
        else if(((double)remainingYears / maxAge) >= 0.3 && (double)remainingYears / maxAge < 0.6){
            return 90;
        }
        else{
            return 100;
        }
    }
    
    public static void breedAnimal(Player player){
        int animalToBreed1, animalToBreed2;
        
        clear();
        if(player.getMyAnimals().size() < 2){
            System.out.println("Två djur är en bra förutsättning. köp fler djur först.");
            System.out.println("Tryck Enter...");
            prompt("");
            return;
        }
        System.out.println("\nVilka djur skulle du vilja försöka para?");
        breedFeedSellPrint(player);
        System.out.println(player.getMadeMove() ? " Avsluta rundan" : " Backa");
        
        animalToBreed1 = promptInt("\nVälj det första djuret du vill para: ", 1, player.getMyAnimals().size()+1) ;
        if(animalToBreed1 == player.getMyAnimals().size() + 1){
            player.setMadeMove(false);
            return;
        }
        animalToBreed2 = promptInt("\nVälj nu det andra djuret: ", 1, player.getMyAnimals().size());
        
        if(player.getMyAnimals().get(animalToBreed1 -1).getClass().equals(player.getMyAnimals().get(animalToBreed2 -1).getClass()) &&
                player.getMyAnimals().get(animalToBreed1 -1).getGender() != player.getMyAnimals().get(animalToBreed2 -1).getGender()){
    
            String typeOfAnimalToBreed = player.getMyAnimals().get(animalToBreed1 - 1).getClass().getSimpleName();
            
            boolean breedingSuccess = randomBoolean();
            if(breedingSuccess){
                player.addingNewBorneAnimals(typeOfAnimalToBreed);
            }
            else{
                System.out.println("Tyvärr, parningen lyckades inte.");
                System.out.println("Tryck Enter för att avsluta rundan...");
                prompt("");
                clear();
            }
            player.setMadeMove(true);
        }
        else{
            System.out.println("Det går inte att para olika arter eller djur av samma kön.");
            System.out.println("Tryck Enter för att fortsätta rundan...");
            prompt("");
            player.setMadeMove(false);
        }
        
    }
    
    static public String randomGender(){
        if(randomBoolean()){
            System.out.println("Grattis det blev en pojke!");
            return "MALE";
        }
        else{
            System.out.println("Grattis det blev en flicka!");
            return "FEMALE";
        }
    }
    
    //  Getters and setters
    //===================================
    public int getVetCost() {
        return vetCost;
    }
    
    public void setVetCost(int vetCost) {
        this.vetCost = vetCost;
    }
    
    public void setFood(Food food) {
        this.eatenFood = food;
    }
    
    public int getMaxAge() {
        return maxAge;
    }
    
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
    
    public boolean isSick() {
        return isSick;
    }
    
    public void setSick(boolean sick) {
        isSick = sick;
    }
    
    public Food getFood() {
        return eatenFood;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public Food getEatenFood() {
        return eatenFood;
    }
    
    public void setEatenFood(Food eatenFood) {
        this.eatenFood = eatenFood;
    }
    
    public String getName() {
        return name;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public double getHealth() {
        return health;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String toString(){
        return this.getName();
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
