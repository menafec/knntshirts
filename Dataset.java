import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Dataset {
    List<Instance> data; // Lista de instancias que contiene los datos

    // Constructor que carga los datos desde un archivo
    public Dataset(String filePath) throws IOException {
        data = new ArrayList<>(); // Inicializa la lista de datos
        loadDataFromFile(filePath); // Carga los datos del archivo
    }

    // Método para imprimir los datos cargados
    public void printData() {
        for (Instance instance : data) {
            System.out.println("Features: " + Arrays.toString(instance.getFeatures()) + ", Label: " + instance.getLabel());
        }
    }

    // Método para cargar datos desde un archivo CSV
    private void loadDataFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Saltar la primera línea (cabecera)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Suponiendo que los datos están separados por comas
                double height = Double.parseDouble(parts[0]); // Altura
                double weight = Double.parseDouble(parts[1]); // Peso
                String label = parts[2]; // Etiqueta (T-Shirt Size)
                data.add(new Instance(new double[]{height, weight}, label)); // Agregar la instancia a la lista
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear los datos. Verifica el formato del archivo.");
            throw e; // Relanza la excepción para manejarla más adelante
        }
    }
}