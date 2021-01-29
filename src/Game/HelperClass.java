package Game;
import Food.*;
import java.util.HashMap;
import java.util.Scanner;


public class HelperClass {
    
    private static Scanner input = new Scanner(System.in);
    
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
    
    public static void buyFoodMenu(Player player){
        System.out.println("    |   Sort    |     Pris    |    Djur");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ensilage  |   240kr     |     Nötkreatur
                    [2] | Kattmat   |   190kr     |     Katter
                    [3] | Korn      |   110kr     |     Fjäderfä
                    [4] | Foder     |   180kr     |     Grisar - Får
                    """);
        if(player.getMadeMove()){
            System.out.println("[5] | Avsluta runda");
        }
        else{
            System.out.println("[5] | Backa");
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
    
    public static void checkIfFoodExists(Player player, int amount, Food food) {
        
        if(player.myFood.size() == 0){
            player.myFood.add(food);
            player.myFood.get(player.myFood.size()-1).setAmountOfFood(+amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
        }
        else if (player.myFood.contains(food)) {
            int ownedFood = food.getAmountOfFood();
            food.setAmountOfFood(ownedFood += amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
        } else {
            player.myFood.add(food);
            player.myFood.get(player.myFood.size() - 1).setAmountOfFood(+amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
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
    
    static public int promptInt(String question, int min, int max){
        var num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
        }
        catch(Exception ignore){}
        // if illegal choice show the prompt again (recursion)
        // otherwise return the choice
        return num < min || num > max ?
                promptInt(question, min, max) : num;
    }
    
    static public String prompt(String question){
        // clear() ? maybe we want a clear before each prompt
        System.out.println(question);
        return input.nextLine();
    }
}
