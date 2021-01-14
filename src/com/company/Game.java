package com.company;

import java.util.Scanner;

public class Game {
    
    public static void newGame(){
        Scanner input = new Scanner(System.in);
        int menuChoice = 0;
        int rounds;
        int players;
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
                startGame(rounds, players);
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
    
    public static void startGame(int rounds, int players){
    
    }
}
