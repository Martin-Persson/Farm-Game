package Animals;

import Food.Ensilage;



public class Cow extends Animal {
    
    public Cow(String gender, String name){
        super(gender, name);
        setPrice(800);
        setEatenFood(new Ensilage());
        setMaxAge(9);
        
    }
    public int getPrice() {
        return price;
    }
    
   
}

