package com.company;

public class Cat extends Animal{
    String name;
    Gender gender;
    int price = 300;
    
    public Cat(String name, String gender){
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

