package word_occurrences;

/**
 * Immutable class used to represent a position in a file,
 * which includes the line and column numbers.  The fields
 * line and column are publicly accessible, though cannot
 * be changed.
 */
public final class FilePosition implements Comparable<FilePosition> {

    final public int line;
    final public int column;

    /**
     * Create a new Position with the given line and column.
     * The first line of a file is 1, and the first column is 1.
     *
     * @param line number of the given Position
     * @param column number of the given Position
     */
    public FilePosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    /**
     * Creates a copy of the given Position.
     *
     * @return a copy of the object
     */
    public FilePosition clone() {
        return new FilePosition(line, column);
    }

    /**
     * @return String representation of the Position
     */
    public String toString() {
        return "line " + line + ", column " + column;
    }


    /**
     * Determines whether this Position occurs before pos,
     * assuming both refer to the same file.
     *
     * @param pos the other Position to compare
     * @return a negative number if this occurs before pos,
     *   a positive number if pos occurs before this, and 0 if
     *   the two are the same
     */
    public int compareTo(FilePosition pos) {

        if (this.line < pos.line) return -1;
        if (this.line > pos.line) return 1;

        // if we get to this point, the lines are equal
        if (this.column < pos.column) return -1;
        if (this.column > pos.column) return 1;

        // If we get to this point, both lines and columns are equal
        return 0;
    }

}


