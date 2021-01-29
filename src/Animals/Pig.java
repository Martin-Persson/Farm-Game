package Animals;

import Animals.Animal;
import Animals.Gender;

public class Pig extends Animal {
    
    
    public Pig(String gender, String name){
        super(gender, name);
        price = 600;
        setFoder(true);
        MAX_AGE = 7;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
