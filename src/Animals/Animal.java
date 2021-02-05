package Animals;

import Food.Food;
import java.io.Serializable;

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
