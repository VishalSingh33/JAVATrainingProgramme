import java.util.*;

// Problem Description
// Given a list of strings words representing an English Dictionary, find the 
// longest word in words that can be built one character at a time by other words in words.

// If there is more than one possible answer, return the longest word with 
// the smallest lexicographical order.

// If there is no answer, return the empty string.

// Input format
// First line contains a single integer n - the number of words in the dictionary.
// Second line contains n space separated words.

public class LongestWordInDictionary {
    public static String longestWordInDictionary(int n, String[] words) {
        Arrays.sort(words);
        Set<String> validWords = new HashSet<>();
        String longestWord = "";

        for (String word : words) {
            if (word.length() == 1 || validWords.contains(word.substring(0, word.length() - 1))) {
                validWords.add(word);
                if (word.length() > longestWord.length() ||
                        (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)) {
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        String[] words = scanner.nextLine().split(" ");

        String result = longestWordInDictionary(n, words);
        System.out.println(result);

        scanner.close();
    }
}

// Explanation:
// Input Reading: The main method reads the input values and splits the words
// into an array.
// Sorting: The words array is sorted lexicographically. This ensures that if
// two words have the same length, the lexicographically smaller one is
// considered first.
// Set for Valid Words: A set named validWords is used to keep track of words
// that can be built one character at a time.
// Checking Words: For each word, check if it is of length 1 or if its prefix
// (word without the last character) exists in the set of valid words.
// Updating Longest Word: If the current word is valid, add it to the set and
// update the longestWord if it is longer or lexicographically smaller than the
// current longest valid word.
