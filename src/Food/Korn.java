package Food;


public class Korn extends Food{
    public Korn() {
        setPrice(15);
        setEatenBy("Kyckling");
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
