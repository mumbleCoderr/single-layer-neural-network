import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Perceptron englishPerceptron = new Perceptron("english");
        Perceptron frenchPerceptron = new Perceptron("french");
        Perceptron germanPerceptron = new Perceptron("german");
        Perceptron polishPerceptron = new Perceptron("polish");
        Perceptron spanishPerceptron = new Perceptron("spanish");
        db.loadData();
        englishPerceptron.train(db.getEnglish(), db.getFrench(), db.getGerman(), db.getPolish(), db.getSpanish());
        frenchPerceptron.train(db.getEnglish(), db.getFrench(), db.getGerman(), db.getPolish(), db.getSpanish());
        germanPerceptron.train(db.getEnglish(), db.getFrench(), db.getGerman(), db.getPolish(), db.getSpanish());
        polishPerceptron.train(db.getEnglish(), db.getFrench(), db.getGerman(), db.getPolish(), db.getSpanish());
        spanishPerceptron.train(db.getEnglish(), db.getFrench(), db.getGerman(), db.getPolish(), db.getSpanish());

        List<Perceptron> perceptrons = Arrays.asList(englishPerceptron, frenchPerceptron, germanPerceptron, polishPerceptron, spanishPerceptron);
        Tester tester = new Tester(perceptrons, db);
        tester.computeTestingData("test");


        Window window = new Window(tester);
    }
}