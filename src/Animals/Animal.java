package Animals;

public abstract class Animal {
    
    public String name;
    public Gender gender;
    private int health = 100;
    private int age;
    int MAX_AGE;
    public int price;
    private int weight;
    //private Food food1, food2, food3;
    
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
    
    public String toString(){
        return this.getGender().toString().toLowerCase();
    }
}
