// Problem Description
// Given an array of non-negative integers, you are initially positioned at the first position of the array. Each element in the array represents your maximum jump length at that position.

// Determine if you are able to reach the last position.

// Input format
// First line contains N, the size of the array

// Second line contains N space separated integers representing the array

public class JumpGame {
    public String canJump(int[] nums) {
        // Initial check if array is empty or has one element
        if (nums == null || nums.length == 0) {
            return "false";
        }

        // Initialize the farthest position we can reach
        int farthest = 0;

        // Iterate over each position in the array
        for (int i = 0; i < nums.length; i++) {
            // If the current position is beyond the farthest reachable, break early
            if (i > farthest) {
                return "false";
            }

            // Update the farthest position reachable
            farthest = Math.max(farthest, i + nums[i]);

            // If at any point the farthest position reaches or exceeds the last index
            if (farthest >= nums.length - 1) {
                return "true";
            }
        }

        // If we exit the loop without having reached the last index
        return "false";
    }

    public static void main(String[] args) {
        JumpGame game = new JumpGame();
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(game.canJump(nums)); // Output: "true"

        int[] nums2 = { 3, 2, 1, 0, 4 };
        System.out.println(game.canJump(nums2)); // Output: "false"
    }
}

// The algorithm initializes the farthest variable to zero.
// It iterates over each element in the array. If the current index i is greater
// than the farthest reachable index, it returns "false" immediately because
// it's not possible to proceed further.
// On each iteration, it updates farthest with the maximum of its current value
// and the sum of the current index i and the jump length nums[i].
// If at any point farthest reaches or exceeds the last index of the array, it
// returns "true" indicating it's possible to reach the end.
// After the loop, if the end of the array has not been reached, it returns
// "false".
// This implementation offers a time complexity of

// O(n), where n is the number of elements in the array, which is optimal for
// this problem.
