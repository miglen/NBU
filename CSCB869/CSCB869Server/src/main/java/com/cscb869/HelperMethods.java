package com.cscb869;

/**
 *
 * @author Miglen Evlogiev
 */
public class HelperMethods {

    /**
     * This method returns the number of words in a string.
     * @param String input - the input string
     * @return int words.length - the length of words in the input string.
     */
    public static int getNumberOfWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.trim()
                .replaceAll("[^\\w\\s]", "") // remove all non letter symbols
                .replaceAll("(^|\\s+)[a-zA-Z](\\s+|$)", " ") // remove single characters
                .split("\\s+");
        return words.length;
    }

    /**
     * This methods returns the number of printable unique characters in a string.
     * @param input - the input string
     * @return long output - the number of printable characters in the input.
     */
    public static long getNumberOfSymbols(String input) {
        return input.chars()
                .distinct()
                .count();
    }

    /**
     * This methods inverts the input string.
     * @param String input - the input string
     * @return String output - the inverted string.
     */
    public static String invertString(String input) {
        String output = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            output += (input.substring(i, i + 1));
        }
        return output;
    }

    /**
     * This methods returns the location of the substring needle in the input
     * string. If there's nothing found returns -1
     * @param input
     * @param needle
     * @return int - the location of the substring needle in input string.
     */
    public static int findSubstr(String input, String needle) {
        if (needle.length() > input.length() || input.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i <= input.length() - needle.length(); i++) {
                if (input.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
