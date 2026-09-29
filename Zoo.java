import java.io.*;
import java.util.*;

public class Zoo {
    private String zooName;
    private ArrayList<Animal> animals;

    public Zoo(String zooName) {
        this.zooName = zooName;
        this.animals = new ArrayList<>();
    }

    public String getZooName() { return zooName; }

    // Function to add an animal with validation
    public boolean addAnimal(Animal animal) {
        if (animal.getName() == null || animal.getName().trim().isEmpty() ||
                animal.getColour() == null || animal.getColour().trim().isEmpty()) {
            System.out.println("Error: Name or colour cannot be blank. Animal not saved.");
            return false;
        }
        animals.add(animal);
        System.out.println(animal.getName() + " added to the zoo.");
        return true;
    }

    // Function to Remove animal
    public void removeAnimal(String name) {
        boolean found = false;
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName().equalsIgnoreCase(name)) {
                animals.remove(i);
                System.out.println(name + " removed.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Animal not found.");
    }

    // Function to View all animals
    public void displayAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Zoo is empty.");
            return;
        }
        for (Animal a : animals) {
            System.out.println(a.makeSound());
        }
    }

    // Function to Search all animals by name or colour
    public void searchAnimal(String query) {
        boolean found = false;
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(query) || a.getColour().equalsIgnoreCase(query)) {
                System.out.println("Found: " + a.makeSound());
                found = true;
            }
        }
        if (!found) System.out.println("No match found.");
    }

    // Function to Perform daily care
    public void performDailyCare() {
        for (Animal a : animals) {
            if (a instanceof Flyable) { ((Flyable) a).performWingCheck(); }
            if (a instanceof Swimmable) { ((Swimmable) a).performWaterQualityCheck(); }
        }
    }

    // Function to Print Zoo Report
    public void printZooReport() {
        System.out.println("\n--- Zoo Report: " + zooName + " ---");
        int eagles = 0, penguins = 0, ducks = 0;
        HashMap<String, Integer> colours = new HashMap<>();

        for (Animal a : animals) {
            if (a instanceof Eagle) eagles++;
            else if (a instanceof Penguin) penguins++;
            else if (a instanceof Duck) ducks++;

            String col = a.getColour().toLowerCase();
            colours.put(col, colours.getOrDefault(col, 0) + 1);
        }

        System.out.println("Eagles: " + eagles + " | Penguins: " + penguins + " | Ducks: " + ducks);

        String dominant = "None";
        int max = 0;
        for (Map.Entry<String, Integer> entry : colours.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                dominant = entry.getKey();
            }
        }
        System.out.println("Dominant Colour: " + dominant + " (" + max + ")");
    }

    // Function to Modify animal details
    public void modifyAnimalWeight(String name, double newWeight) {
        boolean found = false;
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) {
                a.setWeight(newWeight);
                System.out.println(name + "'s weight has been updated to " + newWeight + "kg.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Animal not found.");
    }

    // An additional feature to feed all animals
    public void feedAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals to feed!");
            return;
        }
        System.out.println("\n--- Feeding Time! ---");
        for (Animal a : animals) {
            double weightGained = 0.5;
            a.setWeight(a.getWeight() + weightGained);
            System.out.println(a.getName() + " ate their food and gained " + weightGained + "kg! New weight: " + a.getWeight() + "kg.");
        }
    }

    // Another additional feature to calculate the total weight of all animals in the zoo
    public void calculateTotalWeight() {
        if (animals.isEmpty()) {
            System.out.println("The zoo is empty. Total weight is 0.0 kg.");
            return;
        }
        double totalWeight = 0;
        for (Animal a : animals) {
            totalWeight += a.getWeight();
        }
        System.out.println("\n--- Zoo Logistics ---");
        System.out.println("Total combined weight of all animals: " + String.format("%.2f", totalWeight) + " kg");
    }

    // Function to Save data to the 2 files
    public void saveData() {
        try (PrintWriter zooOut = new PrintWriter(new FileWriter("ZooDetails.txt"));
             PrintWriter animalOut = new PrintWriter(new FileWriter("Animal Details.txt"))) {

            zooOut.println(this.zooName);
            for (Animal a : animals) {
                if (a.getName().trim().isEmpty() || a.getColour().trim().isEmpty()) continue;

                if (a instanceof Eagle) {
                    animalOut.println("Eagle," + a.getName() + "," + a.getAge() + "," + a.getColour() + "," + a.getWeight() + ",2.0");
                } else if (a instanceof Penguin) {
                    animalOut.println("Penguin," + a.getName() + "," + a.getAge() + "," + a.getColour() + "," + a.getWeight() + ",15.0");
                } else if (a instanceof Duck) {
                    animalOut.println("Duck," + a.getName() + "," + a.getAge() + "," + a.getColour() + "," + a.getWeight() + ",Yellow");
                }
            }
            System.out.println("Data successfully saved to disk.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Function to Load details from disk
    public void loadData() {
        try {
            File animalFile = new File("Animal Details.txt");
            if (animalFile.exists()) {
                Scanner fileScanner = new Scanner(animalFile);
                while (fileScanner.hasNextLine()) {
                    String[] parts = fileScanner.nextLine().split(",");
                    if (parts.length >= 6) {
                        String type = parts[0], name = parts[1], colour = parts[3];
                        int age = Integer.parseInt(parts[2]);
                        double weight = Double.parseDouble(parts[4]);

                        if (type.equals("Eagle")) animals.add(new Eagle(name, age, colour, weight, Double.parseDouble(parts[5])));
                        else if (type.equals("Penguin")) animals.add(new Penguin(name, age, colour, weight, Double.parseDouble(parts[5])));
                        else if (type.equals("Duck")) animals.add(new Duck(name, age, colour, weight, parts[5]));
                    }
                }
                fileScanner.close();
                System.out.println("Data loaded successfully.");
            }
        } catch (Exception e) {
            System.out.println("No previous data found or error loading.");
        }
    }
}