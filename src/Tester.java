import java.io.File;
import java.util.List;

public class Tester {
    private List<Perceptron> perceptrons;
    private Database db;
    private double totalAttemps;
    private double positiveAttemps;

    public Tester(List<Perceptron> perceptrons, Database db) {
        this.perceptrons = perceptrons;
        this.db = db;
        this.totalAttemps = 0;
        this.positiveAttemps = 0;
    }


    public void computeTestingData(String filename) {
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCOMPUTING TESTING DATA");
        System.out.println("====================================================================================" +
                "===============================================================================================" +
                "===============");
        File dict = new File(filename);
        File[] languages = null;
        if (dict.exists() && dict.isDirectory())
            languages = dict.listFiles();

        for (File file : languages) {
            String realLanguage = file.getName();
            int[] inputVector = db.readFile(file.toString());
            Perceptron decision = null;
            for (Perceptron perceptron : perceptrons) {
                double dotProduct = perceptron.dotProduct(inputVector);
                if (dotProduct >= 0) decision = perceptron;
            }

            System.out.println("DECISION : " + decision.getLanguage().toUpperCase());
            System.out.println("REAL LANGUAGE: " + realLanguage.toUpperCase());

            if (decision.getLanguage().toUpperCase().equals(realLanguage.toUpperCase())) {
                positiveAttemps++;
                totalAttemps++;
            } else {
                totalAttemps++;
            }
        }

        System.out.println("\naccuracy: " + evaluation() + "%");
    }

    public void computeUserData(String filename) {
        int[] inputVector = db.readFile(filename);
        Perceptron decision = null;
        for (Perceptron perceptron : perceptrons) {
            double dotProduct = perceptron.dotProduct(inputVector);
            if (dotProduct >= 0) decision = perceptron;
        }

        System.out.println("DECISION : " + decision.getLanguage().toUpperCase());
    }

    private double evaluation() {
        double accuracy = (positiveAttemps / totalAttemps) * 100;
        return accuracy;
    }
}
