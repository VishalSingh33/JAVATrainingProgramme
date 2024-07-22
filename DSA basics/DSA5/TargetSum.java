import java.util.Arrays;

// Problem Description: You are given an integer array nums and an integer target.

// You want to build an expression out of nums by adding one of the 
// symbols '+' and '-' before each integer in nums and then concatenate all the integers.

// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and 
// concatenate them to build the expression "+2-1".

// Return the number of different expressions that you can build, which evaluates to target.

// Input format
// First line of input contains 2 integers, n and target denoting the number of elements 
// in the integer array and the target to be achieved.
// The next n lines contain the n elements of the integer array.

public class TargetSum {

    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums)
            sum += num;

        // Early exit if absolute value of target S is greater than the sum of all
        // numbers.
        if (S > sum || S < -sum)
            return 0;

        // We need a memoization table where index will represent current index in nums
        // and sumIndex will represent the adjusted sum index.
        int[][] memo = new int[nums.length][2 * sum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return calculateWays(nums, 0, 0, S, sum, memo);
    }

    private static int calculateWays(int[] nums, int index, int currentSum, int target, int totalSum, int[][] memo) {
        if (index == nums.length) {
            if (currentSum == target)
                return 1;
            else
                return 0;
        } else {
            // Adjust currentSum index to fit in memo array
            int sumIndex = currentSum + totalSum; // Shift index to positive
            if (memo[index][sumIndex] != Integer.MIN_VALUE) {
                return memo[index][sumIndex];
            }

            // Calculate the number of ways by adding and subtracting current number
            int add = calculateWays(nums, index + 1, currentSum + nums[index], target, totalSum, memo);
            int subtract = calculateWays(nums, index + 1, currentSum - nums[index], target, totalSum, memo);

            // Memoize and return result
            memo[index][sumIndex] = add + subtract;
            return memo[index][sumIndex];
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 1, 1 };
        int target = 3;
        System.out.println("Number of ways: " + findTargetSumWays(nums, target));
    }
}

// Given that you need to handle possibly negative sums due to the subtraction
// operations, and the constraints are not specified, we'll proceed with a
// dynamic programming approach using a memoization technique to optimize it.

// Step-by-step Solution
// Calculate the Total Sum: Start by calculating the total sum of the array to
// determine the range of possible sums.
// Adjust Indexing for Negative Sums: To handle negative indices in the array,
// you can shift the indices to make them all non-negative. This is achieved by
// adding the total sum to the indices to map them in the [0, 2 * sum] range.
// Define a Recursive Function with Memoization: This function will explore both
// adding and subtracting the current number and use a memoization array to
// store previously computed results for specific combinations of current index
// and running sum.
