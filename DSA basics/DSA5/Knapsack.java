// Problem Description
// There are N items, numbered 0, 2,..., N-1. For each i (0 ≤ i ≤ N-1), 
// item i has a weight of w[i] and a value of v[i].

// You have decided to choose some of the N items and carry them home in a sack.
// The capacity of the sack is W, which means that the sum of the weights of 
// items taken must be at most W.

// Find the maximum possible sum of the values of items that you can take home.

public class Knapsack {

    static long maxValue(int W, long[] weights, long[] values) {
        int N = weights.length;
        long[][] dp = new long[N + 1][W + 1];

        // Build table dp[][] in bottom up manner
        for (int i = 1; i <= N; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - (int) weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[N][W];
    }
    public static void main(String[] args) {
        int N = 3;
        int W = 50;
        long[] weights = { 10, 20, 30 };
        long[] values = { 60, 100, 120 };

        System.out.println("Maximum value we can obtain = " + maxValue(W, weights, values));
    }
}

// Pseudocode Breakdown:

// Initialize the DP table: Create a 2D DP table c where c[i][w] represents the
// maximum value that can be achieved using the first i items within the weight
// limit w.
// Base Cases:
// c[0][w] = 0 for all w: If there are no items, the maximum value is 0.
// c[i][0] = 0 for all i: If the weight capacity is 0, no items can be added, so
// the value is 0.
// DP Transition:
// If the item can fit (wi <= w), decide whether to include the item or not.
// This decision is based on whether adding the item increases the total value
// compared to not adding it.
// If the item can't fit, just carry over the value from the previous item set
// at the same weight.
