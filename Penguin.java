public class Penguin extends Animal implements Swimmable {
    private double swimSpeed; // Additional variable

    public Penguin(String name, int age, String colour, double weight, double swimSpeed) {
        super(name, age, colour, weight);
        this.swimSpeed = swimSpeed;
    }

    @Override
    public String makeSound() {
        return "Honk! I am " + name + ", a " + age + " year old penguin.";
    }

    @Override
    public void dive() { System.out.println(name + " dives into the water."); }
    @Override
    public void surface() { System.out.println(name + " surfaces."); }
    @Override
    public void performWaterQualityCheck() { System.out.println("Checking pool temp for " + name + "."); }
}