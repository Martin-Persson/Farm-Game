package Food;

public class Kattmat extends Food{
    
    public Kattmat(){
        price = 12;
        kattmat = true;
    }
    
    @Override
    public int getPrice() {
        return price;
        
    }
}
