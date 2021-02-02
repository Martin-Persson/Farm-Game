package Food;

import java.io.Serializable;

public abstract class Food implements Serializable {
    protected int price;
    protected int amountOfFood = 0;
    protected String eatenBy;
    
    public abstract int getPrice();
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getEatenBy() {
        return eatenBy;
    }
    
    public void setEatenBy(String eatenBy) {
        this.eatenBy = eatenBy;
    }
    
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
