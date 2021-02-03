package Game;
import Animals.Animal;
import Food.*;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


class HelperClass implements Serializable {
    private Game game;
    private static final Scanner input = new Scanner(System.in);
    public HelperClass(Game game){
        this.game = game;
    }
    
    public static void buyAnimalMenu(Store store, Player player){
        int counter = 1;
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.printf("%7s%13s%8s%8s\n", "Typ", "Max ålder", "Pris", "Äter");
        System.out.println("-".repeat(40));
        for (Animal animal : store.animalList) {
                System.out.printf("[%d] %-10s%-10s%s%-4s%s\n",
                        counter, HelperClass.translateAnimals(animal.getClass().getSimpleName())
                        , animal.getMaxAge()+ "år", animal.getPrice(), "Kr", animal.getEatenFood());
            counter++;
        }
        if(player.getMadeMove()){
            System.out.println("[6] Avsluta runda");
        }
        else{
            System.out.println("[6] Backa");
        }
        
    }
    
    public static void buyFoodMenu(Player player, Store store){
        int counter = 1;
        System.out.println("Detta är maten vi har att erbjuda:\n");
        System.out.printf("%8s%10s%11s\n", "Typ", "Pris", "Äts av");
        System.out.println("-".repeat(30));
        for (Food food : store.foodList) {
            System.out.printf("[%d] %-11s%-8s%s\n", counter, food.getClass().getSimpleName(), food.getPrice() + " Kr", food.getEatenBy());
            counter++;
        }
        if(player.getMadeMove()){
            System.out.printf("[%d] Avsluta runda\n", counter);
        }
        else{
            System.out.printf("[%d] Backa\n", counter);
        }
    
    }
    
    public static void mainMenu(){
        System.out.println("""
                            [1] - Köpa djur
                            [2] - Köpa mat
                            [3] - Mata
                            [4] - Para
                            [5] - Sälja
                            [6] - Hoppa över rundan
                            [7] - Avsluta och spara spelet""");
    }
    
    public static void clear(){
        System.out.println("\n".repeat(40));
    }
    
    public static void checkIfPlayerOwnsFood(Player player, int amount, Food food) {
        
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
    
    static public String translateAnimals(String animal){
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
    
    static public int randomNum(int max, int min){
        Random rand = new Random();
        return rand.nextInt(((max + 1) - min) + min );
    }
    
    static public boolean randomBoolean(){
        Random rand = new Random();
        return rand.nextBoolean();
    }
    
    public void saveGame(){
        while(true) {
            String saveGame = HelperClass.prompt("Spara som:") + ".ser";
            if (!Files.exists(Paths.get("SavedGames/" + saveGame))) {
                boolean save = Serializer.serialize("SavedGames/" + saveGame, game);
                
                if(save){
                    System.out.println("Game saved.");
                break;
                }
                else{
                    System.out.println("Det gick inte att spara spelet");
                }
                
            } else {
                System.out.println("Det finns redan en fil med det namnet.");
                int choice = HelperClass.promptInt("""
                        Vill du skriva över den gamla filen?
                        [1] Skriv över
                        [2] Döp om filen
                        [3] Avsluta spelet utan att spara""", 1, 3);
                if (choice == 1) {
                    Serializer.serialize("SavedGames/" + saveGame, game);
                    System.out.println("Game saved.");
                    break;
                } else if (choice == 2) {
                    System.out.println("Välj ett annat namn på filen");
                    saveGame = HelperClass.prompt("Spara som:") + ".ser";
                    Serializer.serialize("SavedGames/" + saveGame, game);
                    System.out.println("Game saved.");
                    break;
                } else {
                    break;
                }
            }
        }
    }
    
    public void loadGame(){
        int counter = 1;
        System.out.println("Följande spel finna sparade:");
        File[] gameFiles = new File("SavedGames").listFiles();
        for(File gameFile : gameFiles){
            System.out.println(counter + " " + gameFile.getName().replace(".ser", ""));
            counter++;
        }
        System.out.println((gameFiles.length + 1) + " Ångra");
        
        int choice = HelperClass.promptInt("Vilket spel ska laddas?", 1, gameFiles.length + 1) - 1;
        if(choice == gameFiles.length + 1){
            return;
        }
        else{
            try{
                System.out.println("Försöker ladda " + gameFiles[choice].getName());
            game = (Game) Serializer.deserialize("SavedGames/" + gameFiles[choice].getName());
            game.startGame();
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println("Spelet gick inte att ladda.");
            }
        }
        
    }
}
