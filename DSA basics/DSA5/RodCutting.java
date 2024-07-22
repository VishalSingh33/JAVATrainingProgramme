//Problem Description: Given a rod of length N inches and an array of prices, 
// price[] that contains prices of all pieces of size smaller than N. 
// Determine the maximum value obtainable by cutting up the rod and selling the pieces.

// Input format
// N - length of the rod
// then follow n integers corresponding to the prices of ith length piece

public class RodCutting {

    public static int rodCutting(int n, int price[]) {
        int[] dp = new int[n + 1];

        // Iterate over each rod length from 1 to n
        for (int i = 1; i <= n; i++) {
            int maxVal = 0;
            // Try cutting the rod into pieces of length j and find the maximum value
            for (int j = 1; j <= i; j++) {
                maxVal = Math.max(maxVal, price[j - 1] + dp[i - j]);
            }
            dp[i] = maxVal;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // Sample input
        int n = 5;
        int[] price = { 1, 9, 3, 3, 3 };
        System.out.println("Maximum obtainable value: " + rodCutting(n, price));
    }
}

// Problem Breakdown:
// We are given a rod of length N and an array price where price[i] represents
// the price of a piece of rod of length i+1.
// We need to determine the maximum value obtainable by cutting the rod into
// pieces and selling them.

// Dynamic Programming Approach:
// Create a DP array dp where dp[i] represents the maximum value obtainable for
// a rod of length i.
// Initialize dp[0] to 0 because a rod of length 0 has no value.
// For each length from 1 to N, calculate the maximum value by trying to cut the
// rod into all possible lengths and taking the best option.