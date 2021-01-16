package com.company;

import java.util.Scanner;

public class Player {
    private String name;
    private int money;
    
    public Player(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void buyAnimals(){
        int menuChoice = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Välkommen till Jöns defekta djur!\n");
        System.out.println("Vad för slags djur får det lov att vara?\n");
        while(!(menuChoice > 0 && menuChoice < 7)){
            System.out.println("""
                [1] Ko
                [2] Gris
                [3] Får
                [4] Kyckling
                [5] Katt
                [6] Avsluta""");
            try{
                menuChoice = Integer.parseInt(input.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Välj en djursort.");
            }
            switch(menuChoice){
                case 1:
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
        // Print all available animals in the store
        
    }
    public void sellAnimals(){
        
    }
    public void buyFood(){
        
    }
    public void breed(){
    
    }
    public void feed(){
    
    }
}
