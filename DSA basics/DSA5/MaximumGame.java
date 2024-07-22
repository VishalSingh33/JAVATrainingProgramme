import java.util.Scanner;

// Problem Description
// Three friends went on to play a team game. The game has n boxes and inside each 
// box there are some coins. They have to choose some boxes and they will get the 
// amount of coins present in that box. The only constraint the game has is that 
// no two adjacent boxes can be chosen. The friends want to maximise the number of 
// coins they win. Print the maximum number of coins they can get.

// Input format
// First line contains a single integer n representing the number of boxes available.
// Second line contains n space seperated integers representing the number of coins in the ith box.

public class MaximumGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(maximumGame(nums, n));
        sc.close();
    }

    static int maximumGame(int[] nums, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }

        // Create a dp array to store the maximum coins collected up to the i-th box
        int[] dp = new int[n];

        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill the dp array
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        // The result is the maximum coins collected up to the last box
        return dp[n - 1];
    }
}

// Explanation:
// Input Reading:

// The main method reads the integer n and the array nums which represents the
// number of coins in each box.

// Base Cases:
// If there are no boxes (n == 0), return 0.
// If there is only one box (n == 1), return the coins in that box (nums[0]).

// DP Initialization:
// Initialize the dp array where dp[0] is nums[0] and dp[1] is the maximum of
// the first two boxes (Math.max(nums[0], nums[1])).

// DP Transition:
// For each box from the 2nd to the last, calculate dp[i] using the formula
// Math.max(dp[i-1], nums[i] + dp[i-2]).

// Result:
// The last element of the dp array (dp[n-1]) contains the maximum coins that
// can be collected.