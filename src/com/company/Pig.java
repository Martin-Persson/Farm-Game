package com.company;

public class Pig extends Animal{
    String name;
    Gender gender;
    int price = 600;
    
    public Pig(String name, String gender){
        super(name, gender);
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
