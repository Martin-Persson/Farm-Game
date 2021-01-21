package com.company;

public class Cat extends Animal{
    
    
    public Cat(String name, String gender){
        super(name, gender);
        price = 300;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}

