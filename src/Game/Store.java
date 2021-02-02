package Game;

import Animals.*;
import Food.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Store implements Serializable {
    ArrayList<Animal> animalList = new ArrayList<>(Arrays.asList(new Cow("female", ""),
            new Cat("female", ""),
            new Chicken("female", ""),
            new Pig("female", ""),
            new Sheep("female", "")));
    ArrayList<Food> foodList = new ArrayList<>(Arrays.asList(new Ensilage(), new Kattmat(),
            new Korn(), new Foder()));
    Game game;
    boolean running = true;
    int menuChoice;
    int amount = 0;
    public Store(Game game){
        this.game = game;
    }
    
    public void buyAnimals(Player player) {
        
        helperClass.clear();
        System.out.println("Välkommen till Jöns defekta djur!\n");
        running = true;
        while (running) {//TODO Formatting table
            
            helperClass.buyAnimalMenu(this, player);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            menuChoice = helperClass.promptInt("-= Vilket djur vill du köpa? =-", 1, 6);
            switch (menuChoice) {
                case 1:
                    if (player.money >= animalList.get(0).getPrice()) {
                        player.myAnimals.add(new Cow(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 2:
                    if (player.money >= animalList.get(1).getPrice()) {
                        player.myAnimals.add(new Cat(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 3:
                    if (player.money >= animalList.get(2).getPrice()) {
                        player.myAnimals.add(new Chicken(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 4:
                    if (player.money >= animalList.get(3).getPrice()) {
                        player.myAnimals.add(new Pig(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 5:
                    if (player.money >= animalList.get(4).getPrice()) {
                        player.myAnimals.add(new Sheep(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 6: {
                    if (player.getMadeMove()) {
                        running = false;
                        break;
                    } else {
                        return;
                    }
                }
            }
            
        }
    }
    
    public void sellAnimals(Player player) {
        int animalToSell;
        int sellPrice;
        player.setMadeMove(false);
        helperClass.clear();
        if (player.myAnimals.size() == 0) {
            System.out.println("Du äger inga djur.");
            System.out.println("Tryck Enter för att fortsätta...");
            helperClass.prompt("");
            return;
        }
        running = true;
        while (running) {
            Scanner input = new Scanner(System.in);
            System.out.println("Vilket djur vill du sälja?");
            player.printAnimals();
            animalToSell = helperClass.promptInt("Ange vilket djur du vill sälja: ", 1, player.myAnimals.size()+1);
            if(animalToSell == player.myAnimals.size() + 1){
                if(player.getMadeMove()){
                    running = false;
                    break;
                }
                else{
                    return;
                }
            }
            sellPrice = (int) (player.myAnimals.get(animalToSell - 1).getPrice()
                    * (player.myAnimals.get(animalToSell - 1).getHealth() / 100)
                    * player.myAnimals.get(animalToSell - 1).sellAgeModifier() / 100 );
            player.money += sellPrice;
            System.out.printf("Ditt djur är nu sålt och du fick %d kr för det.\n", sellPrice);
            player.myAnimals.remove(animalToSell - 1);
            player.setMadeMove(true);
            if(player.myAnimals.size() == 0){
                System.out.println("Du har inte fler djur.");
                System.out.println("Tryck Enter för att avsluta rundan...");
                input.nextLine();
                return;
            }
        }
    }
    
    public void buyFood(Player player) {
        helperClass.clear();
        System.out.println("Välkommen till Jöns begagnade mat!\n");
        running = true;
        while (running) {
            
            helperClass.buyFoodMenu(player, this);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            
            menuChoice = helperClass.promptInt("-= Vilken sorts mat vill du köpa? =-", 1, 5);
    
            switch (menuChoice) {
                case 1 -> {
                    amount = helperClass.promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(0).getPrice() * amount) {
                        helperClass.checkIfPlayerOwnsFood(player, amount, foodList.get(0));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 2 -> {
                    amount = helperClass.promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(1).getPrice() * amount) {
                        helperClass.checkIfPlayerOwnsFood(player, amount, foodList.get(1));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 3 -> {
                    amount = helperClass.promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(2).getPrice() * amount) {
                        helperClass.checkIfPlayerOwnsFood(player, amount, foodList.get(2));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 4 -> {
                    amount = helperClass.promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(3).getPrice() * amount) {
                        helperClass.checkIfPlayerOwnsFood(player, amount, foodList.get(3));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 5 -> {
                    if (player.getMadeMove()) {
                        running = false;
                        break;
                    } else {
                        return;
                    }
                }
            }
            
        }
    }
    
    
    public String genderOfAnimal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Och vilket kön önskas (skriv hane eller hona)?");
        String gender = input.nextLine();
        if (gender.equalsIgnoreCase("HANE")) {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }
        return gender;
    }
    
    
    public void payAnimal(Player player) {
        for (Animal animal : animalList) {
            if (player.myAnimals.get(player.myAnimals.size() - 1).getClass().getSimpleName()
                    .equalsIgnoreCase(animal.getClass().getSimpleName())) {
                
                player.money -= animal.getPrice();
            } else {
                System.out.println();
            }
        }
    }
    
    
}

