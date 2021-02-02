package Food;

public class Korn extends Food{
    public Korn() {
        price = 15;
        korn = true;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
