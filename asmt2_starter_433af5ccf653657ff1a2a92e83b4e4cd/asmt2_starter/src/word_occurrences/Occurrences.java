
package word_occurrences;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Occurrences {

    // Maps words -> filename -> sets of their Positions in the file.
    private final TreeMap<String, TreeMap<String, TreeSet<FilePosition>>> occMap;

    public Occurrences(String rootDirPath) throws FileNotFoundException {
        occMap = new TreeMap<>();
        FileWalker walker = new FileWalker(rootDirPath, this);
        walker.populateOccurrenceMap();
    }

    /*
        Called by FileWalker to add an occurrence to the occMap
     */
    void put(String word, String filePath, FilePosition pos) {

        // TODO: Implement me!!!
    }


    /**
     * @return the number of distinct words found in the files
     */
    public int distinctWords() {

        // TODO: Implement me!!!
        return 0;
    }

    /**
     * @return the number of total word occurrences across all files
     */
    public int totalOccurrences() {

        // TODO: Implement me!!!
        return 0;
    }

    /**
     * Finds the total number of occurrences of a given word across
     * all files.  If the word is not found among the occurrences,
     * simply return 0.
     *
     * @param word whose occurrences we are counting
     * @return the number of occurrences
     */
    public int totalOccurrencesOfWord(String word) {

        // TODO: Implement me!!!
        return 0;
    }

    /**
     * Finds the total number of occurrences of a given word in the given file.
     * If the file is not found in Occurrences, or if the word does not occur
     * in the file, simply return 0.
     *
     * @param word whose occurrences we are counting
     * @param filepath of the file
     * @return the number of occurrences
     */
    public int totalOccurrencesOfWordInFile(String word, String filepath) {

        // TODO: Implement me!!!
        return 0;
    }

    public String toString() {

        // TODO: Implement me!!!
        StringBuilder sb = new StringBuilder();

        String word = "some_word";
        String filepath = "some\\file\\path.txt";
        FilePosition pos = null;

        sb.append("\""+ word + "\" has " + totalOccurrencesOfWord(word) +
                    " occurrence(s):\n");

        sb.append("   ( file: \"" + filepath + "\"; " + pos + " )\n");

        return sb.toString();
    }
}


