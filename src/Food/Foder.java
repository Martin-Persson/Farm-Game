package Food;

public class Foder extends Food{
    public Foder() {
        price = 20;
        eatenBy = "Gris, Får";
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
