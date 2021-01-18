package com.company;

public class Cow extends Animal{
    String name;
    boolean isMale;
    int price = 1000;
    
    @Override
    public int getPrice() {
        return price;
    }
    
    public Cow(boolean isMale){
        this.isMale = isMale;
        
        
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + isMale;
    }
}
