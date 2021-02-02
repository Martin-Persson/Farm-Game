package Animals;

import Food.Foder;
import Food.Kattmat;

public class Sheep extends Animal {
    
    
    public Sheep(String gender, String name){
        super(gender, name);
        setPrice(600);
        setMaxAge(7);
        setEatenFood(new Foder());
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + getPrice() + " Kön: " + Gender.values();
    }
}
