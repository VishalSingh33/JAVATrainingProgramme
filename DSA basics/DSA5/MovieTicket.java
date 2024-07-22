import java.util.Arrays;
import java.util.Scanner;

// Problem Description
// Rohan is a guy who has N different types of books. Given an integer array which 
// represents the selling price of each type of book. He has an infinite supply of 
// each type of book. Rohan wants to buy a movie ticket, which has a cost of K dollars. 
// Since he has got no money, he decided to sell some of his books. Print the minimum 
// number of books he needs to sell to get exactly K dollars. If the exact amount of 
// money cannot be made by any combination of the books print -1.

// Note: He needs exactly K dollars, not more than that.

// Input format
// First line contains an integer.(Number of types of books)
// Second line contains n space separated integers.(Selling price of each type of book)
// Third line contains an integer.(Cost of the movie ticket)

public class MovieTicket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(movieTicket(n, array, k));
        sc.close();
    }

    static int movieTicket(int n, int[] array, int k) {
        int[] dp = new int[k + 1];

        // Initialize dp array with a large number (infinity)
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case: 0 books needed to make 0 dollars

        // Fill the dp array
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= array[j] && dp[i - array[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - array[j]] + 1);
                }
            }
        }

        // If dp[k] is still infinity, it means we cannot make exactly k dollars
        return dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
    }
}

// Explanation:
// Input Reading:

// The main method reads the number of book types n, the array array which
// represents the selling prices of each book, and the cost of the movie ticket
// k.

// Initialize DP Array:
// The dp array is initialized with a large value (Integer.MAX_VALUE)
// representing that initially, we assume it's impossible to make that amount.
// dp[0] is set to 0 because no books are needed to make 0 dollars.

// Fill the DP Array:
// For each amount i from 1 to k, iterate through each book price.
// If the current amount i is at least the current book price and dp[i -
// array[j]] is not infinity, update dp[i] to the minimum of its current value
// or dp[i - array[j]] + 1.

// Result Check:
// If dp[k] is still infinity, it means it's not possible to make exactly k
// dollars with the given book prices. Otherwise, return dp[k], the minimum
// number of books needed.
// This solution ensures that we efficiently find the minimum number of books
// required to make exactly K dollars, or determine that it's not possible. The
// time complexity is O(n * k), where n is the number of book types and k is the
// target amount, which is efficient for reasonably large values of n and k.
