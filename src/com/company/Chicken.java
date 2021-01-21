package com.company;

public class Chicken extends Animal{
    
    
    public Chicken(String name, String gender){
        super(name, gender);
        price = 400;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
