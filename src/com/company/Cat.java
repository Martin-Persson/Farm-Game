package com.company;

public class Cat extends Animal{
    
    
    public Cat(String name, String gender, int price){
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

