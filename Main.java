import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo myZoo = new Zoo("My Awesome Zoo");

        // Attempt to load previously saved data on startup
        myZoo.loadData();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Zoo Management System ---");
            System.out.println("1. Add a new animal");
            System.out.println("2. Remove an animal");
            System.out.println("3. Search for an animal");
            System.out.println("4. Modify an animal's weight");
            System.out.println("5. Display all animals");
            System.out.println("6. Perform daily care routines");
            System.out.println("7. Print zoo report");
            System.out.println("8. Feed all animals");
            System.out.println("9. Calculate total weight");
            System.out.println("10. Save and Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addAnimalMenu(scanner, myZoo);
                    break;
                case "2":
                    System.out.print("Enter the name of the animal to remove: ");
                    String nameToRemove = scanner.nextLine().trim();
                    myZoo.removeAnimal(nameToRemove);
                    break;
                case "3":
                    System.out.print("Enter name or colour to search: ");
                    String query = scanner.nextLine().trim();
                    myZoo.searchAnimal(query);
                    break;
                case "4":
                    System.out.print("Enter the name of the animal to modify: ");
                    String modName = scanner.nextLine().trim();
                    System.out.print("Enter new weight: ");
                    try {
                        double newWeight = Double.parseDouble(scanner.nextLine().trim());
                        myZoo.modifyAnimalWeight(modName, newWeight);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid number for weight.");
                    }
                    break;
                case "5":
                    myZoo.displayAllAnimals();
                    break;
                case "6":
                    myZoo.performDailyCare();
                    break;
                case "7":
                    myZoo.printZooReport();
                    break;
                case "8":
                    myZoo.feedAnimals();
                    break;
                case "9":
                    myZoo.calculateTotalWeight();
                    break;
                case "10":
                    myZoo.saveData();
                    isRunning = false;
                    System.out.println("Data saved. Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addAnimalMenu(Scanner scanner, Zoo zoo) {
        System.out.println("\n--- Select Animal Type ---");
        System.out.println("1. Eagle");
        System.out.println("2. Penguin");
        System.out.println("3. Duck");
        System.out.print("Type: ");

        String typeChoice = scanner.nextLine().trim();

        // Blank Name Check
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Animal name cannot be blank. Aborting.");
            return;
        }

        // Age Validation Loop
        int animalAge = -1;
        while (animalAge < 0) {
            System.out.print("Enter animal age: ");
            try {
                animalAge = Integer.parseInt(scanner.nextLine().trim());
                if (animalAge < 0) {
                    System.out.println("Error: Age cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a real number.");
                animalAge = -1;
            }
        }

        System.out.print("Enter colour: ");
        String colour = scanner.nextLine().trim();

        // Weight Validation Loop (Extra protection!)
        double animalWeight = -1.0;
        while (animalWeight < 0) {
            System.out.print("Enter animal weight (kg): ");
            try {
                animalWeight = Double.parseDouble(scanner.nextLine().trim());
                if (animalWeight < 0) {
                    System.out.println("Error: Weight cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a real number.");
                animalWeight = -1.0;
            }
        }

        // Create the specific animal
        Animal newAnimal = null;
        try {
            if (typeChoice.equals("1")) {
                System.out.print("Enter wingspan (m): ");
                double wingspan = Double.parseDouble(scanner.nextLine().trim());
                newAnimal = new Eagle(name, animalAge, colour, animalWeight, wingspan);
            } else if (typeChoice.equals("2")) {
                System.out.print("Enter swim speed (km/h): ");
                double swimSpeed = Double.parseDouble(scanner.nextLine().trim());
                newAnimal = new Penguin(name, animalAge, colour, animalWeight, swimSpeed);
            } else if (typeChoice.equals("3")) {
                System.out.print("Enter beak colour: ");
                String beak = scanner.nextLine().trim();
                newAnimal = new Duck(name, animalAge, colour, animalWeight, beak);
            } else {
                System.out.println("Error: Invalid animal type selected.");
                return;
            }

            // Add to zoo
            zoo.addAnimal(newAnimal);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input for specific trait. Aborting.");
        }
    }
}