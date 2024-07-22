import java.util.Scanner;
import java.util.Vector;

// Problem Description: Given a list of integers, find the length 
// of the longest increasing subsequence.

// Input format
// First line contains an integer N - Number of integers in the list.
// Second line contains N integers.

public class LongestIncreasingSubsequence {

    private static int longestIncreasingSubsequence(Vector<Integer> vec) {
        int n = vec.size();
        int[] dp = new int[n];

        // Initialize all values in dp to 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (vec.get(i) > vec.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the maximum value in dp
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of integers
        int N = scanner.nextInt();

        // Read the integers into a vector
        Vector<Integer> vec = new Vector<>();
        for (int i = 0; i < N; i++) {
            vec.add(scanner.nextInt());
        }

        // Find and print the length of the longest increasing subsequence
        System.out.println(longestIncreasingSubsequence(vec));

        scanner.close();
    }
}

// Dynamic Programming Array:
// Create a DP array dp where dp[i] represents the length of the longest
// increasing subsequence that ends with the element at index i.

// Initialization:
// Initialize all values in the dp array to 1, because the minimum length of the
// increasing subsequence for any single element is 1 (the element itself).

// Filling the DP Table:
// Iterate through each element in the list.
// For each element at index i, check all previous elements (from 0 to i-1). If
// an element at index j is less than the element at index i, update dp[i] to be
// the maximum of dp[i] or dp[j] + 1.

// Result:
// The length of the longest increasing subsequence will be the maximum value in
// the dp array.