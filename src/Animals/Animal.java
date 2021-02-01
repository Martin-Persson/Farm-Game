package Animals;

import Food.Food;

public abstract class Animal {
    
    public String name;
    public Gender gender;
    private int health = 100;
    private int age = 0;
    int MAX_AGE;
    boolean isAlive = true;
    Food eatenFood;
    
    
    public int price;
    private int weight;
    
    
    public void setFood(Food food) {
        this.eatenFood = food;
    }
    
    public int getMAX_AGE() {
        return MAX_AGE;
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
    
    boolean ensilage = false;
    boolean korn = false;
    boolean kattmat = false;
    boolean foder = false;
    
    
    public Animal(String gender, String name){
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    
    public boolean isAlive() {
        return isAlive;
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
    
    public void setKattmat(boolean kattmat) {
        this.kattmat = kattmat;
    }
    
    public void setFoder(boolean foder) {
        this.foder = foder;
    }
    
    public void setEnsilage(boolean ensilage) {
        this.ensilage = ensilage;
    }
    
    public void setKorn(boolean korn) {
        this.korn = korn;
    }
    
    public boolean isEnsilage() {
        return ensilage;
    }
    
    public boolean isKorn() {
        return korn;
    }
    
    public boolean isKattmat() {
        return kattmat;
    }
    
    public boolean isFoder() {
        return foder;
    }
    

    
    public String toString(){
        return this.getGender().toString().toLowerCase();
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int sellAgeModifier(){
        int remainingYears = MAX_AGE - age;
        if(((double)remainingYears / MAX_AGE) < 0.3){
        return 80;
        }
        else if(((double)remainingYears / MAX_AGE) >= 0.3 && (double)remainingYears / MAX_AGE < 0.6){
            return 90;
        }
        else{
            return 100;
        }
    }
    
}
