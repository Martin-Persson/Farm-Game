package Food;

import java.io.Serializable;

public abstract class Food implements Serializable {
    private int price;
    private int amountOfFood = 0;
    private String eatenBy;
    
    public int getPrice(){
        return this.price;
    };
    
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
