package Game;

import Animals.*;
import Food.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import static Game.HelperClass.*;

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
            switch (menuChoice) {
                case 1:
                    if (player.money >= animalList.get(0).getPrice()) {
                        player.myAnimals.add(new Cow(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                        clear();
                        System.out.println("Grattis till din nya Ko!");
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 2:
                    if (player.money >= animalList.get(1).getPrice()) {
                        player.myAnimals.add(new Cat(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                        clear();
                        System.out.println("Grattis till din nya Katt!");
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 3:
                    if (player.money >= animalList.get(2).getPrice()) {
                        player.myAnimals.add(new Chicken(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                        clear();
                        System.out.println("Grattis till din nya Kyckling!");
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 4:
                    if (player.money >= animalList.get(3).getPrice()) {
                        player.myAnimals.add(new Pig(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                        clear();
                        System.out.println("Grattis till din nya Gris!");
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 5:
                    if (player.money >= animalList.get(4).getPrice()) {
                        player.myAnimals.add(new Sheep(genderOfAnimal(), player.namingAnimal()));
                        payAnimal(player);
                        player.setMadeMove(true);
                        clear();
                        System.out.println("Grattis till dit nya Får!");
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
        clear();
        if (player.myAnimals.size() == 0) {
            System.out.println("Du äger inga djur.");
            System.out.println("Tryck Enter för att fortsätta...");
            prompt("");
            return;
        }
        running = true;
        while (running) {
            player.breedFeedSellPrint();
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
                prompt("");
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
                case 1 -> {
                    amount = promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(0).getPrice() * amount) {
                        checkIfPlayerOwnsFood(player, amount, foodList.get(0));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 2 -> {
                    amount = promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(1).getPrice() * amount) {
                        checkIfPlayerOwnsFood(player, amount, foodList.get(1));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 3 -> {
                    amount = promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(2).getPrice() * amount) {
                        checkIfPlayerOwnsFood(player, amount, foodList.get(2));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                }
                case 4 -> {
                    amount = promptInt("Hur mycket vill du köpa?", 1, 30);
                    if (player.money >= foodList.get(3).getPrice() * amount) {
                        checkIfPlayerOwnsFood(player, amount, foodList.get(3));
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
        String gender = prompt("Och vilket kön önskas (skriv hane eller hona)?");
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

