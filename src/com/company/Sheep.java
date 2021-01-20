package com.company;

public class Sheep extends Animal{
    int price = 700;
    
    public Sheep(String name, String gender, int price){
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
