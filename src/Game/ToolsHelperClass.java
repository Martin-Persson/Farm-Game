package Game;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ToolsHelperClass {
    
    static public void clear() {
        System.out.println("\n".repeat(40));
    }
    
    static public String translateAnimals(String animal) {
        HashMap<String, String> translation = new HashMap<>();
        translation.put("Cow", "Ko");
        translation.put("Cat", "Katt");
        translation.put("Chicken", "Kyckling");
        translation.put("Pig", "Gris");
        translation.put("Sheep", "Får");
        
        return translation.get(animal);
    }
    
    static public int promptInt(String question, int min, int max) {
        var num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
        } catch (Exception ignore) {
        }
        // if illegal choice show the prompt again (recursion)
        // otherwise return the choice
        return num < min || num > max ?
                promptInt(question, min, max) : num;
    }
    
    static public String prompt(String question) {
        Scanner input = new Scanner(System.in);
        // clear() ? maybe we want a clear before each prompt
        System.out.println(question);
        return input.nextLine();
    }
    
    static public int randomNum(int max, int min) {
        Random rand = new Random();
        return rand.nextInt(((max - min) + 1)) + min;
    }
    
    static public boolean randomBoolean() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
    
    static public void mainMenu() {
        System.out.println("""
                [1] - Köpa djur
                [2] - Köpa mat
                [3] - Mata
                [4] - Para
                [5] - Sälja
                [6] - Hoppa över rundan
                [7] - Avsluta och spara spelet""");
    }
    
    static public void infoMenu() {
        System.out.println("""
                Hej och välkommen till Avelbonanza 3000!
                
                Spelet går till som så att man ska köpa och sälja djur för att tjäna pengar.
                Djuren man äger kommer att förlora hälsa varje runda (10% - 30%).
                För att få upp hälsan måste man mata sina djur.
                Djuren kan inte äta all sorts mat,
                så man får ha koll på vad man köper för djur och mat.
                
                Man kan bara göra en sak per runda, men du får dock köpa så
                mycket mat eller hur många djur du vill varje runda.
                Maten säljs i Kg och det krävs 1 Kg mat för att höja hälsan med 10%
                
                Man kan även försöka att para sina djur och får bara
                en chans per runda oavsett om det lyckas eller inte.
                Hur många avkommor man får slumpas fram och skiljer sig
                lite mellan de olika djuren.
                
                När alla rundor är spelade så kommer alla djur att säljas
                och den med mest pengar vinner.
                
                Lycka till!
                """);
    }
}
