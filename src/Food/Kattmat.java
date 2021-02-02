package Food;

public class Kattmat extends Food{
    
    public Kattmat(){
        setPrice(12);
        setEatenBy("Katt");
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
