package Animals;

import Animals.Animal;
import Animals.Gender;

public class Cat extends Animal {
    
    
    public Cat(String gender, String name){
        super(gender, name);
        price = 300;
        setKattmat(true);
        MAX_AGE = 15;
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}

