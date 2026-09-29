// Abstract base class storing shared variables
public abstract class Animal {
    protected String name;
    protected int age;
    protected String colour;
    protected double weight;

    public Animal(String name, int age, String colour, double weight) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    // Abstract method to be overridden by subclasses
    public abstract String makeSound();

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getColour() { return colour; }
    public double getWeight() { return weight; }

    // Setter
    public void setWeight(double weight) { this.weight = weight; }
}