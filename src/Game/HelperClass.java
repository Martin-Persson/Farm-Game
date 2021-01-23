package Game;

public class HelperClass {
    
    
    
    
    public static void buyAnimalmenu(Player player){
        
        System.out.println("Detta är djuren vi har att erbjuda:\n");
        System.out.println("    |   Sort    |     Pris    |    Foder");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ko        |   1000kr    |     Ensilage
                    [2] | Katt      |   300kr     |     Kattmat
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
    
    public static void buyFooodmeenu(Player player){
        System.out.println("    |   Sort    |     Pris    |    Djur");
        System.out.println("-----------------------------------------");
        System.out.printf("""
                    [1] | Ensilage  |   240kr     |     Nötkreatur
                    [2] | Kattmat   |   190kr     |     Katter
                    [3] | Korn      |   110kr     |     Fjäderfä
                    [4] | Foder     |   180kr     |     Grisar - Får
                    """);
        if(player.getMadeMove()){
            System.out.println("[5] |  Avsluta runda");
        }
        else{
            System.out.println("[5] |  Backa");
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
