package Food;

public class Ensilage extends Food{
    public Ensilage() {
        price = 240;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
