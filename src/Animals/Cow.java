package Animals;

import Food.Ensilage;


public class Cow extends Animal {
    
    
    
    
    public Cow(String gender, String name){
        super(gender, name);
        price = 800;
        setEnsilage(true);
        eatenFood = new Ensilage();
        MAX_AGE = 9;
        
    }
    public int getPrice() {
        return price;
    }
    
   
}

