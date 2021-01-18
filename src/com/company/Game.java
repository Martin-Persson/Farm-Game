package com.company;

import java.util.Scanner;

public class Game {
    
    public static void newGame(){
        Scanner input = new Scanner(System.in);
        int menuChoice = 0;
        int rounds;
        int players;
        Player[] playerNames;
        System.out.println("Välkommen till avenbonanza 3000!");
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
                System.out.printf("\nNu är det %ss tur", players[(currentPlayer -1)].getName());
                System.out.printf("\nRunda nr %d av %d", currentRound, rounds);
                System.out.println("\nVad vill du göra denna rundan?\n");
                do{
                System.out.println("""
                        [1] - Köpa djur
                        [2] - Köpa mat
                        [3] - Mata
                        [4] - Para
                        [5] - Sälja
                        [6] - Avsluta och spara spelet""");
                
                    menuChoice = 0;
                    try{
                        menuChoice = Integer.parseInt(input.nextLine());
                    }
                    catch(Exception e){
                        System.out.println("Nu blev det galet");
                    }
                    switch(menuChoice){
                        case 1:
                            players[(currentPlayer -1)].buyAnimals();
                            //Köpa djur - tills pengarna tar slut
                            break;
                        case 2:
                            players[(currentPlayer -1)].buyFood();
                            //köpa mat - tillls pengarna tar slut
                            break;
                        case 3:
                            players[(currentPlayer -1)].feed();
                            // Feed - hur mycket som helst
                            break;
                        case 4:
                            players[(currentPlayer -1)].breed();
                            // Breed - bara ett försök
                            break;
                        case 5:
                            players[(currentPlayer -1)].sellAnimals();
                            //Sell animals
                            break;
                        case 6:
                            System.exit(0);
                            // Spara och avsluta
                            break;
                    }
                }while(!(menuChoice > 0 && menuChoice < 7));
                
                currentPlayer++;
            }
            currentPlayer = 1;
            
            currentRound++;
        }
    }
}
