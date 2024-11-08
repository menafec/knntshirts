import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

// Clase principal KnnClassifier
class KnnClassifier {
    // Métrica de distancia utilizada para calcular la distancia entre instancias
    private DistanceMetric distanceMetric;
    // Número de vecinos más cercanos a considerar
    private int k;
    // Lista que almacena los datos de entrenamiento
    private List<Instance> trainingData;

    // Constructor que inicializa la métrica de distancia y el número de vecinos k
    public KnnClassifier(DistanceMetric distanceMetric, int k) {
        this.distanceMetric = distanceMetric;
        this.k = k;
    }

    // Método para entrenar el modelo con datos de entrenamiento
    public void train(Dataset data) {
        this.trainingData = data.data;  // Asigna los datos de entrenamiento al atributo trainingData
    }

    // Método para hacer una predicción de clase para una instancia de prueba
    public String predict(Instance testInstance) {
        // Obtiene los k vecinos más cercanos a la instancia de prueba
        List<Neighbor> neighbors = getNeighbors(trainingData, testInstance.getFeatures(), k);
        // Retorna la clase mayoritaria entre los vecinos
        return getResponse(neighbors);
    }

    // Método para evaluar el rendimiento del modelo en un conjunto de prueba
    public void evaluate(List<Instance> testData) {
        int correctPredictions = 0; // Contador para predicciones correctas
        int totalPredictions = testData.size();   // Contador para el total de predicciones

        // Para cada instancia en los datos de prueba
        for (Instance testInstance : testData) {
            String actualLabel = testInstance.getLabel();    // Obtiene la clase real
            String predictedLabel = predict(testInstance);    // Obtiene la clase predicha

            // Incrementa el contador si la predicción es correcta
            if (predictedLabel.equals(actualLabel)) {
                correctPredictions++;
            }
        }
        // Calcula y muestra la precisión como porcentaje
        System.out.printf("Accuracy: %.2f%%\n", (double) correctPredictions / totalPredictions * 100);
    }

    // Método para obtener los k vecinos más cercanos y sus distancias a la instancia de prueba
    public List<Neighbor> getNeighbors(List<Instance> trainingData, double[] testFeatures, int k) {
        List<Neighbor> distances = new ArrayList<>(); // Lista para almacenar los vecinos y sus distancias

        // Para cada instancia en los datos de entrenamiento
        for (Instance instance : trainingData) {
            // Calcula la distancia entre la instancia de entrenamiento y la instancia de prueba
            double distance = distanceMetric.calculate(instance.getFeatures(), testFeatures);
            // Agrega la instancia y su distancia a la lista de vecinos
            distances.add(new Neighbor(instance, distance));
        }
        // Ordena los vecinos por distancia en orden ascendente
        Collections.sort(distances);
        // Retorna solo los k vecinos más cercanos
        return distances.subList(0, Math.min(k, distances.size())); // Asegúrate de no exceder el tamaño
    }

    // Método para determinar la clase mayoritaria entre los vecinos más cercanos
    private String getResponse(List<Neighbor> neighbors) {
        Map<String, Integer> frequencyMap = new HashMap<>(); // Mapa para contar las etiquetas de los vecinos

        // Para cada vecino en la lista
        for (Neighbor neighbor : neighbors) {
            // Incrementa el conteo de la etiqueta de este vecino en el mapa de frecuencias
            frequencyMap.put(neighbor.instance.getLabel(), 
                             frequencyMap.getOrDefault(neighbor.instance.getLabel(), 0) + 1);
        }
        // Retorna la etiqueta con el mayor conteo (es decir, la clase mayoritaria)
        return Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}