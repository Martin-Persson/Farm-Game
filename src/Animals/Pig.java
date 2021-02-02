package Animals;


import Food.Foder;

public class Pig extends Animal {
    
    
    public Pig(String gender, String name){
        super(gender, name);
        price = 500;
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
