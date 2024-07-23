import java.util.ArrayList;
import java.util.List;

// Given a string s, partition s into substrings such that each substring is a palindrome.
// Return all possible palindrome partitioning of s.

public class PalindromePartitioning {
    public List<List<String>> palindromePartitioning(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        backtrack(result, currentList, s, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> currentList, String s, int start) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentList.add(s.substring(start, end + 1)); // Add the palindrome substring
                backtrack(result, currentList, s, end + 1); // Recursive call with updated start index
                currentList.remove(currentList.size() - 1); // Backtrack (remove the last substring)
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        String s = "aab";
        List<List<String>> partitions = pp.palindromePartitioning(s);
        for (List<String> partition : partitions) {
            System.out.println(partition);
        }
    }
}
