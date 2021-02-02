package Food;

public class Kattmat extends Food{
    
    public Kattmat(){
        price = 12;
        eatenBy = "Katt";
    }
    
    @Override
    public int getPrice() {
        return price;
        
    }
}
