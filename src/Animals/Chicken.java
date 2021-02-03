package Animals;

import Food.Korn;

public class Chicken extends Animal {
    
    
    public Chicken(String gender, String name){
        super(gender, name);
        setPrice(400);
        setMaxAge(10);
        setEatenFood(new Korn());
        setVetCost(75);
    }

    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + getPrice() + " Kön: " + Gender.values();
    }
}
