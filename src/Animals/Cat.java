package Animals;

import Food.Kattmat;

public class Cat extends Animal {
    
    
    public Cat(String gender, String name){
        super(gender, name);
        price = 300;
        setKattmat(true);
        MAX_AGE = 15;
        eatenFood = new Kattmat();
    }
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    }
}

