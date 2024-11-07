
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
    public void put(String word, String filePath, FilePosition pos) {
        word = word.toLowerCase();
        TreeSet<FilePosition> temp = new TreeSet<>(); //temporary treeset for ...
        FilePosition cloned = pos.clone();
        temp.add(cloned);

        //if it doesn't containt the given word
        if(!(occMap.containsKey(word))){
            TreeMap<String, TreeSet<FilePosition>> currMap = new TreeMap<>();
            currMap.put(filePath, temp);
            
            occMap.put(word, currMap);
        }else{
            TreeMap<String, TreeSet<FilePosition>> val = this.occMap.get(word);
            if(val.containsKey(filePath)){
                occMap.get(word).get(filePath).add(pos);
            }else{
                occMap.get(word).put(filePath, temp);
            }
        }
        // TODO: Implement me!!! OK!
    }


    /**
     * @return the number of distinct words found in the files
     */
    public int distinctWords() {
        return this.occMap.keySet().size();
        // TODO: Implement me!!! okay
    }

    /**
     * @return the number of total word occurrences across all files
     */
    public int totalOccurrences() {
        int counter = 0;    //to count the occurences
        String[] wordsss = this.occMap.keySet().toArray(new String[0]);
        for(String obj:wordsss){
            String[] paths = this.occMap.get(obj).keySet().toArray(new String[0]);
            for(String val: paths){
                FilePosition[] positions = this.occMap.get(obj).get(val).descendingSet().toArray(new FilePosition[0]);
                counter = counter + positions.length;
            }
        }
        return counter;
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
        int size = 0;
        if(!(this.occMap.get(word) == null)){
            String[] words = occMap.get(word).keySet().toArray(new String[0]);
            int counter = 0;
            
            for(String obj: words){
                size = occMap.get(word).get(obj).size();
                counter = counter + size;
            }
            return counter;
        }
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
        if((occMap.containsKey(word) == true) && (occMap.get(word).keySet().contains(filepath))){
            return occMap.get(word).get(filepath).size();
        }
        return 0;
    }

    @Override
    public String toString() {

        // TODO: Implement me!!!
        StringBuilder sb = new StringBuilder();
        String[] words = this.occMap.descendingKeySet().toArray(new String[0]);
        String word;
        String filepath;
        FilePosition pos = null;
        int len = words.length;
        for(int i = len - 1; i >= 0; i--){
            word = words[i];
            sb.append("\""+ word + "\" has " + totalOccurrencesOfWord(word) +
                    " occurrence(s):\n");
            String[] paths = this.occMap.get(words[i]).keySet().toArray(new String[0]);
            int len2 = paths.length;
            for(int j = len2 - 1; j >= 0; j--){
                filepath = paths[j];
                FilePosition[] positions = this.occMap.get(words[i]).get(paths[j]).descendingSet().toArray(new FilePosition[0]);
                int len3 = positions.length;
                for(int m = len3 - 1; m >= 0; m--){
                    pos = positions[m];
                    sb.append("   ( file: \"" + filepath + "\"; " + pos + " )\n");
                }
            }
        }
        return sb.toString();
    }
}


