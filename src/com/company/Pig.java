package com.company;

public class Pig extends Animal{
    boolean isMale;
    int price = 800;
    
    public Pig(boolean isMale) {
        this.isMale = isMale;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + isMale;
    }
}
