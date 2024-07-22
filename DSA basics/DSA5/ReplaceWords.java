import java.util.*;

// Problem Description
// In English, we have a concept called root words, which can be followed by some 
// other letters to form a longer word. Let's call these words successor words. 
// For example, the root word "an", followed by the letters “other”, gives us the 
// successor word “another”.

// Now, given a dictionary consisting of many root words and a sentence, you need to 
// replace all the successor words in the sentence with the root words forming them. 
// If a successor word has many root words, replace it with the root word of the shortest length.

// You need to output the sentence after the replacement.

// Input format
// The first line contains N, representing the number of words in the dictionary
// Next line contains N space separated strings representing the words in the dictionary
// Next line contains the sentence which needs to undergo the replacements

public class ReplaceWords {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private static void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word;
    }

    private static String findRoot(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                break;
            }
            node = node.children[index];
            if (node.word != null) {
                return node.word;
            }
        }
        return word;
    }

    public static String replaceWords(ArrayList<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            insert(root, word);
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(findRoot(root, word));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ArrayList<String> dict = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dict.add(scanner.next());
        }
        scanner.nextLine(); // Consume newline

        String sentence = scanner.nextLine();

        scanner.close();

        System.out.println(replaceWords(dict, sentence));
    }
}

// Explanation:
// TrieNode Class: Represents a node in the Trie, with an array of children
// nodes and a word property.
// insert Method: Inserts a word into the Trie.
// findRoot Method: Finds the shortest root word in the Trie that is a prefix of
// the given word. If no such root word is found, returns the original word.
// replaceWords Method: Inserts all root words from the dictionary into the
// Trie, then replaces each word in the sentence with the shortest root word
// found in the Trie.
// main Method: Reads the input, calls the replaceWords method, and prints the
// result.