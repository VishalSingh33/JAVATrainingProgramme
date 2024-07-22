// Problem Description
// Given a non-empty array nums containing only positive integers, find if the array 
// can be partitioned into two subsets such that the sum of elements in both subsets is equal.

// Input format
// First line contains an integer n which denotes the size of the input array.
// The next n lines contain n integers denoting the elements of the array.

public class EqualSumPartition {

    public static int canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        // If the total sum is odd, it cannot be partitioned into two equal sum subsets
        if (total % 2 != 0)
            return 0;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: sum of 0 is always possible

        // Update the DP array for each number in the array
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target] ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 5, 11, 5 };
        System.out.println("Can partition: " + canPartition(nums)); // Output: 1
    }
}

// The function now returns 1 if the partition is possible and 0 if it's not, in
// accordance with the expected output format.
// The rest of the logic remains the same, with a dynamic programming array dp
// where dp[j] signifies whether a subset sum of j is achievable.
// We start from dp[0] = true (zero sum is always achievable with an empty
// subset) and attempt to fill up the array up to the target which is half of
// the total sum.
// If dp[target] ends up being true, it indicates that it's possible to
// partition the array into two subsets with equal sums, thereby allowing a
// valid split as described in the problem.