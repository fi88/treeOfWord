package my.coding.challenge;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is for extracting word occurrence from a text file
 * In a text file, words are separated by spaces
 */
public class TextFileWordExtractor implements WordExtractor {

    private File fileToExtract;
    public TextFileWordExtractor(File file) {
        this.fileToExtract = file;
    }

    @Override
    public Map<String, Integer> extractWordOccurrence() throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileToExtract))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    if (map.containsKey(words[i])) {
                        map.put(words[i], map.get(words[i]) + 1);
                    } else {
                        map.put(words[i], 1);
                    }
                }
            }
        } catch (IOException exc) {
            throw exc;
        }
        return map;
    }
}
