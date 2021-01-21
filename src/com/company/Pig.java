package com.company;

public class Pig extends Animal{
    
    
    public Pig(String name, String gender){
        super(name, gender);
        price = 600;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
