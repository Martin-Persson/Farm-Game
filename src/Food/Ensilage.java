package Food;

public class Ensilage extends Food{
    public Ensilage() {
        price = 40;
        ensilage = true;
    }
    
    @Override
    public void setAmountOfFood(int amountOfFood) {
        super.setAmountOfFood(amountOfFood);
    }
    
    @Override
    public int getPrice() {
        return price;
    }
}
