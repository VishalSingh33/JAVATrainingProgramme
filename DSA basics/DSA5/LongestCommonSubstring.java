import java.util.Scanner;
// Problem Description
// You have been given two strings 's1' and 's2'. You have to find the 
// length of the longest common substring.

// A string "str1" is a substring of another string “str2” if “str2” contains the 
// same characters as in “str1”, in the same order and in continuous fashion also.

// Input format
// First line of input contains T which denotes the number of test cases.

// The first line of input of every test case contains the 2 strings for which
// you need to find the length of the longest common substring.

public class LongestCommonSubstring {

    public static int longestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of test cases
        scanner.nextLine(); // Consume the newline character
        // abcjklp acjkp
        // wasdijkl wsdjkl

        for (int t = 0; t < T; t++) {
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            System.out.println(longestCommonSubstring(s1, s2));
        }

        scanner.close();
    }
}

// Dynamic Programming Table:
// Create a 2D DP array dp where dp[i][j] represents the length of the
// longest common substring that ends at s1[i-1] and s2[j-1].
// Initialize the DP array with zeros.

// Filling the DP Table:
// Iterate through each character of s1 and s2.
// If s1[i-1] equals s2[j-1], then dp[i][j] = dp[i-1][j-1] + 1.
// Otherwise, set dp[i][j] to 0 since the characters do not match and cannot
// form a substring.
// Keep track of the maximum value in the DP table, which will be the length
// of the longest common substring.

// Iterative Approach:
// Using nested loops, fill in the DP table based on the conditions mentioned
// above.
// The final result will be the maximum value in the DP table.
