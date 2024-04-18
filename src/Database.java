import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    Map<Character, Integer> english;
    Map<Character, Integer> french;
    Map<Character, Integer> german;
    Map<Character, Integer> polish;
    Map<Character, Integer> spanish;

    public Database() {
        this.english = new HashMap<>();
        this.french = new HashMap<>();
        this.german = new HashMap<>();
        this.polish = new HashMap<>();
        this.spanish = new HashMap<>();

        char letter = 'a';
        for (int i = 0; i < 25; i++) {
            english.put(letter, 0);
            french.put(letter, 0);
            german.put(letter, 0);
            polish.put(letter, 0);
            spanish.put(letter, 0);
            letter++;
        }
    }


    public void loadData() {
        File dictionary = new File("data");
        File[] dicts = null;
        if (dictionary.exists() && dictionary.isDirectory()) {
            dicts = dictionary.listFiles();
        }

        String language;
        for (File file : dicts) {
            language = file.getName();
            File[] texts = file.listFiles();
            for (File text : texts){
                System.out.println(language + " " + text.getName());
                countChars(text, language);
            }
        }
    }

    private void countChars(File file, String language){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = "";
            while ((line = br.readLine()) != null){
                String[] split = line.split("\\s++");
                for (String s : split){
                    for (int i = 0; i < s.length(); i++) {
                        if(language.equals("ENGLISH")){
                            if (english.containsKey(s.charAt(i))) {
                                int counter = english.get(s.charAt(i));
                                english.put(s.charAt(i), counter + 1);
                            }
                        }
                        if(language.equals("FRENCH")){
                            if (french.containsKey(s.charAt(i))) {
                                int counter = french.get(s.charAt(i));
                                french.put(s.charAt(i), counter + 1);
                            }
                        }
                        if(language.equals("GERMAN")){
                            if (german.containsKey(s.charAt(i))) {
                                int counter = german.get(s.charAt(i));
                                german.put(s.charAt(i), counter + 1);
                            }
                        }
                        if(language.equals("POLISH")){
                            if (polish.containsKey(s.charAt(i))) {
                                int counter = polish.get(s.charAt(i));
                                polish.put(s.charAt(i), counter + 1);
                            }
                        }
                        if(language.equals("SPANISH")){
                            if (spanish.containsKey(s.charAt(i))) {
                                int counter = spanish.get(s.charAt(i));
                                spanish.put(s.charAt(i), counter + 1);
                            }
                        }
                    }
                }
            }
            br.close();
        }catch (IOException e){
            System.err.println("no such a language in our dictionary");
        }
    }
}
