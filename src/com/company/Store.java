package com.company;

import java.util.ArrayList;

public class Store {
    
    
    ArrayList<Animal> storeList = new ArrayList<>();
    
    
    public Store(){
        storeList = createCows();
        storeList = createCats();
        storeList = createChickens();
        storeList = createSheep();
        storeList = createPigs();
        for(Animal animal : storeList){             // For testing the store..
            System.out.println(animal.getClass().getSimpleName());
        }
    }
    public ArrayList<Animal> createCows(){
        for(int i = 0; i < 6; i++){
            Cow cow = new Cow();
            storeList.add(cow);
        }
        return storeList;
    }
    public ArrayList<Animal> createCats() {
        for (int i = 0; i < 6; i++) {
            Cat cat = new Cat();
            storeList.add(cat);
        }
        return storeList;
    }
    public ArrayList<Animal> createSheep() {
        for (int i = 0; i < 6; i++) {
            Sheep sheep = new Sheep();
            storeList.add(sheep);
        }
        return storeList;
    }
    public ArrayList<Animal> createPigs() {
        for (int i = 0; i < 6; i++) {
            Pig pig = new Pig();
            storeList.add(pig);
        }
        return storeList;
    }
    public ArrayList<Animal> createChickens() {
        for (int i = 0; i < 6; i++) {
            Chicken chicken = new Chicken();
            storeList.add(chicken);
        }
        return storeList;
    }
    
    
}
