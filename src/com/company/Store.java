package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Store {
    ArrayList<Animal> animalList = new ArrayList<>(Arrays.asList(new Cow("", "female"),
            new Cat("", "female"),
            new Chicken("", "female"),
            new Pig("", "female"),
            new Sheep("", "female")));
    ArrayList<Food> foodList = new ArrayList<>();
    
    int menuChoice;
    
    public void buyAnimals(Player player) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Välkommen till Jöns defekta djur!\n");
        while (true) {//TODO Formatting table
            System.out.println("Detta är djuren vi har att erbjuda:\n");
            System.out.println("    |   Sort    |     Pris    |    Foder");
            System.out.println("-----------------------------------------");
            System.out.println("""
                    [1] | Ko        |   1000kr    |     Gräs
                    [2] | Katt      |   300kr     |     Möss
                    [3] | Kyckling  |   400kr     |     Frön
                    [4] | Gris      |   600kr     |     Foder
                    [5] | Får       |   700kr     |     Foder
                    [6] [Avsluta rundan]
                    """);
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
                    if (player.money > animalList.get(0).getPrice()) {
                        player.myAnimals.add(new Cow(player.namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 2:
                    if (player.money > animalList.get(1).getPrice()) {
                        player.myAnimals.add(new Cat(player.namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 3:
                    if (player.money > animalList.get(2).getPrice()) {
                        player.myAnimals.add(new Chicken(player.namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 4:
                    if (player.money > animalList.get(3).getPrice()) {
                        player.myAnimals.add(new Pig(player.namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 5:
                    if (player.money > animalList.get(4).getPrice()) {
                        player.myAnimals.add(new Sheep(player.namingAnimal(), genderOfAnimal()));
                        pay(player);
                    } else {
                        System.out.println("Du har tyvärr inte råd att köpa detta djur.");
                    }
                    break;
                case 6:
                    System.out.println("\n".repeat(40));
                    return;
            }
        }
    }
    
    public void sellAnimals(Player player){
        int animalToSell = 0;
        int sellPrice = 0;
        if(player.myAnimals.size() == 0){
            System.out.println("Du äger inga djur.");
            return;
        }
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Vilket djur vill du sälja?");
            player.printAnimals();
            do {
                try {
                    System.out.print("Ange vilket djur du vill sälja: ");
                    animalToSell = Integer.parseInt(input.nextLine());
                    if(animalToSell == 99){
                        break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Nu blev det galet.");
                }
            } while (animalToSell > player.myAnimals.size() || animalToSell < 1);
            if(animalToSell == 99){
                System.out.println("\n".repeat(40));
                break;
            }
            sellPrice = (int) (player.myAnimals.get(animalToSell - 1).getPrice()
                    * (player.myAnimals.get(animalToSell - 1).getHealth() / 100));
            player.money += sellPrice;
            System.out.printf("Ditt djur är nu sålt och du fick %d kr för det.\n", sellPrice);
            player.myAnimals.remove(animalToSell - 1);
            
        }
    }
    
    public void buyFood(Player player){
    
    }
    
    public void feedAnimal(Player player){
    
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
    
    
    public int pay(Player player){
        for(Animal animal : animalList){
            if(player.myAnimals.get(player.myAnimals.size()-1).getClass().getSimpleName()
                    .equalsIgnoreCase(animal.getClass().getSimpleName())){
                
                player.money -= animal.getPrice();
            }
            else{
                System.out.println();
            }
        }
        return player.money;
    }
}