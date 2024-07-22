// Problem Description: Given a string, find the length of its Longest Palindromic Substring.

// In a palindromic string, elements read the same backward and forward.

// Input format
// First line contains T, the number of test cases.
// First line of each test case contains integer N, the length of the String.
// Second line of each test case contains the string S.

public class LongestPalindromicSubstringLength {

    static int longestPalindromicSubstringLength(String str) {
        int n = str.length();
        if (n == 0)
            return 0;

        int maxLength = 1; // Minimum length of palindrome is 1

        for (int i = 0; i < n; i++) {
            // Odd length palindromes
            int len1 = expandAroundCenter(str, i, i);
            // Even length palindromes
            int len2 = expandAroundCenter(str, i, i + 1);

            maxLength = Math.max(maxLength, Math.max(len1, len2));
        }

        return maxLength;
    }

    // Helper function to expand around the center and find the length of the
    // palindrome
    private static int expandAroundCenter(String str, int left, int right) {
        int n = str.length();
        while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            String S = scanner.nextLine();
            System.out.println(longestPalindromicSubstringLength(S));
        }

        scanner.close();
    }
}

// Center Expansion:

// For odd-length palindromes, treat each character as the center and expand
// outwards to check for palindromes.
// For even-length palindromes, treat each pair of consecutive characters as the
// center and expand outwards.
// Expand Around Center:

// For each center (both single character and pair of characters), expand
// outwards while the characters on the left and right are equal.
// Keep track of the maximum length of any palindrome found.
// Iterative Expansion:

// Iterate through each character and each pair of characters in the string.
// Use a helper function to expand around the center and calculate the length of
// the palindrome.