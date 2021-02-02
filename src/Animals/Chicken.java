package Animals;

import Food.Kattmat;
import Food.Korn;

public class Chicken extends Animal {
    
    
    public Chicken(String gender, String name){
        super(gender, name);
        setPrice(350);
        setMaxAge(10);
        setEatenFood(new Korn());
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + getPrice() + " Kön: " + Gender.values();
    }
}
