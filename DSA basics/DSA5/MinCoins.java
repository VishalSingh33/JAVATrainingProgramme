// Problem Description: Given a list of N coin denominations and a total sum S. 
// Return the minimum number of coins from the given denominations required to
// form the given sum or report that it's not possible to select coins in such
// a way that they sum upto S.

// You can assume you have an infinite number of each kind of coin.

// Input format
// An integer array coins representing the denominations of coins you have, 
// and an integer S, representing the amount of money you need to form using the given coins.

// In the sample input, the first line contains two numbers - N and amount, 
// denoting the number of different coins you have and the amount you want to
//  form respectively. The second line contains N integers - the denominations of coins you have.

import java.util.Arrays;

public class MinCoins {
    public static int minCoins(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        // Initialize dp array with a large value (representing infinity)
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // Iterate through each coin
        for (int coin : coins) {
            // Update the dp array for all amounts from coin value to target amount
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // If dp[amount] is still the large value, return -1 indicating no solution
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // Sample input
        int[] coins = { 1, 2, 5 };
        int amount = 11;
        System.out.println("Minimum number of coins required: " + minCoins(coins, amount));

        // Another sample input
        int[] coins2 = { 2 };
        int amount2 = 3;
        System.out.println("Minimum number of coins required: " + minCoins(coins2, amount2));
    }
}

// Dynamic Programming Approach:
// Use a DP array dp where dp[i] represents the minimum number of coins required
// to make up the amount i.
// Initialize the dp array with a large value (representing infinity) except for
// dp[0], which should be 0 because zero coins are needed to make the amount 0.
// For each coin in the list, iterate through the possible amounts from the coin
// value to the target amount and update the DP array accordingly.

// Iterative Approach:
// Iterate through each coin and for each coin, update the DP array for all
// amounts from the coin value up to the target amount.
// For each amount, update the DP value to be the minimum of its current value
// or the value of the amount minus the coin value plus one (indicating the use
// of one more coin).
// Here is the Java implementation of the solution: