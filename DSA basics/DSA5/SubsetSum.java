// Problem Description: Given a set of non-negative integers, and a value sum, 
// determine if there is a subset of the given set with sum equal to given sum.

// Input format
// N- length of array
// T- Target number
// A- a set of size n

public class SubsetSum {
    static boolean subsetSum(int[] a, int sum) {
        int n = a.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialize the dp table
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // There's always a subset with sum 0: the empty subset
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (a[i - 1] <= j) {
                    // Include the current element or not
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - a[i - 1]];
                } else {
                    // Cannot include the element because it's greater than 'j'
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // The answer is whether we can sum up to 'sum' using all elements
        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] a = { 3, 34, 4, 12, 5, 2 };
        int sum = 9; // Change this value to test other cases

        System.out.println("Is there a subset with given sum? " + subsetSum(a, sum));
    }
}

// Approach:
// We use a 2D boolean dynamic programming (DP) table where dp[i][j] indicates
// whether there exists a subset of the first i integers in the array that sums
// up to j. Here's the step-by-step logic:

// Initialization:
// dp[i][0] = true for all i, because a sum of 0 can always be formed by not
// picking any numbers from the set.
// dp[0][j] = false for all j > 0, since no sum greater than 0 can be formed
// without any numbers (except when the array contains the number 0, but we
// handle it differently).

// Transition:
// If the current number a[i-1] can be included in the subset (i.e., if a[i-1]
// <= j), then dp[i][j] can be true if dp[i-1][j] (not including a[i-1]) is true
// or dp[i-1][j-a[i-1]] (including a[i-1]) is true.

// Result:
// The value dp[n][sum] will tell whether there is a subset of the array that
// sums up to sum.
