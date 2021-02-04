package Game;

import Animals.Animal;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static Game.HelperClass.*;

public class Game implements Serializable {
    
    static Path filePath = Paths.get("Highscore.txt");
    String contentFromFile;
    Store store = new Store(this);
    HelperClass helper = new HelperClass(this);
    private int menuChoice = 0;
    public int rounds;
    private Player[] players;
    public int currentRound = 1;
    public int currentPlayer = 1;
    
    public Game() throws IOException {
        buildGame();
    }
    
    public void buildGame() throws IOException {
        
        System.out.println("Välkommen till avelbonanza 3000!\n");
        while (!(menuChoice == 4)) {
            menuChoice = promptInt("""
                    [1] Nytt spel
                    [2] Ladda spel
                    [3] Highscore
                    [4] Avsluta""", 1, 4);
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
                
                case 3 -> {
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
                case 4 -> System.exit(0);
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
        while(currentRound <= rounds){
            while(currentPlayer <= players.length){
                players[currentPlayer - 1].checkIfPlayerIsActive();
                if(!(players[currentPlayer - 1].isActive())){
                    System.out.println(players[currentPlayer - 1].getName() + " Har tyvärr åkt ut.");
                    currentPlayer++;
                    continue;
                }
                payVet(players[(currentPlayer - 1)]);
                printDeadAnimals(players[(currentPlayer -1)]);
                players[(currentPlayer -1)].printInventory(this);
                do{
                    players[(currentPlayer - 1)].setMadeMove(false);
                    mainMenu();
                    menuChoice = promptInt("\nVad vill du göra denna rundan?", 1, 7);
                    clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal();
                        case 4 -> players[(currentPlayer - 1)].breedAnimal();
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> players[currentPlayer - 1].setMadeMove(true);
                        case 7 -> {
                            helper.saveGame();
                            System.exit(0);
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
        sellAllAnimals(players);
        winner(players);
        
    }
    
    public void healthAndAgeLoop(Player[] players){
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int randHealthDecrease = randomNum(3, 1);
                animal.setHealth((int) animal.getHealth() - randHealthDecrease * 10);
                animal.setAge(animal.getAge() +1);
                if(animal.getAge() == animal.getMaxAge() || animal.getHealth() <= 0){
                    player.deadAnimals.add(animal);
                    System.out.println(animal.getName() + " dog.");
                }
            }
        }
        animalGetSick(players);
    }
    
    public void animalGetSick(Player[] players){
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int getSickChance = randomNum(100, 1);
                if(getSickChance <= 20){
                    animal.setSick(true);
                }
            }
        }
    }
    
    public void payVet(Player player){
        for(Animal animal : player.getMyAnimals()){
            if(animal.isSick()){
                System.out.printf("%s!\n",player.getName());
                System.out.println("Ditt djur " + animal.getName() + " Har blivit väldigt sjukt.");
                System.out.println("Du har " + player.getMoney() + "Kr:");
                int choice = promptInt("""
            Vill du försöka rädda djuret hos veterinären?
            Det skulle kosta dig""" + " " + animal.getVetCost() + "Kr\n" +
                        "[1] - Ja\n[2] - Nej", 1, 2);
                
                if(choice == 1){
                    if(player.getMoney() < animal.getVetCost()){
                        clear();
                        System.out.println("Du har tyvärr inte råd att betala.");
                        System.out.println(animal.getName() + " dog.");
                        player.deadAnimals.add(animal);
                    }
                    else{
                        player.setMoney(player.getMoney() - animal.getVetCost());
                        if(randomBoolean()){
                            player.deadAnimals.add(animal);
                            clear();
                            System.out.println(animal.getName() + " klarade sig tyvärr inte.\nVeterinären " +
                                    "kämpade hela natten utan framgång.");
                        }
                        else{
                            clear();
                            System.out.println("Hurra! " + animal.getName() + " klarade sig och är nu frisk.");
                            animal.setSick(false);
                        }
                    }
                }
                else{
                    clear();
                    System.out.println("Eftersom du är snål så dog " + animal.getName());
                    player.deadAnimals.add(animal);
                }
            }
        }
    }
    
    public void printDeadAnimals(Player player){
        if(player.deadAnimals.size() > 0){
            System.out.println("\nDina djur som dött under spelets gång:");
            System.out.println(player.deadAnimals);
            player.myAnimals.removeAll(player.deadAnimals);
            if(player.deadAnimals.size() > 10){
                System.out.println("Du kan nu titulera dig djurrikets baneman.");
            }
        }
        System.out.println();
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
            System.out.println("På plats nr. " + counter + " " + player.getName() + " med " + player.getMoney() + " Kr.\n");
            counter++;
        }
        highScore(winners.get(0));
    }
    
    public static void highScore(Player winner) throws IOException {
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
            // Calculate path to file
            // (will be saved in our current project folder
            
            
            // Create a list with the lines to write to the text file
            String linesToWrite = winner.getName() + " " + highScore;
            
            // Write to the text file (or replace the file if it already exists)
            Files.write(filePath, Collections.singleton(linesToWrite), StandardCharsets.UTF_8);
        }
    }
}
