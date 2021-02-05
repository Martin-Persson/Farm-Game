package Game;

import Animals.Animal;
import Food.Food;
import static Game.ToolsHelperClass.*;


public class PlayerHelperClass {
    
    static public void printInventory(Game game, Player player){
        System.out.printf("-".repeat(10) + "=".repeat(5) + " Runda %d av %d " + "=".repeat(5) +
                "-".repeat(10), game.currentRound, game.rounds);
        System.out.printf("\nNu är det %ss tur.\t Pengar: %dKr\n",
                player.getName(), player.getMoney());
        
        printFood(player);
        printAnimals(player);
        System.out.println("-".repeat(45));
    }
    
    static public void printFood(Player player){
        if (player.getMyFood().size() > 0) {
            System.out.println("Din mat:");
            System.out.format("%-10s%-10s%s\n", "Typ", "Mängd", "Äts av");
            for (Food food : player.getMyFood()) {
                System.out.format("%-10s%-10s%s\n", food.getClass().getSimpleName(), food.getAmountOfFood(), food.getEatenBy());
            }
            System.out.println("-".repeat(45));
        } else {
            System.out.println("Du äger ingen mat.");
        }
    }
    
    static public void printAnimals(Player player){
        
        if (player.getMyAnimals().size() > 0) {
            System.out.println("Dina djur:");
            System.out.format("%-10s%-10s%-11s%-8s%s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
            
            for (Animal animal : player.getMyAnimals()) {
                System.out.format("%-10s%-10s%s/%-2s%10.0f%s%9s",
                        translateAnimals(animal.getClass().getSimpleName()),
                        animal.getName(), animal.getAge(), animal.getMaxAge(), animal.getHealth(), "%",
                        animal.getGender().toString().equalsIgnoreCase("male") ? "Hane\n": "Hona\n");
            }
        }   else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    static public void checkIfPlayerOwnsFood(Player player, int amount, Food food) {
        if(player.myFood.size() == 0){
            player.myFood.add(food);
            player.myFood.get(player.myFood.size()-1).setAmountOfFood(+amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
        }
        else if (player.myFood.contains(food)) {
            int ownedFood = food.getAmountOfFood();
            food.setAmountOfFood(ownedFood + amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
        } else {
            player.myFood.add(food);
            player.myFood.get(player.myFood.size() - 1).setAmountOfFood(+amount);
            player.money -= food.getPrice() * amount;
            player.setMadeMove(true);
        }
    }
    
    static public void printFeedFood(Player player){
        int counter = 1;
        if (player.getMyFood().size() > 0) {
            System.out.println("Din mat:");
            System.out.format("%-10s%-10s\n", "Typ", "Mängd");
            for (Food food : player.getMyFood()) {
                System.out.format("[%d] %-10s%-10s\n", counter, food.getClass().getSimpleName(), food.getAmountOfFood());
                counter++;
            }
            System.out.println("-".repeat(45));
        } else {
            System.out.println("Du äger ingen mat.");
        }
    }
    
    static public void payAnimal(Player player, Store store) {
        for (Animal animal : store.animalList) {
            if (player.myAnimals.get(player.myAnimals.size() - 1).getClass().getSimpleName()
                    .equalsIgnoreCase(animal.getClass().getSimpleName())) {
                
                player.money -= animal.getPrice();
            } else {
                System.out.println();
            }
        }
    }
    
    static public  void buyFoodMenu(Player player, Store store){
        int counter = 1;
        System.out.println("Detta är maten vi har att erbjuda:\n");
        System.out.printf("%8s%10s%11s\n", "Typ", "Pris", "Äts av");
        System.out.println("-".repeat(30));
        for (Food food : store.foodList) {
            System.out.printf("[%d] %-11s%-8s%s\n", counter, food.getClass().getSimpleName(), food.getPrice() + " Kr", food.getEatenBy());
            counter++;
        }
        System.out.printf(player.getMadeMove() ?"[%d] Avsluta runda\n" : "[%d] Backa\n", counter);
    }
    
    static public  void buyAnimalMenu(Store store, Player player){
        int counter = 1;
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.printf("%7s%13s%8s%8s\n", "Typ", "Max ålder", "Pris", "Äter");
        System.out.println("-".repeat(40));
        for (Animal animal : store.animalList) {
            System.out.printf("[%d] %-10s%-10s%s%-4s%s\n",
                    counter, translateAnimals(animal.getClass().getSimpleName())
                    , animal.getMaxAge()+ "år", animal.getPrice(), "Kr", animal.getEatenFood());
            counter++;
        }
        System.out.println(player.getMadeMove() ? "["+counter+"] Avsluta runda" : "["+counter+"] Backa");
    }
    
    static public void breedFeedSellPrint(Player player){
        if (player.getMyAnimals().size() > 0) {
            int counter = 1;
            System.out.println("Dina djur:");
            System.out.format("%7s%10s%12s%11s%6s\n", "Typ", "Namn", "Ålder", "Hälsa", "Kön");
            
            for (Animal animal : player.getMyAnimals()) {
                System.out.format("[%d] %-10s%-10s%s/%-2s%10.0f%s%9s",
                        counter, translateAnimals(animal.getClass().getSimpleName()),
                        animal.getName(), animal.getAge(), animal.getMaxAge(), animal.getHealth(), "%",
                        animal.getGender().toString().equalsIgnoreCase("male") ? "Hane\n": "Hona\n");
                counter++;
            }
            System.out.printf("[%d]", counter);
        }   else {
            System.out.println("Du äger inga djur.\n");
        }
    }
    
    static public void payVet(Player player){
        for(Animal animal : player.getMyAnimals()){
            if(animal.isSick()){
                System.out.printf("%s!\n",player.getName());
                System.out.println("Ditt djur " + animal.getName() + " Har blivit väldigt sjukt.");
                System.out.println("Du har " + player.getMoney() + "Kr:");
                int choice = promptInt("""
            Vill du försöka rädda djuret hos veterinären?
            Det skulle kosta dig""" + " " + animal.getVetCost() + "Kr\n" +
                        "[1] - Ja\n[2] - Nej", 1, 2);
                
                if(choice == 1){
                    if(player.getMoney() < animal.getVetCost()){
                        clear();
                        System.out.println("Du har tyvärr inte råd att betala.");
                        System.out.println(animal.getName() + " dog.");
                        player.deadAnimals.add(animal);
                    }
                    else{
                        player.setMoney(player.getMoney() - animal.getVetCost());
                        if(randomBoolean()){
                            player.deadAnimals.add(animal);
                            clear();
                            System.out.println(animal.getName() + " klarade sig tyvärr inte.\nVeterinären " +
                                    "kämpade hela natten utan framgång.");
                        }
                        else{
                            clear();
                            System.out.println("Hurra! " + animal.getName() + " klarade sig och är nu frisk.");
                            animal.setSick(false);
                        }
                    }
                }
                else{
                    clear();
                    System.out.println("Eftersom du är snål så dog " + animal.getName());
                    player.deadAnimals.add(animal);
                }
            }
        }
    }
    
    static public void printDeadAnimals(Player player){
        if(player.deadAnimals.size() > 0){
            System.out.println("\nDina djur som dött under spelets gång:");
            System.out.println(player.deadAnimals);
            player.myAnimals.removeAll(player.deadAnimals);
            if(player.deadAnimals.size() > 10){
                System.out.println("Du kan nu titulera dig djurrikets baneman.");
            }
        }
        System.out.println();
    }
    
    static public void sellAllAnimals(Player[] players){
        for(Player player : players){
            for(Animal animal : player.myAnimals){
                int sellPrice = (int) (animal.getPrice()
                        * animal.getHealth() / 100)
                        * animal.sellAgeModifier() / 100;
                player.money += sellPrice;
            }
        }
    }
}
