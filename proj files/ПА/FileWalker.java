
package word_occurrences;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


final class FileWalker {

    private final Occurrences occ;
    private final File rootDir;

    FileWalker(String rootDirPath, Occurrences occ) throws FileNotFoundException {
        this.occ = occ;
        this.rootDir = new File(rootDirPath);

        if (!this.rootDir.isDirectory()) {
            throw new FileNotFoundException(rootDirPath + " does not exist, " +
                    "or is not a directory.");
        }
    }

    void populateOccurrenceMap() {
        try {
            populateOccurrenceMap(rootDir);
        } catch (IOException ex) {
            // We should never really get to this point, but just in case...
            System.out.println(ex);
        }
    }

    private void populateOccurrenceMap(File fileOrDir) throws IOException{
        String paths;
        String dat = "";
        char new_line = '\n';
        int line = 1;
        int column = 1;
        int blank;
        int ch = 0;

        if ((fileOrDir.isDirectory() == true) && !(fileOrDir.listFiles() == null)) {
            for (File contents: fileOrDir.listFiles()) {
                populateOccurrenceMap(contents);
            }
        }
        if (!(fileOrDir.isFile() == false)) {
            paths = fileOrDir.getPath();
            BufferedReader reader = new BufferedReader(new FileReader(paths));

            while(!(ch == -1)) {
                ch = reader.read();
                if (Syntax.isInWord((char)ch) == false) {
                    if (!(dat.length() <= 0)) {
                        FilePosition position = new FilePosition(line, column);
                        blank = column - dat.length();
                        column += column - blank;
                        occ.put(dat, paths, position);
                        dat = "";
                    }
                    column = column + 1;
                }

                if (Syntax.isInWord((char)ch) == true) {
                    dat = dat + (char)ch;
                }
                if (ch == new_line) {
                    line += 1; 
                    column = 1;
                    dat = "";
                }

            }
            reader.close();
        }
        /*BufferedReader reader = new BufferedReader(new FileReader(fileOrDir));
        while(ch != -1){
            ch = reader.read();
            if(Syntax.isInWord((char)ch)){
                dat = dat + (char)ch;
                column += 1;
            }else{
                if(!dat.equals("")){
                    occ.put(dat.toLowerCase(), fileOrDir.getPath(), new FilePosition(line, column - dat.length()));
                    dat = "";
                }if(Syntax.isNewLine((char)ch)){
                    line += 1;
                    column = 1;
                }else{
                    column = column + 1;
                }

        */
    }
}


