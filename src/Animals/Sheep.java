package Animals;

import Food.Foder;

public class Sheep extends Animal {
    
    public Sheep(String gender, String name){
        super(gender, name);
        setPrice(600);
        setMaxAge(7);
        setEatenFood(new Foder());
        setVetCost(125);
    }
}
