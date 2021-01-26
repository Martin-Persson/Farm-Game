package Animals;

public abstract class Animal {
    
    public String name;
    public Gender gender;
    private int health = 100;
    private int age;
    int MAX_AGE;
    public int price;
    private int weight;
    String food = "";
    
    public void setFood(String food) {
        this.food = food;
    }
    
    public String getFood() {
        return food;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    boolean ensilage = false;
    boolean korn = false;
    boolean kattmat = false;
    boolean foder = false;
    
    public Animal(String name, String gender){
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }
    
    public String getName() {
        return name;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public double getHealth() {
        return health;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setKattmat(boolean kattmat) {
        this.kattmat = kattmat;
    }
    
    public void setFoder(boolean foder) {
        this.foder = foder;
    }
    
    public void setEnsilage(boolean ensilage) {
        this.ensilage = ensilage;
    }
    
    public void setKorn(boolean korn) {
        this.korn = korn;
    }
    
    public boolean isEnsilage() {
        return ensilage;
    }
    
    public boolean isKorn() {
        return korn;
    }
    
    public boolean isKattmat() {
        return kattmat;
    }
    
    public boolean isFoder() {
        return foder;
    }
    
    public String toString(){
        return this.getGender().toString().toLowerCase();
    }
}
