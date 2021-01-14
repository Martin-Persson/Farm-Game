package com.company;

public abstract class Animal {
    private String name;
    private boolean isMale;
    private int health = 100; //double?
    private Food food1, food2, food3;
    private int weight;
    private int age;
    
    public int feed(){
    
    }
    public Animal breed(){
    
    }
    /*public int getSick(){
     VG - metod
    }*/
    public String toString(){
        return " Name: " + name + ", Health: " + health + ", age: " + age;
    }
}
