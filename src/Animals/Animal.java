package Animals;

import Food.Food;
import java.io.Serializable;

public abstract class Animal implements Serializable {
    
    protected String name;
    protected Gender gender;
    protected int health = 100;
    protected int age = 0;
    protected int maxAge;
    protected boolean isAlive = true;
    protected Food eatenFood;
    protected int price;
    protected int weight;
    
    
    public void setFood(Food food) {
        this.eatenFood = food;
    }
    
    public int getMaxAge() {
        return maxAge;
    }
    
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
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
    
    public Animal(String gender, String name){
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.name = name;
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
    
    public int getWeight() {
        return weight;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String toString(){
        return this.getGender().toString().toLowerCase();
    }
    
    public void setAge(int age) {
        this.age = age;
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
    
}
