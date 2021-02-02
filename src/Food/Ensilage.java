package Food;

public class Ensilage extends Food{
    public Ensilage() {
        setPrice(40);
        setEatenBy("Ko");
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
