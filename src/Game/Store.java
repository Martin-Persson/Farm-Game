package Game;

import Animals.*;
import Food.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Store {
    ArrayList<Animal> animalList = new ArrayList<>(Arrays.asList(new Cow("female", ""),
            new Cat("female", ""),
            new Chicken("female", ""),
            new Pig("female", ""),
            new Sheep("female", "")));
    ArrayList<Food> foodList = new ArrayList<>(Arrays.asList(new Ensilage(), new Kattmat(),
            new Korn(), new Foder()));
    
    boolean running = true;
    int menuChoice;
    int amount = 0;
    
    public void buyAnimals(Player player) {
        Scanner input = new Scanner(System.in);
        HelperClass.clear();
        System.out.println("Välkommen till Jöns defekta djur!\n");
        running = true;
        while (running) {//TODO Formatting table
            
            HelperClass.buyAnimalMenu(player);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            do {
                System.out.println("-= Vilket djur vill du köpa? =-");
                try {
                    menuChoice = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Något gick fel, försök igen");
                }
            } while (!(menuChoice > 0 && menuChoice < 7));
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
        int animalToSell = 0;
        int sellPrice = 0;
        player.setMadeMove(false);
        HelperClass.clear();
        if (player.myAnimals.size() == 0) {
            System.out.println("Du äger inga djur.");
            return;
        }
        running = true;
        while (running) {
            Scanner input = new Scanner(System.in);
            System.out.println("Vilket djur vill du sälja?");
            player.printAnimals();
            do {
                try {
                    System.out.print("Ange vilket djur du vill sälja: ");
                    
                    animalToSell = Integer.parseInt(input.nextLine());
                    if(animalToSell == player.myAnimals.size() + 1){
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Nu blev det galet.");
                    player.setMadeMove(false);
                }
            } while (animalToSell > player.myAnimals.size() || animalToSell < 1);
            if (animalToSell == 99) {
                System.out.println("\n".repeat(40));
                if (player.getMadeMove()) {
                    running = false;
                    break;
                } else {
                    return;
                }
                
            }
            sellPrice = (int) (player.myAnimals.get(animalToSell - 1).getPrice()
                    * (player.myAnimals.get(animalToSell - 1).getHealth() / 100));
            player.money += sellPrice;
            System.out.printf("Ditt djur är nu sålt och du fick %d kr för det.\n", sellPrice);
            player.myAnimals.remove(animalToSell - 1);
            player.setMadeMove(true);
            
        }
    }
    
    public void buyFood(Player player) {
        Scanner input = new Scanner(System.in);
        HelperClass.clear();
        System.out.println("Välkommen till Jöns begagnade mat!\n");
        running = true;
        while (running) {//TODO Formatting table
            
            HelperClass.buyFoodMenu(player);
            System.out.printf("\nDu har %d kr.", player.money);
            System.out.println("\n\n");
            do {
                System.out.println("-= Vilken sorts mat vill du köpa? =-");
                try {
                    menuChoice = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Något gick fel, försök igen");
                }
            } while (!(menuChoice > 0 && menuChoice < 6));
            switch (menuChoice) {
                
                case 1:
                    System.out.println("Hur mycket vill du köpa?");
                    try {
                        amount = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Något gick fel, försök igen");
                    }
                    if (player.money >= foodList.get(0).getPrice() * amount) {
                        HelperClass.checkIfFoodExists(player, amount, foodList.get(0));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                    break;
                case 2:
                    System.out.println("Hur mycket vill du köpa?");
                    try {
                        amount = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Något gick fel, försök igen");
                    }
                    if (player.money >= foodList.get(1).getPrice() * amount) {
                        HelperClass.checkIfFoodExists(player, amount, foodList.get(1));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                    break;
                case 3:
                    System.out.println("Hur mycket vill du köpa?");
                    try {
                        amount = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Något gick fel, försök igen");
                    }
                    if (player.money >= foodList.get(2).getPrice() * amount) {
                        HelperClass.checkIfFoodExists(player, amount, foodList.get(2));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                    break;
                case 4:
                    System.out.println("Hur mycket vill du köpa?");
                    try {
                        amount = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Något gick fel, försök igen");
                    }
                    if (player.money >= foodList.get(3).getPrice() * amount) {
                        HelperClass.checkIfFoodExists(player, amount, foodList.get(3));
                    } else {
                        System.out.println("Du har tyvärr inte råd med detta..");
                    }
                    break;
                case 5: {
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
    
    
    public int payAnimal(Player player) {
        for (Animal animal : animalList) {
            if (player.myAnimals.get(player.myAnimals.size() - 1).getClass().getSimpleName()
                    .equalsIgnoreCase(animal.getClass().getSimpleName())) {
                
                player.money -= animal.getPrice();
            } else {
                System.out.println();
            }
        }
        return player.money;
    }
    

}

