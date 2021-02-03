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
    
    static Path filePath = Paths.get("Highscore.txt");
    String contentFromFile;
    Store store = new Store(this);
    helperClass helper = new helperClass(this);
    private int menuChoice = 0;
    private int rounds;
    private Player[] players;
    private int currentRound = 1;
    private int currentPlayer = 1;
    
    public Game() throws IOException {
        buildGame();
    }
    
    public void buildGame() throws IOException {
        
        System.out.println("Välkommen till avelbonanza 3000!\n");
        while (!(menuChoice == 4)) {
            menuChoice = helperClass.promptInt("""
                    [1] Nytt spel
                    [2] Ladda spel
                    [3] Highscore
                    [4] Avsluta""", 1, 4);
            switch (menuChoice) {
                case 1 -> {
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
        
        return helperClass.promptInt("\nHur många rundor ska spelet vara (mellan 5-30) ?"
                ,5, 30);
    }
    
    public int numberOfPlayers(){
        // Method for selecting number of players for the game.
        return helperClass.promptInt("\nVälj antal spelare 1-4:", 1, 4);
        
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
                payVet(players[(currentPlayer - 1)]);
                System.out.printf("-".repeat(10) + "=".repeat(5) + " Runda %d av %d " + "=".repeat(5) +
                        "-".repeat(10), currentRound, rounds);
                System.out.printf("\nNu är det %ss tur.\t Pengar: %dKr\n", players[(currentPlayer -1)].getName(), players[currentPlayer - 1].getMoney());
                printDeadAnimals(players[(currentPlayer -1)]);
                players[(currentPlayer -1)].printInventory();
                System.out.println("");
                
                do{
                    players[(currentPlayer - 1)].setMadeMove(false);
                    helperClass.mainMenu();
                    
                    menuChoice = helperClass.promptInt("\nVad vill du göra denna rundan?", 1, 7);
                    helperClass.clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal();
                        case 4 -> players[(currentPlayer - 1)].breedAnimal();
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> {
                            players[currentPlayer - 1].setMadeMove(true);
                            break;
                        }
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
                helperClass.clear();
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
        Random rand = new Random();
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int randHealthDecrease = rand.nextInt(4 - 1) + 1;
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
        Random rand = new Random();
        for(Player player : players){
            for(Animal animal : player.getMyAnimals()){
                int getSickChance = rand.nextInt(100 - 1) +1;
                if(getSickChance <= 20){
                    animal.setSick(true);
                }
            }
        }
    }
    
    public void payVet(Player player){
        for(Animal animal : player.getMyAnimals()){
            if(animal.isSick()){
                System.out.println("Ditt djur " + animal.getName() + " Har blivit väldigt sjukt.");
                
                int choice = helperClass.promptInt("""
            Vill du försöka rädda djuret hos veterinären?
            Det skulle kosta dig""" + " " + animal.getVetCost() + "Kr\n" +
                        "[1] - Ja\n[2] - Nej", 1, 2);
                
                if(choice == 1){
                    if(player.getMoney() < animal.getVetCost()){
                        System.out.println("Du har tyvärr inte råd att betala.");
                        player.deadAnimals.add(animal);
                    }
                    else{
                        player.setMoney(player.getMoney() - animal.getVetCost());
                        int chanceToLive = helperClass.randomNum(100, 1);
                        if(chanceToLive <= 50){
                            player.deadAnimals.add(animal);
                            helperClass.clear();
                            System.out.println(animal.getName() + " klarade sig tyvärr inte.\nVeterinären " +
                                    "kämpade hela natten utan framgång.\n");
                        }
                        else{
                            helperClass.clear();
                            System.out.println("Hurra! " + animal.getName() + " klarade sig och är nu frisk.\n");
                            animal.setSick(false);
                        }
                    }
                }
                else{
                    helperClass.clear();
                    System.out.println("Eftersom du är snål så dog " + animal.getName());
                    player.deadAnimals.add(animal);
                }
            }
        }
    }
    
    public void printDeadAnimals(Player player){
        if(player.deadAnimals.size() > 0){
            System.out.println("\nDina djur som dött under spelets gång:");
            for(Animal animal : player.deadAnimals){
                System.out.println(animal.getName() + " ---- " + helperClass.translateAnimals(animal.getClass().getSimpleName()));
                player.myAnimals.removeAll(player.deadAnimals);
            }
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
        filePath = Paths.get("Highscore.txt");
        Boolean exists = Files.exists(filePath);
        // If the file does not exist then create the text file
        if (!exists) {
            // Create a list with the lines to write to the text file
            String lineToWrite = "";
            
            // Write to the text file (or replace the file if it already exists)
            Files.write(filePath, Collections.singleton(lineToWrite), StandardCharsets.UTF_8);
            
            int temp = 0;
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
    
}
