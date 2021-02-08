package Game;

import Animals.*;
import Food.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import static Game.PlayerHelperClass.*;
import static Game.ToolsHelperClass.*;

public class Store implements Serializable {
    ArrayList<Animal> animalList = new ArrayList<>(Arrays.asList(new Cow("female", ""),
            new Cat("female", ""),
            new Chicken("female", ""),
            new Pig("female", ""),
            new Sheep("female", "")));
    ArrayList<Food> foodList = new ArrayList<>(Arrays.asList(new Ensilage(), new Kattmat(),
            new Korn(), new Foder()));
    Game game;
    private boolean running = true;
    private int menuChoice;
    
    public Store(Game game){
        this.game = game;
    }
    
    public void buyAnimals(Player player) {
        clear();
        System.out.println("Välkommen till Jöns defekta djur!\n");
        running = true;
        while (running) {
            buyAnimalMenu(this, player);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            menuChoice = promptInt("-= Vilket djur vill du köpa? =-", 1, 6);
            animalToBuy(player, menuChoice);
        }
    }
    
    public void sellAnimals(Player player) {
        int animalToSell;
        int sellPrice;
        player.setMadeMove(false);
        clear();
        if (player.myAnimals.size() == 0) {
            System.out.println("Du äger inga djur.");
            prompt("Tryck Enter för att fortsätta...");
            return;
        }
        running = true;
        while (running) {
            breedFeedSellPrint(player);
            System.out.println(player.getMadeMove() ? " Avsluta rundan" : " Backa");
            animalToSell = promptInt("\nAnge vilket djur du vill sälja: ", 1, player.myAnimals.size()+1);
            if(animalToSell == player.myAnimals.size() + 1){
                if(player.getMadeMove()){
                    running = false;
                    break;
                }
                else{
                    return;
                }
            }
            sellPrice = getSellPrice(player, animalToSell);
            player.money += sellPrice;
            System.out.printf("Ditt djur är nu sålt och du fick %d kr för det.\n", sellPrice);
            player.myAnimals.remove(animalToSell - 1);
            player.setMadeMove(true);
            if(player.myAnimals.size() == 0){
                System.out.println("Du har inte fler djur.");
                prompt("Tryck Enter för att avsluta rundan...");
                return;
            }
        }
    }
    
    public void buyFood(Player player) {
        clear();
        System.out.println("Välkommen till Jöns begagnade mat!\n");
        running = true;
        while (running) {
            buyFoodMenu(player, this);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            
            menuChoice = promptInt("-= Vilken sorts mat vill du köpa? =-", 1, 5);
            
            int amount;
            switch (menuChoice) {
                case 1, 2, 3, 4 -> {
                    amount = promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(menuChoice - 1).getPrice() * amount) {
                        checkIfPlayerOwnsFood(player, amount, foodList.get(menuChoice - 1));
                        System.out.println("Du har köpt " + foodList.get(menuChoice - 1).toString().toLowerCase()
                                + ", vilket kostade dig " + foodList.get(menuChoice - 1).getPrice() * amount + "Kr.\n");
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 5 -> {
                    if (player.getMadeMove()) {
                        running = false;
                    } else {
                        return;
                    }
                }
            }
        }
    }
    
    public String genderOfAnimal() {
        int choice = promptInt("""
                Vilket kön önskas?
                [1] Hane
                [2] Hona""", 1, 2);
        
        return choice == 1 ? "MALE" : "FEMALE" ;
    }
    
    public void animalToBuy(Player player, int menuChoice){
        switch (menuChoice) {
            case 1 ->{
                if (player.money >= animalList.get(0).getPrice()) {
                    player.myAnimals.add(new Cow(genderOfAnimal(), player.namingAnimal()));
                    payAnimal(player, this);
                    player.setMadeMove(true);
                    clear();
                    System.out.println("Grattis till din nya Ko!");
                } else {
                    System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                }}
            case 2 ->{
                if (player.money >= animalList.get(1).getPrice()) {
                    player.myAnimals.add(new Cat(genderOfAnimal(), player.namingAnimal()));
                    payAnimal(player, this);
                    player.setMadeMove(true);
                    clear();
                    System.out.println("Grattis till din nya Katt!");
                } else {
                    System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                }}
            case 3 ->{
                if (player.money >= animalList.get(2).getPrice()) {
                    player.myAnimals.add(new Chicken(genderOfAnimal(), player.namingAnimal()));
                    payAnimal(player, this);
                    player.setMadeMove(true);
                    clear();
                    System.out.println("Grattis till din nya Kyckling!");
                } else {
                    System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                }}
            case 4 ->{
                if (player.money >= animalList.get(3).getPrice()) {
                    player.myAnimals.add(new Pig(genderOfAnimal(), player.namingAnimal()));
                    payAnimal(player, this);
                    player.setMadeMove(true);
                    clear();
                    System.out.println("Grattis till din nya Gris!");
                } else {
                    System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                }}
            case 5 ->{
                if (player.money >= animalList.get(4).getPrice()) {
                    player.myAnimals.add(new Sheep(genderOfAnimal(), player.namingAnimal()));
                    payAnimal(player, this);
                    player.setMadeMove(true);
                    clear();
                    System.out.println("Grattis till dit nya Får!");
                } else {
                    System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                }}
            case 6 -> {
                if (player.getMadeMove()) {
                    running = false;
                }
            }
        }
    }
    
    public int getSellPrice(Player player, int animalToSell){
        return (int) (player.myAnimals.get(animalToSell - 1).getPrice()
                * (player.myAnimals.get(animalToSell - 1).getHealth() / 100)
                * player.myAnimals.get(animalToSell - 1).sellAgeModifier() / 100 );
    }
    
}

