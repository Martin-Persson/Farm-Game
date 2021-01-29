package Game;

import Animals.Animal;

import java.util.*;

public class Game {
    
    static Store store = new Store();
    public Game(){
        Scanner input = new Scanner(System.in);
        
        int menuChoice = 0;
        int rounds;
        int players;
        
        Player[] playerNames;
        System.out.println("Välkommen till avelbonanza 3000!\n");
        while(!(menuChoice > 0 && menuChoice < 4)){
            menuChoice = HelperClass.promptInt("""
                [1] Starta nytt spel
                [2] Highscore
                [3] Avsluta""", 1, 3);
        }
        switch(menuChoice){
            case 1:
                rounds = numberOfRounds();
                players = players();
                playerNames = creatingPlayers(players);
                startGame(rounds, playerNames);
                break;
            case 2:
                highScore();
                break;
            case 3:
                System.exit(0);
        }
    }
    public static int numberOfRounds(){
        
        Scanner input = new Scanner(System.in);
        
        return HelperClass.promptInt("\nHur många rundor ska spelet vara (mellan 5-30) ?"
                ,5, 30);
    }
    
    public static int players(){
        // Method for selecting number of players for the game.
        return HelperClass.promptInt("\nVälj antal spelare 1-4:", 1, 4);
        
    }
    
    public static Player[] creatingPlayers(int numberOfPlayers){
        Scanner input = new Scanner(System.in);
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("\nVar vänlig att skriv in namn på spelare " + (i + 1));
            players[i] = new Player(input.nextLine());
        }
        System.out.println("\n".repeat(50));
        return players;
    }
    
    public static void startGame(int rounds, Player[] players){
        Scanner input = new Scanner(System.in);
        
        int menuChoice = 0;
        int currentRound = 1;
        int currentPlayer = 1;
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
                    HelperClass.mainMenu();
                    
                    menuChoice = HelperClass.promptInt("\nVad vill du göra denna rundan?", 1, 6);
                    HelperClass.clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal(store);
                        case 4 -> players[(currentPlayer - 1)].breedAnimal();
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> System.exit(0);
                    }
                    if(!(players[currentPlayer -1].getMadeMove())){
                        currentPlayer--;
                        continue;
                    }
                }while(!(menuChoice < 7 && menuChoice > 0));
                HelperClass.clear();
                currentPlayer++;
            }
            currentPlayer = 1;
            healthAndAgeLoop(players);
            currentRound++;
            //TODO method for checking remaining players
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
            }
        }
        }
        
    }
    
    public static void printDeadAnimals(Player player){
        for(Animal animal : player.deadAnimals){
            System.out.println("Detta djuret har tyvärr dött:");
            System.out.println(animal.getName() + " som var en " + HelperClass.translateAnimals(animal.getClass().getSimpleName()));
            player.myAnimals.removeAll(player.deadAnimals);
        }
    }
    
    public static void sellAllAnimals(Player[] players){
            for(Player player : players){
                for(Animal animal : player.myAnimals){
                    int sellPrice = (int) (animal.getPrice()
                            * animal.getHealth() / 100);
                    player.money += sellPrice;
                }
            
        }
        
    }
    
    public static void winner(Player[] players){
            
            ArrayList<Player> winners = new ArrayList<>();
            Collections.addAll(winners, players);
        winners.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.valueOf(o2.getMoney()).compareTo(o1.getMoney());
            }
        });
        System.out.println(winners.get(0).name + "Vann med " + winners.get(0).getMoney() + " Kr.");
        
        
        }
        
    public static void highScore(){
    
    }
    

}
