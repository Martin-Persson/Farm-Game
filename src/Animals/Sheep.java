package Animals;

import Food.Foder;

public class Sheep extends Animal {
    
    
    public Sheep(String gender, String name){
        super(gender, name);
        price = 600;
        setFoder(true);
        MAX_AGE = 7;
        eatenFood = new Foder();
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}
