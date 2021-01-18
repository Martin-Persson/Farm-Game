package com.company;

public class Chicken extends Animal{
    boolean isMale;
    int price = 500;
    
    public Chicken(boolean isMale) {
        this.isMale = isMale;
        
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + isMale;
    }
}
