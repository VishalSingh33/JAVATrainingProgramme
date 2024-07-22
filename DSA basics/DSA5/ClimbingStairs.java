// Problem Description: You are climbing a staircase. It takes n steps to reach to the top.

// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
// Note: Given n will be a positive integer.

// Input format
// A single Integer N, that contains the number of steps in the stair.
// Output format
// Print the number of distinct ways you can climb to the top.

class ClimbingStairs {

    private int climbHelper(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbHelper(n - 1) + climbHelper(n - 2);
    }

    public int climbingStairs(int n) {
        return climbHelper(n);
    }

    public static void main(String[] args) {
        ClimbingStairs stairs = new ClimbingStairs();
        int n = 5; // Example input
        System.out.println("Number of distinct ways to climb " + n + " steps: " + stairs.climbingStairs(n));
    }
}
// public int climbingStairs(int n) {
// if (n <= 1) {
// return 1;
// }
// int[] dp = new int[n + 1];

// dp[0] = 1;
// dp[1] = 1;
// for (int i = 2; i <= n; i++) {
// dp[i] = dp[i - 1] + dp[i - 2];
// }
// return dp[n];
// }
