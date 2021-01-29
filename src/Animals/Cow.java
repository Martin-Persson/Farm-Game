package Animals;

import Animals.Animal;

//TODO add price in animal
public class Cow extends Animal {
    
    String food = "ensilage";
    public Cow(String gender, String name){
        super(gender, name);
        price = 1000;
        setEnsilage(true);
        setFood("ensilage");
        MAX_AGE = 9;
    }
    public int getPrice() {
        return price;
    }
    

    
    //  @Override
    // public String toString() {
    //   return getClass().getSimpleName() + " Pris:" + price + " Kön: " + Gender.values();
    //}
}

