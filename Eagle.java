public class Eagle extends Animal implements Flyable {
    private double wingSpan; // Additional variable

    public Eagle(String name, int age, String colour, double weight, double wingSpan) {
        super(name, age, colour, weight);
        this.wingSpan = wingSpan;
    }

    @Override
    public String makeSound() {
        return "Screech! I am " + name + ", a " + age + " year old eagle.";
    }

    @Override
    public void takeOff() { System.out.println(name + " takes off."); }
    @Override
    public void land() { System.out.println(name + " lands."); }
    @Override
    public void performWingCheck() { System.out.println("Checking " + name + "'s wingspan of " + wingSpan + "m."); }
}