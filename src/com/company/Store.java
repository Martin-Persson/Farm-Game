package com.company;

import java.util.ArrayList;

public class Store {
    ArrayList<Animal> storeList = new ArrayList<>();
    static Store store = new Store();
    
    public Store(){
        Cow cow1 = new Cow( true);
        Cow cow2 = new Cow( false);
        storeList.add(cow1);
        storeList.add(cow2);
        Cat cat1 = new Cat(true);
        Cat cat2 = new Cat(false);
        storeList.add(cat1);
        storeList.add(cat2);
        Chicken chick1 = new Chicken(true);
        Chicken chick2 = new Chicken(false);
        storeList.add(chick1);
        storeList.add(chick2);
        Sheep sheep1 = new Sheep(true);
        Sheep sheep2 = new Sheep(false);
        storeList.add(sheep1);
        storeList.add(sheep2);
        Pig pig1 = new Pig(true);
        Pig pig2 = new Pig(false);
        storeList.add(pig1);
        storeList.add(pig2);
        Cow cow3 = new Cow(true);
        storeList.add(cow3);
    }
    public void buyShowCow(){
    
    }
}
// TODO Fixa att läsa in värdet i gender, MALE - FEMALE.
// TODO skapa nya djur