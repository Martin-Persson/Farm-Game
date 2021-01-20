package com.company;
//TODO add price in animal
public class Cow extends Animal{
    
    
    public Cow(String name, String gender, int price){
        super(name, gender, price);
        
    }
    public int getPrice() {
        return price;
    }
    
    //  @Override
    // public String toString() {
    //   return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    //}
}

