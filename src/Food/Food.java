package Food;

import Animals.Animal;

import java.io.Serializable;

public abstract class Food implements Serializable {
    int price;
    int amountOfFood = 0;
    String eatenBy;
    
    public abstract int getPrice();
    
    public String getEatenBy() {
        return eatenBy;
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
