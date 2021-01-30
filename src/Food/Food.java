package Food;

public abstract class Food {
    int price;
    int amountOfFood = 0;
    boolean ensilage = false;
    boolean korn = false;
    boolean kattmat = false;
    boolean foder = false;
    
    public abstract int getPrice();
    
    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }
    
    public int getAmountOfFood() {
        return amountOfFood;
    }
    
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
