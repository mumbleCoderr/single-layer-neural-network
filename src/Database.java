import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    int[][] english;
    int[][] french;
    int[][] german;
    int[][] polish;
    int[][] spanish;

    public Database() {
        this.english = new int[10][27];
        this.french = new int[10][27];
        this.german = new int[10][27];
        this.polish = new int[10][27];
        this.spanish = new int[10][27];
    }
    public void loadData() {
        File dict = new File("data");
        File[] languages = null;
        if (dict.exists() && dict.isDirectory())
            languages = dict.listFiles();

        String language = "";
        File[] texts = null;
        for (File file : languages) {
            language = file.getName();
            texts = file.listFiles();
            for (int i = 0; i < texts.length; i++) {
                if (language.equals("ENGLISH")) english[i] = readFile(texts[i].toString());
                if (language.equals("FRENCH")) french[i] = readFile(texts[i].toString());
                if (language.equals("GERMAN")) german[i] = readFile(texts[i].toString());
                if (language.equals("POLISH")) polish[i] = readFile(texts[i].toString());
                if (language.equals("SPANISH")) spanish[i] = readFile(texts[i].toString());
            }
        }
    }
    public int[] readFile(String filename) {
        Map<Character, Integer> letters = new HashMap<>();
        char letter = 'a';
        for (int i = 0; i < 26; i++) {
            letters.put(letter++, 0);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s++");
                for (String s : split) {
                    for (int i = 0; i < s.length(); i++) {
                        char tmp = Character.toLowerCase(s.charAt(i));
                        if (letters.containsKey(tmp)) {
                            int counter = letters.get(tmp);
                            letters.put(tmp, counter + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("no such a language in our dictionary");
        }

        int[] lettersCount = convert(letters);

        return lettersCount;
    }

    private int[] convert(Map<Character, Integer> map) {
        int[] arr = new int[27];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            arr[key - 'a'] = count;
        }
        arr[26] = -1;
        return arr;
    }
    public int[][] getEnglish() {
        return english;
    }

    public int[][] getFrench() {
        return french;
    }

    public int[][] getGerman() {
        return german;
    }

    public int[][] getPolish() {
        return polish;
    }

    public int[][] getSpanish() {
        return spanish;
    }
}
