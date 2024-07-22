import java.util.Scanner;

// Problem Description
// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// 'Z' -> 26

// Given a non-empty string containing only digits, determine the total number of ways to decode it.
// Since the answer can be very large, print the answer modulo 1000000007.

// Input format
// Single line with the string
// Output format
// Number of ways to decode modulo 1000000007

public class DecodeWays {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the encoded string:");
        String s = scanner.next();
        System.out.println("Number of ways to decode: " + numDecodings(s));
        scanner.close();
    }

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: An empty string has one way to be decoded
        dp[1] = 1; // The first character cannot be '0' as checked above

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i)); // Get single digit
            if (oneDigit != 0) {
                dp[i] += dp[i - 1]; // Add ways to decode up to the previous character
                dp[i] %= MOD; // Modulo operation to prevent overflow
            }

            int twoDigits = Integer.parseInt(s.substring(i - 2, i)); // Get two consecutive digits
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2]; // Add ways to decode two characters back
                dp[i] %= MOD; // Modulo operation
            }
        }

        return dp[n]; // Return the number of ways to decode the entire string
    }
}

// Dynamic Programming Array (dp): This array is used to keep track of the
// number of ways to decode the string up to each index. dp[i] represents the
// number of ways to decode the substring s[0:i].

// Initialization: We start with dp[0] = 1 because an empty string can be
// decoded in one way (doing nothing). If the first character is not '0', dp[1]
// = 1 since there is one way to decode a non-zero digit. If it is '0', then
// dp[1] = 0 because '0' cannot correspond to any letter.

// Iterating Through the String: For each character starting from the second
// one, we consider two cases:
// Decoding the single character (oneDigit): If it's between '1' and '9', it can
// be decoded, so we add the ways to decode the string up to the previous
// character (dp[i-1]).
// Decoding two characters together (twoDigits): If the number formed by these
// two characters is between 10 and 26, it can be decoded into a letter. We then
// add the ways to decode the string up to two characters before (dp[i-2]).
