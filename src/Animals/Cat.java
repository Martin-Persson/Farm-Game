package Animals;

import Animals.Animal;
import Animals.Gender;

public class Cat extends Animal {
    
    
    public Cat(String name, String gender){
        super(name, gender);
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

