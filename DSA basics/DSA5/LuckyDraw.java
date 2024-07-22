import java.util.*;

// Problem Description
// In a lucky draw, every person is given a token. The winners are the people who 
// draw the Xth smallest token value. Find the winner’s token value.

// Input format
// First line contains an integer N - Total number of persons participating in the lucky draw.
// Second line contains N integers - Token values.
// Third line contains the integer X.

public class LuckyDraw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of participants
        int n = sc.nextInt();

        // Read the token values
        int[] tokens = new int[n];
        for (int i = 0; i < n; i++) {
            tokens[i] = sc.nextInt();
        }

        // Read the value of X
        int x = sc.nextInt();

        // Find the Xth smallest token value
        int result = luckyDraw(tokens, x);

        // Print the result
        System.out.println(result);

        sc.close();
    }

    public static int luckyDraw(int[] tokens, int x) {
        // Sort the array of token values
        Arrays.sort(tokens);

        // Return the Xth smallest value (X-1 due to zero-based index)
        return tokens[x - 1];
    }
}

// Explanation:
// Input Reading:
// The program reads the number of participants (n), the token values (tokens
// array), and the value of X.

// Sorting:
// The Arrays.sort(tokens) method sorts the token values in ascending order.
// Retrieve Xth Smallest Value:
// Since arrays in Java are zero-indexed, the Xth smallest value is located at
// the x-1 index in the sorted array.
