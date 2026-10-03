import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class DataExporter {

    public void exportInventoryToCSV(List<Animal> zooAnimals, String filePath) {
        // try-with-resources automatically closes the file when done
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            
            // 1. Write the CSV Header (the column names)
            writer.println("Name,Species,Age,Enclosure");

            // 2. Loop through your list of animals and write their data
            for (Animal animal : zooAnimals) {
                // Construct a single line of text separated by commas
                String line = animal.getName() + "," + 
                              animal.getSpecies() + "," + 
                              animal.getAge() + "," + 
                              animal.getEnclosure();
                
                // Write the line to the file
                writer.println(line);
            }
            
            System.out.println("Success: Zoo inventory exported to " + filePath);

        } catch (IOException e) {
            System.out.println("Error: Could not save the CSV file.");
            e.printStackTrace();
        }
    }
}