package Animals;

import Food.Korn;

public class Chicken extends Animal {
    
    
    public Chicken(String gender, String name){
        super(gender, name);
        price = 350;
        setKorn(true);
        MAX_AGE = 10;
        eatenFood = new Korn();
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
