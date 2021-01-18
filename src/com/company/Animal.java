package com.company;

import java.util.Random;

public abstract class Animal {
    public Animal(){
    
    }
    private String name;
    private boolean isMale;
    
    public int getPrice() {
        return price;
    }
    
    private int health = 100; //double?
    //private Food food1, food2, food3;
    private int weight;
    private int age;
    private int price;
    
    public void feed(){
    
    }
    public void breed(){
    
    }
    public void getSick(){
        //VG - metod
    }
    public static boolean gender(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public abstract String toString();
}
