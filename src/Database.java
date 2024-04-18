import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    int[][] lettersInFiles;

    public Database() {
        this.lettersInFiles = new int[10][27];
    }
    public void countLetters(String language){
        File dict = new File("data/" + language.toUpperCase());
        File[] texts = null;
        if(dict.exists() && dict.isDirectory())
            texts = dict.listFiles();

        for (int i = 0; i < texts.length; i++) {
            lettersInFiles[i] = readFile(texts[i].toString());
        }
    }

    private int[] readFile(String filename){
        Map<Character, Integer> letters = new HashMap<>();
        char letter = 'a';
        for (int i = 0; i < 26; i++) {
            letters.put(letter++, 0);
        }

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = "";
            while ((line = br.readLine()) != null){
                String[] split = line.split("\\s++");
                for (String s : split){
                    for (int i = 0; i < s.length(); i++) {
                        char tmp = Character.toLowerCase(s.charAt(i));
                        if (letters.containsKey(tmp)) {
                            int counter = letters.get(tmp);
                            letters.put(tmp, counter + 1);
                        }
                    }
                }
            }
        }catch (IOException e){
            System.err.println("no such a language in our dictionary");
        }

        int [] lettersCount = convert(letters);

        return lettersCount;
    }
    private int[] convert(Map<Character, Integer> map){
        int[] arr = new int[27];
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            char key = entry.getKey();
            int count = entry.getValue();
            arr[key - 'a'] = count;
        }
        arr[26] = -1;
        return arr;
    }


    /*public void loadData() {
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
    }*/
}
