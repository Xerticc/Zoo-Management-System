public class Duck extends Animal implements Swimmable, Flyable {
    private String beakColor; // Additional variable

    public Duck(String name, int age, String colour, double weight, String beakColor) {
        super(name, age, colour, weight);
        this.beakColor = beakColor;
    }

    @Override
    public String makeSound() {
        return "Quack! I am " + name + ", a " + age + " year old duck.";
    }

    @Override
    public void dive() { System.out.println(name + " dunks its head."); }
    @Override
    public void surface() { System.out.println(name + " bobs up."); }
    @Override
    public void performWaterQualityCheck() { System.out.println("Checking algae levels for " + name + "."); }

    @Override
    public void takeOff() { System.out.println(name + " flaps away."); }
    @Override
    public void land() { System.out.println(name + " splashes down."); }
    @Override
    public void performWingCheck() { System.out.println("Inspecting feathers for " + name + "."); }
}