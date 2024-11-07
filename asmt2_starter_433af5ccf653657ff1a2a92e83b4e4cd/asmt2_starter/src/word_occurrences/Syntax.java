
package word_occurrences;

import java.lang.Character;

/**
 * Utility class that provides methods to help determine
 * which characters will be allowed in a word by our
 * application.
 */
public final class Syntax {

    /**
     * Prevents anyone from trying to actually create a
     * Syntax object.
     */
    private Syntax() { }

    /**
     * Returns true if and only if the given character
     * counts as a character of a word in our application
     *
     * @param ch the character to check
     * @return true if ch should belong to a word
     */
    public static boolean isInWord(char ch) {

        // Apostrophe and hyphen cases
        if (ch == 0x2019 || ch == 0x27 || ch == '-')
            return true;

        // Digit cases
        if ('0' <= ch && ch <= '9')
            return true;

        // Alphabetic cases:
        return Character.isLetter(ch);
    }

    /**
     * Returns true if and only if the given character
     * counts as a newline character in our application
     *
     * @param ch the character to check
     * @return true if ch is a newline
     */
    public static boolean isNewLine(char ch) {
        return ch == '\n';
    }

}


