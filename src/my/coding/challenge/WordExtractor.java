package my.coding.challenge;

import java.io.IOException;
import java.util.Map;

public interface WordExtractor {
    Map<String, Integer> extractWordOccurrence() throws IOException;
}
