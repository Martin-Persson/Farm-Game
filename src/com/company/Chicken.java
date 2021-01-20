package com.company;

public class Chicken extends Animal{
    String name;
    Gender gender;
    int price = 400;
    
    public Chicken(String name, String gender){
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
