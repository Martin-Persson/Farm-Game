package Animals;

import Food.Kattmat;

public class Cat extends Animal {
    
    public Cat(String gender, String name){
        super(gender, name);
        setPrice(300);
        setMaxAge(10);
        setEatenFood(new Kattmat());
        setVetCost(50);
    }
}

