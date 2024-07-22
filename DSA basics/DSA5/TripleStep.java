// Problem Description
// A child is running up a staircase with N steps. 
// The child can hop either 1 step, 2 steps or 3 steps at a time. 
// Can you write a program to count the number of possible ways in which the 
// child can run up the staircase.

// Input format
// Single line with N, number of steps
// Output format
// Number of different ways to climb, mod 1000000007

public class TripleStep {
    // Modulus constant
    private static final int MOD = 1000000007;

    int numberOfWays(int n) {
        if (n == 0)
            return 1; // One way to do nothing
        if (n == 1)
            return 1; // One way to step one step
        if (n == 2)
            return 2; // Two ways to step two steps (1+1, 2)

        // Dynamic programming array
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }

        return (int) dp[n];
    }

    public static void main(String[] args) {
        TripleStep steps = new TripleStep();
        int n = 4; // Example, change this value for different inputs
        System.out.println("Number of ways to climb " + n + " steps: " + steps.numberOfWays(n));
    }
}

// Approach:
// The number of ways to reach step n is the sum of the ways to reach the three
// preceding steps (n-1, n-2, n-3), since the child can jump from any of those
// steps to step n. This gives us the recurrence relation:
// dp[n]=dp[n−1]+dp[n−2]+dp[n−3]

// Initial Conditions:
// dp[0]=1: There is one way to be on the ground (do nothing).
// dp[1]=1: Only one way to reach the first step (a single step).
// dp[2]=2: Two ways to reach the second step (1+1 or 2).