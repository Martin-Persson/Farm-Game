package com.company;

import java.util.Scanner;

public class HelperClass {
    
    
    
    
    public static void buyAnimalmenu(Player player){
        
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.println("    |   Sort    |     Pris    |    Foder");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ko        |   1000kr    |     Gräs
                    [2] | Katt      |   300kr     |     Möss
                    [3] | Kyckling  |   400kr     |     Frön
                    [4] | Gris      |   600kr     |     Foder
                    [5] | Får       |   700kr     |     Foder
                    """);
        if(player.getMadeMove()){
            System.out.println("[6] |  Avsluta runda");
        }
        else{
            System.out.println("[6] |  Backa");
        }
        
    }
    
    public static void mainMenu(){
                    System.out.println("""
                            [1] - Köpa djur
                            [2] - Köpa mat
                            [3] - Mata
                            [4] - Para
                            [5] - Sälja
                            [6] - Avsluta och spara spelet""");
    }
}
