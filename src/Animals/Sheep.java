package Animals;

import Animals.Animal;
import Animals.Gender;

public class Sheep extends Animal {
    int price = 700;
    
    public Sheep(String name, String gender){
        super(name, gender);
        price = 700;
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
