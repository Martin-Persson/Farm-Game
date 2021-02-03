package Animals;


import Food.Foder;


public class Pig extends Animal {
    
    
    public Pig(String gender, String name){
        super(gender, name);
        setPrice(500);
        setMaxAge(8);
        setEatenFood(new Foder());
        setVetCost(100);
    }

    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Pris:" + getPrice() + " Kön: " + Gender.values();
    }
}
