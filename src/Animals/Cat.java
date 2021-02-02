package Animals;

import Food.Kattmat;

public class Cat extends Animal {
    
    
    public Cat(String gender, String name){
        super(gender, name);
        setPrice(300);
        setMaxAge(15);
        setEatenFood(new Kattmat());
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + getPrice() + " Kön: " + Gender.values();
    }
}

