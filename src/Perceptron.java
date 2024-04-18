public class Perceptron {
    private double[] weights;
    private double alpha;
    private String language;
    private boolean weightsImproved = false;

    public Perceptron(String language) {
        this.weights = new double[27];
        this.language = language;
        this.alpha = 0.3;
        for (int i = 0; i < weights.length; i++) {
            if (i != weights.length-1)
                weights[i] = 0.2;
            else
                weights[i] = 0;
        }
    }

    public void train(int[][] english, int[][] french, int[][] german, int[][] polish, int[][] spanish){
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTRAINING");
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
        do {
            weightsImproved = false;
            train(english, "english");
            train(french, "french");
            train(german, "german");
            train(polish, "polish");
            train(spanish, "spanish");
        }while (weightsImproved);
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFINISHED TRAINING");
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
    }

    private void train(int[][] language, String languageName){
        for (int[] inputVector : language){
            int realOutput = 0;
            if (languageName.equals(this.language)) realOutput = 1;
            int calculatedOutput = 0;
            double dotProduct = dotProduct(inputVector);
            if (dotProduct >= 0) calculatedOutput = 1;
            double[] newWeigths = deltaRule(inputVector, realOutput, calculatedOutput);
            System.out.print("input vector: ");
            printVector(inputVector);
            System.out.print("weights: ");
            printVector(weights);
            System.out.println("dot product: " + Math.floor(dotProduct * 100) / 100);
            System.out.println("d = " + realOutput + ", y = " + calculatedOutput);
            System.out.print("W': ");
            printVector(newWeigths);
            boolean isEqual = compareVectors(weights, newWeigths);
            if (!isEqual){
                weights = newWeigths;
                weightsImproved = true;
            }
            System.out.println("====================================================================================" +
                    "===============================================================================================" +
                    "===============");
        }
    }

    public double dotProduct(int[] inputVector) {
        double dotProduct = 0;
        for (int i = 0; i < inputVector.length; i++) {
            dotProduct += weights[i] * inputVector[i];
        }
        return dotProduct;
    }

    private double[] deltaRule(int[] inputVector, int realOutput, int calculatedOutput){
        double[] tmpVector = new double[27];
        double[] newWeights = new double[27];
        double tmp = (realOutput - calculatedOutput) * alpha;
        for (int i = 0; i < inputVector.length; i++) {
            tmpVector[i] = inputVector[i] * tmp;
        }
        for (int i = 0; i < tmpVector.length; i++) {
            newWeights[i] = weights[i] + tmpVector[i];
        }
        return newWeights;
    }

    private boolean compareVectors(double[] v1, double[] v2){
        boolean isEqual = true;
        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) isEqual = false;
        }
        return isEqual;
    }

    private void printVector(double[] vector){
        System.out.print("[ ");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(Math.floor(vector[i] * 100) / 100);
            if (i != vector.length-1) System.out.print(", ");
        }
        System.out.println(" ]");
    }
    private void printVector(int[] vector){
        System.out.print("[ ");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i != vector.length-1) System.out.print(", ");
        }
        System.out.println(" ]");
    }

    public double[] getWeights() {
        return weights;
    }

    public String getLanguage() {
        return language;
    }
}
