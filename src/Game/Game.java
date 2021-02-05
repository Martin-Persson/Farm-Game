package Game;

import Animals.Animal;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static Game.GameHelperClass.*;
import static Game.PlayerHelperClass.*;
import static Game.ToolsHelperClass.*;

public class Game implements Serializable {
    
    static Path filePath = Paths.get("Highscore.txt");
    String contentFromFile;
    Store store = new Store(this);
    GameHelperClass helper = new GameHelperClass(this);
    private int menuChoice = 0;
    public int rounds;
    private Player[] players;
    public int currentRound = 1;
    public int currentPlayer = 1;
    
    public Game() throws IOException {
        buildGame();
    }
    
    public void buildGame() throws IOException {
        
        while (!(menuChoice == 5)) {
            menuChoice = promptInt("""
                    [1] Nytt spel
                    [2] Ladda spel
                    [3] Spel info
                    [4] Highscore
                    [5] Avsluta""", 1, 5);
            switch (menuChoice) {
                case 1 -> {
                    currentRound = 1;
                    currentPlayer = 1;
                    rounds = numberOfRounds();
                    int numberOfPlayers = numberOfPlayers();
                    players = creatingPlayers(numberOfPlayers);
                    startGame();
                }
                case 2 -> helper.loadGame();
                
                case 3 -> infoMenu();
                
                case 4 -> {
                    try{
                        contentFromFile = Files.readString(
                                filePath, StandardCharsets.UTF_8);
                        System.out.println("** High Score **");
                        System.out.print("  " + contentFromFile);
                        System.out.println("****************");
                        System.out.println();
                    }
                    catch(Exception e){
                        System.out.println("Finns inget highscore ännu");
                    }
                }
                case 5 -> System.exit(0);
            }
        }
    }
    
    public int numberOfRounds(){
        
        return promptInt("\nHur många rundor ska spelet vara (mellan 5-30) ?"
                ,5, 30);
    }
    
    public int numberOfPlayers(){
        return promptInt("\nVälj antal spelare 1-4:", 1, 4);
    }
    
    public Player[] creatingPlayers(int numberOfPlayers){
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++){
            players[i] = new Player(prompt("\nVar vänlig att skriv in namn på spelare " + (i + 1)));
            players[i].setGame(this);
        }
        System.out.println("\n".repeat(50));
        return players;
    }
    
    public void startGame() throws IOException {
        outerloop:
        while(currentRound <= rounds){
            while(currentPlayer <= players.length){
                checkIfPlayerIsActive(players[currentPlayer - 1]);
                if(!(players[currentPlayer - 1].isActive())){
                    if(players.length > 1){
                    System.out.println(players[currentPlayer - 1].getName() + " Har tyvärr åkt ut.");
                    }
                    currentPlayer++;
                    continue;
                }
                payVet(players[(currentPlayer - 1)]);
                printDeadAnimals(players[(currentPlayer -1)]);
                printInventory(this, players[(currentPlayer -1)]);
                do{
                    players[(currentPlayer - 1)].setMadeMove(false);
                    mainMenu();
                    menuChoice = promptInt("\nVad vill du göra denna rundan?", 1, 7);
                    clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal();
                        case 4 -> Animal.breedAnimal(players[(currentPlayer - 1)]);
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> players[currentPlayer - 1].setMadeMove(true);
                        case 7 -> {
                            helper.saveGame();
                            break outerloop;
                        }
                    }
                    if(!(players[currentPlayer -1].getMadeMove())){
                        currentPlayer--;
                        continue;
                    }
                }while(!(menuChoice < 7 && menuChoice > 0));
                clear();
                currentPlayer++;
            }
            currentPlayer = 1;
            healthAndAgeLoop(players);
            currentRound++;
        }
        if(currentRound > rounds){
        sellAllAnimals(players);
        winner(players);
        }
    }
}
