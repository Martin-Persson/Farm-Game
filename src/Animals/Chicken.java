package Animals;

import Animals.Animal;
import Animals.Gender;

public class Chicken extends Animal {
    
    
    public Chicken(String name, String gender){
        super(name, gender);
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
