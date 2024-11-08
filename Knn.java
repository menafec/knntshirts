import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Knn {
    public static void main(String[] args) throws IOException {
        // Obtener entrada del usuario para la ruta del archivo de entrenamiento y el valor de k
        Scanner scanner = new Scanner(System.in);

        // Especifica la ruta completa del archivo de datos
        String trainingFile = "/Users/ximenafernandez/Desktop/Machine Learning/KNN Sample Gene/knn.csv";
        System.out.print("Enter the value of k: ");
        int k = Integer.parseInt(scanner.nextLine());

        // Cargar los datos de entrenamiento
        Dataset dataset = new Dataset(trainingFile);
        dataset.printData(); // Imprimir los datos para verificar la carga

        // Objeto KnnClassifier para predicción y evaluación
        KnnClassifier classifier = new KnnClassifier(new EuclideanDistance(), k);
        classifier.train(dataset);

        // Menú interactivo para opciones de predicción
        label:
        while (true) {
            System.out.println("Opciones:");
            System.out.println("a) Evaluar todas las observaciones del test set");
            System.out.println("b) Clasificar puntos desde un archivo CSV");
            System.out.println("c) Salir");
            System.out.print("Opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "a":
                    System.out.print("Ingresa el path del conjunto de prueba: ");
                    String testFile = scanner.nextLine();
                    Dataset testDataset = new Dataset(testFile);
                    classifier.evaluate(testDataset.data);
                    break;

                case "b":
                    System.out.print("Ingresa la ruta del archivo de prueba: ");
                    String testFilePath = scanner.nextLine();
                    Dataset testInstanceDataset = new Dataset(testFilePath);
                    
                    System.out.print("¿Cuántas instancias aleatorias deseas imprimir? ");
                    int numInstances = Integer.parseInt(scanner.nextLine());

                    Random random = new Random();
                    for (int i = 0; i < numInstances; i++) {
                        int randomIndex = random.nextInt(testInstanceDataset.data.size());
                        Instance testInstance = testInstanceDataset.data.get(randomIndex);
                        String predictedLabel = classifier.predict(testInstance);
                        System.out.println("Predicted Label for instance with features " +
                                Arrays.toString(testInstance.getFeatures()) + ": " + predictedLabel);
                    }
                    break;

                case "c":
                    System.out.println("Saliendo...");
                    break label;

                default:
                    System.out.println("Opción inválida. Por favor, selecciona a, b o c.");
            }
        }
        scanner.close();
    }
}