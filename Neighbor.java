class Neighbor implements Comparable<Neighbor> {
    Instance instance; // Instancia del vecino
    double distance;   // Distancia a la instancia de prueba

    public Neighbor(Instance instance, double distance) {
        this.instance = instance;
        this.distance = distance;
    }

    @Override
    public int compareTo(Neighbor other) {
        return Double.compare(this.distance, other.distance); // Compara por distancia
    }
}