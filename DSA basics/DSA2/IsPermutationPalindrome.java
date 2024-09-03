import java.util.HashMap;
import java.util.Map;

public class IsPermutationPalindrome {
    public static void main(String[] args) {

        // Given a string S which consists of both lowercase and uppercase alphabetical
        // letters, you have to write a function to check if string S is a permutation
        // of a palindrome or not. Note: Characters are case sensitive i.e. ‘a’ is not
        // the same as ‘A’.
        String s = "nnaamm";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int[] charCount = new int[128];

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        int oddfreq = 0;
        for (int count : frequencyMap.values()) {
            if (count % 2 != 0) {
                oddfreq++;
            }
        }
        if (oddfreq > 1) {
            // return 0;
            System.out.println(0);
        }
        // return 1;
        System.out.println(1);

    }

}
