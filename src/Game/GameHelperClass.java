package Game;

import Animals.Animal;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static Game.Game.filePath;
import static Game.ToolsHelperClass.*;

public class GameHelperClass implements Serializable {
    
    private Game game;
    
    public GameHelperClass(Game game){
        this.game = game;
    }
    
    public void saveGame(){
        while(true) {
            createSaveGameFolder();
            String saveGame = prompt("Spara som:") + ".ser";
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
                int choice = promptInt("""
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
                    saveGame = prompt("Spara som:") + ".ser";
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
        createSaveGameFolder();
        File[] gameFiles = new File("SavedGames").listFiles();
        if(gameFiles.length == 0){
            System.out.println("Finns inga sparade spel.");
            return;
        }
        for(File gameFile : gameFiles){
            System.out.println(counter + " " + gameFile.getName().replace(".ser", ""));
            counter++;
        }
        System.out.println((gameFiles.length + 1) + " [Backa]");
        
        int choice = promptInt("Vilket spel ska laddas?", 1, gameFiles.length + 1) - 1;
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
                System.out.println("Spelet gick inte att ladda.");
            }
        }
    }
    
    private void createSaveGameFolder() {
        try {
            Files.createDirectories(Paths.get("SavedGames"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static public void checkIfPlayerIsActive(Player player){
        if(player.getMyAnimals().size() == 0 && player.getMoney() < 300){
            player.setActive(false);
        }
    }
    
    static public void healthAndAgeLoop(Player[] players){
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int randHealthDecrease = randomNum(30, 10);
                animal.setHealth((int) animal.getHealth() - randHealthDecrease);
                animal.setAge(animal.getAge() +1);
                if(animal.getAge() == animal.getMaxAge() || animal.getHealth() <= 0){
                    player.deadAnimals.add(animal);
                    System.out.println(animal.getHealth() <= 0 ? player.getName() + " lyckades svälta ihjäl " + animal.getName() + "..." :
                            animal.getName() + " Blev för gammal och dog.");
                }
            }
            player.myAnimals.removeAll(player.deadAnimals);
        }
        animalGetSick(players);
    }
    
    static public void animalGetSick(Player[] players){
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int getSickChance = randomNum(100, 1);
                if(getSickChance <= 20){
                    animal.setSick(true);
                }
            }
        }
    }
    
    static public void highScore(Player winner) throws IOException {
        // Check if the file already exists
        int temp = 0;
        filePath = Paths.get("Highscore.txt");
        Boolean exists = Files.exists(filePath);
        // If the file does not exist then create the text file
        if (!exists) {
            // Create a list with the lines to write to the text file
            String lineToWrite = "";
            
            // Write to the text file (or replace the file if it already exists)
            Files.write(filePath, Collections.singleton(lineToWrite), StandardCharsets.UTF_8);
        }
        else{
            // Read the contents of a text file
            String contentFromFile = Files.readString(
                    filePath, StandardCharsets.UTF_8);
            
            List<String> contentAsList = new ArrayList<String>(Arrays.asList(
                    contentFromFile.replace("\r", "").split("\n")
            ));
            for (String line : contentAsList) {
                try {
                    temp = Integer.parseInt(line);
                } catch (Exception e) {
                
                }
            }
        }
        if (winner.getMoney() >= temp) {
            int highScore = winner.getMoney();
            
            // Creates line to write to the text file
            String linesToWrite = winner.getName() + " " + highScore;
            
            // Write to the text file (or replace the file if it already exists)
            Files.write(filePath, Collections.singleton(linesToWrite), StandardCharsets.UTF_8);
        }
    }
    
    public static void winner(Player[] players) throws IOException {
        
        ArrayList<Player> winners = new ArrayList<>();
        Collections.addAll(winners, players);
        winners.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.valueOf(o2.getMoney()).compareTo(o1.getMoney());
            }
        });
        
        int counter = 1;
        for(Player player : winners){
            System.out.println("På plats nr. " + counter + " " + player.getName() + " med " + player.getMoney() + " Kr.\n");
            counter++;
        }
        highScore(winners.get(0));
    }
}
