package com.company;

public class Cat extends Animal{
    String name;
    boolean isMale;
    int price = 300;
    public Cat(boolean isMale){
        this.isMale = isMale;
        
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + isMale;
    }
}
