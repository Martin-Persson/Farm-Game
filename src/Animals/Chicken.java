package Animals;

import Animals.Animal;
import Animals.Gender;

public class Chicken extends Animal {
    
    
    public Chicken(String gender, String name){
        super(gender, name);
        price = 400;
        setKorn(true);
        MAX_AGE = 10;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
