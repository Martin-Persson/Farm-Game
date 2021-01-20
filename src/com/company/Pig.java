package com.company;

public class Pig extends Animal{
    
    
    public Pig(String name, String gender, int price){
        super(name, gender, price);
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
