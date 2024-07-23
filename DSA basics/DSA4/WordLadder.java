import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Given two words (beginWord and endWord) and a dictionary of words,
// find the length of the shortest transformation sequence from beginWord to endWord, such that:
// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not a transformed 
// word and may not exist in the word list.

// Note:
// Return 0 if there is no such transformation sequence
// All words have the same length
// All words contain only lowercase alphabetic characters
// You may assume no duplicates in the word list
// You may assume beginWord and endWord are non-empty and are not the same

public class WordLadder {
    // BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // End word not in the word list, no transformation sequence possible
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char originalChar = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (charArray[j] == c) {
                            continue;
                        }
                        charArray[j] = c;
                        String transformedWord = new String(charArray);
                        if (transformedWord.equals(endWord)) {
                            return level + 1;
                        }
                        if (wordSet.contains(transformedWord)) {
                            queue.offer(transformedWord);
                            wordSet.remove(transformedWord); // Mark as visited
                        }
                    }
                    charArray[j] = originalChar; // Restore the character
                }
            }
            level++;
        }

        return 0; // No transformation sequence found
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(wordLadder.ladderLength("hit", "cog", wordList)); // Output: 5
    }
}
