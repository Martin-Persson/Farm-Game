package Food;

import Animals.Chicken;

public class Korn extends Food{
    public Korn() {
        price = 15;
        eatenBy = "Kyckling";
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
