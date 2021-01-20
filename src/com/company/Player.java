package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    HashMap<Food, Integer> myFood = new HashMap<>();
    
    public String name;
    public int money = 2500;
    
    public Player(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void printInventory(){//TODO Formatting a table
        System.out.printf("Pengar =  %dkr.\n", money);
        if(myAnimals.size() > 0){
            System.out.println("Typ     |   Namn    |   Kön");
            for(Animal animal : myAnimals){
                System.out.print(animal.getClass().getSimpleName() +
                        "       " + animal.name);
                if(animal.getGender().name().equalsIgnoreCase("MALE")){
                    System.out.println("        hane");
                }
                else{
                    System.out.println("        hona");
                }
            }
        }
    }
}
