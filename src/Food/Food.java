package Food;

public abstract class Food {
    int price;
    int amountOfFood = 0;
    
    
    public abstract int getPrice();
    
    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }
    
    public int getAmountOfFood() {
        return amountOfFood;
    }
}
