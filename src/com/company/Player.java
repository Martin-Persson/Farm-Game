package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    ArrayList<Animal> myAnimals = new ArrayList<>();
    private int menuChoice;
    private int animalToBuy;
    private String name;
    private int money = 2500;
    public Store myStore = Store.store;
    public Player(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void buyAnimals(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n".repeat(50));
        System.out.println("Välkommen till Jöns defekta djur!\n");
        while(true){
            System.out.println("Detta är djuren vi har att erbjuda:\n");
            for(int i = 0; i < myStore.storeList.size(); i++){
                System.out.printf("%d: ", (i + 1 ));
                System.out.println(myStore.storeList.get(i));
            }
            System.out.println("\n\n");
            do{
                System.out.println("""
                [1] Köp ett djur
                [2] Tillbaka""");
                try{
                    menuChoice = Integer.parseInt(input.nextLine());
                }
                catch(Exception e){
                    System.out.println("Något gick fel, försök igen");
                }
            }while(!(menuChoice > 0 && menuChoice < 3));
            switch(menuChoice){
                case 1:
                    do{
                    System.out.println("Vilket djur vill ni köpa (ange nr.)? ");
                        try{
                            animalToBuy = Integer.parseInt(input.nextLine());
                        }
                        catch(Exception e){
                            System.out.println("Något gick fel, försök igen");
                        }
                    }while(!(animalToBuy > -1 && animalToBuy <= myStore.storeList.size()));
                    break;
                case 2:
                    return;
            }
            if(this.money > myStore.storeList.get(animalToBuy - 1).getPrice()){
                myAnimals.add(myStore.storeList.get(animalToBuy -1));
                this.money -= myStore.storeList.get(animalToBuy - 1).getPrice();
            }
            else{
                System.out.println("Du har tyvärr inte tillräckligt med pengar.");
            }
            System.out.println();
            System.out.println("Djuren du äger är:");
            System.out.println(this.myAnimals);
            System.out.println();
        }
        
    }
    
    public void buyCow() {
        //TODO Försöka göra en metod som tar argumenten kön och instanceof
        // Print all available animals in the store
        System.out.println("Vi har följande djur till salu:\n");
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
