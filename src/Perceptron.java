public class Perceptron {
    private double[] wages;
    private String language;

    public Perceptron(String language) {
        this.wages = new double[27];
        this.language = language;
        for (int i = 0; i < wages.length; i++) {
            if (i != wages.length-1)
                wages[i] = 0.2;
            else
                wages[i] = 0;
        }
    }

    public void train(){

    }
}
