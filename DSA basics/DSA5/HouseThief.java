// Problem Description
// An infamous thief goes by the name of Kalia. He is famous for his sheer 
// skills in the field of burglary.

// His next target is a colony containing houses with enormous wealth.
// A given input array represents the wealth of each house in the colony.

// He wants to steal the maximum possible wealth in one go, but needs to be cautious.
// He cannot steal from consecutive houses as that would trigger the security alarm.

// You are his sidekick. Help him find the maximum wealth he can steal.

// Input format
// First line contains a number N denoting the number of houses. Next line
// contains N space separated numbers where the ith number denotes the wealth in ith house.
// Output format
// A single number denoting the maximum wealth Kalia can steal.

public class HouseThief {
    public static long houseThief(int n, int[] arr) {
        // Base cases
        if (n == 0)
            return 0; // No houses
        if (n == 1)
            return arr[0]; // Only one house
        if (n == 2)
            return Math.max(arr[0], arr[1]); // Two houses, pick the one with max wealth

        // DP array to store the maximum wealth up to house i
        long[] dp = new long[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        // Fill the DP array according to the relation
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
        }

        // The last element will contain the maximum wealth we can steal
        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Example usage:
        int n = 4; // Number of houses
        int[] wealth = { 2, 3, 2, 5 }; // Wealth in each house
        System.out.println("Maximum wealth Kalia can steal: " + houseThief(n, wealth));
    }
}

// To solve this problem, you can create a dynamic programming array where each
// element dp[i] represents the maximum amount of wealth that can be stolen from
// the first i houses. The relationship you need to leverage is:

// dp[i]=max(dp[i−1],arr[i]+dp[i−2])

// Where:

// dp[i−1] represents not stealing from the current house and thus the maximum
// wealth is the same as not considering this house.

// arr[i]+dp[i−2] considers the scenario where you steal from the current house,
// hence you can't steal from the house right before it but you can add the
// wealth of the current house to the maximum wealth from all houses before the
// previous one.
