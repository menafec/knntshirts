class EuclideanDistance implements DistanceMetric {
    @Override
    public double calculate(double[] instance1, double[] instance2) {
        double sum = 0.0;
        for (int i = 0; i < instance1.length; i++) {
            sum += Math.pow(instance1[i] - instance2[i], 2);
        }
        return Math.sqrt(sum);
    }
}