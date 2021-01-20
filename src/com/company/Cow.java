package com.company;

public class Cow extends Animal{
    String name;
    Gender gender;
    int price = 1000;
    
    public int getPrice() {
        return price;
    }
    
    public Cow(String name, String gender){
        super(name, gender);
        
    }
    
    //  @Override
    // public String toString() {
    //   return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    //}
}

