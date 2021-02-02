package Food;

public class Ensilage extends Food{
    public Ensilage() {
        price = 40;
        eatenBy = "Ko";
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
