package Game;

import Animals.Animal;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Game implements Serializable {
    static Player champ;
    static Path filePath = Paths.get("Highscore.txt");
    String contentFromFile;
    Store store = new Store(this);
    helper helper = new helper(this);
    int menuChoice = 0;
    int rounds;
    int numberOfPlayers;
    Player[] players;
    //int menuChoice;
    int currentRound = 1;
    int currentPlayer = 1;
    
    public Game() throws IOException {
        buildGame();
    }
    
    public void buildGame() throws IOException {
        
        System.out.println("Välkommen till avelbonanza 3000!\n");
        while (!(menuChoice == 3)) {
            menuChoice = helper.promptInt("""
                    [1] Nytt spel
                    [2] Ladda spel
                    [3] Highscore
                    [4] Avsluta""", 1, 4);
            switch (menuChoice) {
                case 1 -> {
                    rounds = numberOfRounds();
                    numberOfPlayers = numberOfPlayers();
                    players = creatingPlayers(numberOfPlayers);
                    startGame();
                }
                case 2 -> helper.loadGame();
                
                case 3 -> {
                    contentFromFile = Files.readString(
                            filePath, StandardCharsets.UTF_8);
                    System.out.println(contentFromFile);
                    
                }
                case 4 -> System.exit(0);
            }
        }
    }
    public int numberOfRounds(){
        
        return helper.promptInt("\nHur många rundor ska spelet vara (mellan 5-30) ?"
                ,5, 30);
    }
    
    public int numberOfPlayers(){
        // Method for selecting number of players for the game.
        return helper.promptInt("\nVälj antal spelare 1-4:", 1, 4);
        
    }
    
    public Player[] creatingPlayers(int numberOfPlayers){
        Scanner input = new Scanner(System.in);
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("\nVar vänlig att skriv in namn på spelare " + (i + 1));
            players[i] = new Player(input.nextLine());
            players[i].setGame(this);
        }
        System.out.println("\n".repeat(50));
        return players;
    }
    
    public void startGame() throws IOException {
        while(currentRound <= rounds){
            while(currentPlayer <= players.length){
                players[currentPlayer - 1].checkIfPlayerIsActive();
                if(!(players[currentPlayer - 1].isActive())){
                    System.out.println(players[currentPlayer - 1].getName() + " Har tyvärr åkt ut.");
                    currentPlayer++;
                    continue;
                }
                
                System.out.printf("-".repeat(8) + "=".repeat(4) + " Runda %d av %d " + "=".repeat(4) +
                        "-".repeat(8), currentRound, rounds);
                System.out.printf("\nNu är det %ss tur\n", players[(currentPlayer -1)].getName());
                printDeadAnimals(players[(currentPlayer -1)]);
                players[(currentPlayer -1)].printInventory();
                System.out.println("");
                do{
                    players[(currentPlayer - 1)].setMadeMove(false);
                    helper.mainMenu();
                    
                    menuChoice = helper.promptInt("\nVad vill du göra denna rundan?", 1, 6);
                    helper.clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal();
                        case 4 -> players[(currentPlayer - 1)].breedAnimal();
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> {
                            helper.saveGame();
                            System.exit(0);
                        }
                    }
                    if(!(players[currentPlayer -1].getMadeMove())){
                        currentPlayer--;
                        continue;
                    }
                }while(!(menuChoice < 7 && menuChoice > 0));
                helper.clear();
                currentPlayer++;
            }
            currentPlayer = 1;
            healthAndAgeLoop(players);
            currentRound++;
            
        }
        sellAllAnimals(players);
        winner(players);
        
    }
    
    public static void healthAndAgeLoop(Player[] players){
        Random rand = new Random();
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int randHealthDecrease = rand.nextInt(4 - 1) + 1;
                animal.setHealth((int) animal.getHealth() - randHealthDecrease * 10);
                animal.setAge(animal.getAge() +1);
                if(animal.getAge() == animal.getMAX_AGE() || animal.getHealth() <= 0){
                    player.deadAnimals.add(animal);
                    System.out.println(animal.getName() + " dog.");
                }
            }
        }
        
    }
    
    public void printDeadAnimals(Player player) {
        if (player.deadAnimals.size() > 0) {
            System.out.println("\nDina djur som dött under spelets gång:");
            for (Animal animal : player.deadAnimals) {
                System.out.println(animal.getName() + " var en " + helper.translateAnimals(animal.getClass().getSimpleName()));
                player.myAnimals.removeAll(player.deadAnimals);
            }
            if (player.deadAnimals.size() > 10) {
                System.out.println("Du kan nu titulera dig djurrikets baneman.");
            }
        }
    }
    
    public static void sellAllAnimals(Player[] players){
        for(Player player : players){
            for(Animal animal : player.myAnimals){
                int sellPrice = (int) (animal.getPrice()
                        * animal.getHealth() / 100)
                        * animal.sellAgeModifier() / 100;
                player.money += sellPrice;
            }
            
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
            System.out.println("På plats nr. " + counter + " " + player.name + " med " + player.getMoney() + " Kr.\n");
            counter++;
        }
        highScore(winners.get(0));
    }
    
    public static void highScore(Player winner) throws IOException {
        if(champ == null){
            champ = new Player("");
            champ.setMoney(0);
        }
        else if(winner.getMoney() >= champ.getMoney()){
            champ = winner;
            // Calculate path to file
            // (will be saved in our current project folder
            filePath = Paths.get("Highscore.txt");
            
            // Create a list with the lines to write to the text file
            String linesToWrite = champ.name + " " + champ.money;
            
            // Write to the text file (or replace the file if it already exists)
            Files.write(filePath, Collections.singleton(linesToWrite), StandardCharsets.UTF_8);
        }
    }
    
    
    
}
