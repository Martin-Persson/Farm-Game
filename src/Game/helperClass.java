package Game;
import Food.*;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;


class helper implements Serializable {
    private Game game;
    private static final Scanner input = new Scanner(System.in);
    public helper(Game game){
        this.game = game;
    }
    public static void buyAnimalMenu(Player player){
        
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.println("    |   Sort    |     Pris    |    Foder");
        System.out.println("-----------------------------------------");
        System.out.print("""
                    [1] | Ko        |   1000k     |     Ensilage
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
        System.out.print("""
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
    
    public void saveGame(){
        while(true) {
            String saveGame = helper.prompt("Spara som:") + ".ser";
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
                int choice = helper.promptInt("""
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
                    saveGame = helper.prompt("Spara som:") + ".ser";
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
        
        int choice = helper.promptInt("Vilket spel ska laddas?", 1, gameFiles.length + 1) - 1;
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
