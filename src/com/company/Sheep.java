package com.company;

public class Sheep extends Animal{
    boolean isMale;
    int price = 600;
    
    public Sheep(boolean isMale) {
        this.isMale = isMale;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + isMale;
    }
}
