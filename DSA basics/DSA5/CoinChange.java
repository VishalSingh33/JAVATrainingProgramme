// Problem Description
// Given an amount A and the denominations of coins available, determine how many ways there
// are to make change for the amount. Assume that there is a limitless supply of each coin type.

// Input format
// First line contains 2 integers A and N - the amount of money you need to make change
// for, and the number of coins respectively.
// Second line contains N integers - the denominations of the coins available.

public class CoinChange {

    public static long coinChange(int amount, int n, int[] coins) {
        long[] dp = new long[amount + 1];
        dp[0] = 1; // One way to make amount 0 - no coins.

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 10; // Amount to make change for
        int[] coins = { 1, 2, 5 }; // Coin denominations
        System.out.println(
                "Ways to make change for " + amount + ": " + coinChange(amount, coins.length, coins));
        // Example output should show the number of ways to make change for 10 using 1,
        // 2, and 5 denominations.
    }
}

// Key Idea:

// Start by initializing an array dp with a length of amount + 1 and set all its
// values to 0 except for dp[0], which should be set to 1. The base case dp[0] =
// 1 represents the fact that there is exactly one way to make up the amount 0:
// using no coins.
// For each coin in the list of denominations, update the dp array such that for
// each value from the coin's value up to the amount, add the number of ways to
// make change for amount - coin to dp[amount].
// This process effectively builds up the number of ways to make change for all
// amounts from 1 to A using all available coins.
