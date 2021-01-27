package Game;

import Animals.Animal;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    
    static Store store = new Store();
    public Game(){
        Scanner input = new Scanner(System.in);
        
        int menuChoice = 0;
        int rounds;
        int players;
        Player[] playerNames;
        System.out.println("Välkommen till avelbonanza 3000!");
        while(!(menuChoice > 0 && menuChoice < 4)){
            System.out.println("""
                [1] Starta nytt spel
                [2] Highscore
                [3] Avsluta""");
            try {
                menuChoice = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Gör ett val mellan 1-3");
            }
        }
        switch(menuChoice){
            case 1:
                rounds = numberOfRounds();
                players = players();
                playerNames = creatingPlayers(players);
                startGame(rounds, playerNames);
                break;
            case 2:
                //Show highscore
                break;
            case 3:
                System.exit(0);
        }
    }
    public static int numberOfRounds(){
        // Method for selecting number of rounds for the game with try/catch
        Scanner input = new Scanner(System.in);
        int numberOfRounds = 0;
        // A do -while loop that continues until chosen number of rounds is between 5 and 30
        do {
            System.out.println("\nHur många rundor ska spelet vara (mellan 5-30) ?");
            try {
                numberOfRounds = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Inget giltigt nummer, försök igen.");
            }
        }while(!(numberOfRounds > 4 && numberOfRounds < 31));
        System.out.printf("Du valde %d rundor.", numberOfRounds);
        return numberOfRounds;
    }
    
    public static int players(){
        // Method for selecting number of players for the game.
        Scanner input = new Scanner(System.in);
        int numberOfPlayers = 0;
        // do -while loop for numbers of players
        do{
            System.out.println("\nVälj antal spelare 1-4:");
            try{
                numberOfPlayers = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Välj mellan 1-4:");
            }
            
        }while(!(numberOfPlayers >0 && numberOfPlayers < 5));
        System.out.printf("Du valde %d spelare", numberOfPlayers);
        return numberOfPlayers;
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
                System.out.printf("-".repeat(8) + "=".repeat(4) + " Runda %d av %d " + "=".repeat(4) +
                        "-".repeat(8), currentRound, rounds);
                System.out.printf("\nNu är det %ss tur\n", players[(currentPlayer -1)].getName());
                players[(currentPlayer -1)].printInventory();
                System.out.println("\nVad vill du göra denna rundan?\n");
                do{
                    players[(currentPlayer - 1)].setMadeMove(false);
                    HelperClass.mainMenu();
                    menuChoice = 0;
                    try{
                        menuChoice = Integer.parseInt(input.nextLine());
                    }
                    catch(Exception e){
                        System.out.println("Nu blev det galet");
                    }
                    HelperClass.clear();
                    switch (menuChoice) {
                        case 1 -> store.buyAnimals(players[(currentPlayer - 1)]);
                        case 2 -> store.buyFood(players[(currentPlayer - 1)]);
                        case 3 -> players[(currentPlayer - 1)].feedAnimal(store);
                        case 4 -> players[(currentPlayer - 1)].breedAnimal();
                        case 5 -> store.sellAnimals(players[(currentPlayer - 1)]);
                        case 6 -> System.exit(0); //TODO Start working on other methods
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
    }
    
    public static void healthAndAgeLoop(Player[] players){
        for(Player player : players){
        for(Animal animal : player.getMyAnimals()){
            animal.setHealth((int) animal.getHealth() - 10);
            animal.setAge(animal.getAge() + 1);
        }
        }
    }
    

    
    
}