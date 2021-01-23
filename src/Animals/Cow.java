package Animals;

import Animals.Animal;

//TODO add price in animal
public class Cow extends Animal {
    
    
    public Cow(String name, String gender){
        super(name, gender);
        price = 1000;
    }
    public int getPrice() {
        return price;
    }
    
    //  @Override
    // public String toString() {
    //   return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    //}
}

