package Game;

import Animals.Animal;
import Food.*;

import java.util.HashMap;


public class HelperClass {
    
    
    
    public static void buyAnimalMenu(Player player){
        
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.println("    |   Sort    |     Pris    |    Foder");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ko        |   1000kr    |     Ensilage
                    [2] | Katt      |   300kr     |     Kattmat
                    [3] | Kyckling  |   400kr     |     Frön
                    [4] | Gris      |   600kr     |     Foder
                    [5] | Får       |   700kr     |     Foder
                    """);
        if(player.getMadeMove()){
            System.out.println("[6] | Avsluta runda");
        }
        else{
            System.out.println("[6] | Backa");
        }
        
    }
    
    public static void buyFoodMeenu(Player player){
        System.out.println("    |   Sort    |     Pris    |    Djur");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ensilage  |   240kr     |     Nötkreatur
                    [2] | Kattmat   |   190kr     |     Katter
                    [3] | Korn      |   110kr     |     Fjäderfä
                    [4] | Foder     |   180kr     |     Grisar - Får
                    """);
        if(player.getMadeMove()){
            System.out.println("[5] |  Avsluta runda");
        }
        else{
            System.out.println("[5] |  Backa");
        }
    }
    
    public static void mainMenu(){
                    System.out.println("""
                            [1] - Köpa djur
                            [2] - Köpa mat
                            [3] - Mata
                            [4] - Para
                            [5] - Sälja
                            [6] - Avsluta och spara spelet""");
    }
    
    public static void clear(){
        System.out.println("\n".repeat(40));
    }
    
    public static int findingFood(Player player, String foodType){
        for(Food food : player.myFood ){
            if(food.getClass().getSimpleName().equalsIgnoreCase(foodType)){
                if(food.getAmountOfFood() > 0){
                    food.setAmountOfFood(food.getAmountOfFood()-1);
                }
                return food.getAmountOfFood();
            }
            
        }
        return 0;
    }
    
    public static void feeddjur(Store store, Player player){
        int counter = 1;
        for(Animal animal : store.animalList){
            System.out.println("[" + counter + "] " + animal.getClass().getSimpleName());
            counter++;
        }
        if(!(player.getMadeMove())){
            System.out.format("[%d] %-10s\n", counter, "Backa");
        }
        else{
            System.out.format("[%d] %-10s\n", counter, "Avsluta rundan");
        }
    }
    
    public static void checkIfFoodExists(Store store, Player player, int amount) {
        
        if(player.myFood.size() == 0){
            player.myFood.add(new Ensilage());
            player.myFood.get(player.myFood.size()-1).setAmountOfFood(+amount);
            player.money -= store.foodList.get(0).getPrice() * amount;
            player.setMadeMove(true);
            return;
        }
        else{
            for(Food food : store.foodList){
                for(Food playerFood : player.myFood){
                    if(food.getClass().getSimpleName().equals(playerFood.getClass().getSimpleName())){
                        int ownedFood = playerFood.getAmountOfFood();
                        playerFood.setAmountOfFood(ownedFood += amount);
                        player.money -= store.foodList.get(0).getPrice() * amount;
                        player.setMadeMove(true);
                    }
                }
            }
        }
    }
    
    public static String translateAnimals(String animal){
        HashMap<String, String> translation = new HashMap<>();
        translation.put("Cow", "Ko");
        translation.put("Cat", "Katt");
        translation.put("Chicken", "Kyckling");
        translation.put("Pig", "Gris");
        translation.put("Sheep", "Får");
        
        return translation.get(animal);
    }
}
