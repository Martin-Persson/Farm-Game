package Food;

public class Foder extends Food{
    public Foder() {
        setPrice(20);
        setEatenBy("Gris, Får");
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
