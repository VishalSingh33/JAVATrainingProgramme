// Problem Description: Given two words word1 and word2, find the minimum number of operations 
// required to convert word1 to word2.

// You have the following 3 operations permitted on a word:
// Insert a character
// Delete a character
// Replace a character

// Input format
// First line will have word1.
// Second line will have word2.

public class EditDistance {
    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize dp array
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the dp array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, // Replace
                            Math.min(dp[i][j - 1] + 1, // Insert
                                    dp[i - 1][j] + 1)); // Delete
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        // Sample input
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Minimum number of operations required: " + editDistance(word1, word2));
    }
}

// Dynamic Programming Table:

// Create a 2D DP array dp where dp[i][j] represents the minimum number of
// operations required to convert the first i characters of s1 to the first j
// characters of s2.
// Initialization:

// dp[0][0] = 0 because no operations are needed to convert an empty string to
// another empty string.
// dp[i][0] = i because converting the first i characters of s1 to an empty
// string requires i deletions.
// dp[0][j] = j because converting an empty string to the first j characters of
// s2 requires j insertions.
// Filling the DP Table:

// Iterate through each character of s1 and s2.
// If the characters s1[i-1] and s2[j-1] are equal, then no new operation is
// needed: dp[i][j] = dp[i-1][j-1].
// If the characters are not equal, consider three possible operations and take
// the minimum:
// Insert: dp[i][j-1] + 1
// Delete: dp[i-1][j] + 1
// Replace: dp[i-1][j-1] + 1
// Result:

// The value at dp[m][n] (where m is the length of s1 and n is the length of s2)
// gives the minimum number of operations required to convert s1 to s2.