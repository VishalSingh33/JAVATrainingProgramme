// Problem Description: Given a string S, find the length of longest subsequence which is 
// repeated in the string. The two identical subsequences cannot have a character at the same 
// position in the original string.

// Input format
// You will be given a string S

public class LongestRepeatedSubsequence {

    public static void main(String[] args) {
        String str = "crivcrioo"; // Sample input
        System.out.println(longestRepeatedSubsequence(str));
    }

    public static int longestRepeatedSubsequence(String str) {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }
}

// Explanation
// Initialization:
// dp[i][j] is initialized to 0 for all i and j since if one of the substrings is empty, 
// the length of the longest repeated subsequence is 0.

// Filling the DP Table:
// For each pair of indices (i, j) in the string, we check if 
// str.charAt(i - 1) == str.charAt(j - 1) and i != j. If true, it means we found 
// a repeating character at different positions, so we increment the value from dp[i-1][j-1].
// If the characters are not equal or i == j, we take the maximum value from either 
// ignoring the character from the first substring or the second substring (dp[i-1][j] or dp[i][j-1]).

// Result Extraction:
// The length of the longest repeated subsequence is found at dp[n][n].
// This approach efficiently computes the solution with a time complexity 
// of O(n^2) and a space complexity of O(n^2), which is feasible for reasonably large strings.