import java.util.*;

// Problem Description
// You are given two strings and an integer k,find whether the given two strings are 
// similar or not. Two given strings s1 and s2 are similar if for each character the 
// difference between the frequency of that character in s1 and s2 is at most k. 
// If the given strings are similar then print Yes otherwise print No. 
// (Note : Both strings s1 and s2 are in lowercase )

// Input format
// First line contains an integer T - Number of test cases.
// The first line of each test case contains three integers N, M and K where N  
// is the length of the first string, M is the length of the second string.
// The second line of each test case contains a string s1.
// The third line of each test case contains a string s2.

public class SimilarStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            sc.nextLine(); // Consume newline
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();

            System.out.println(similarString(N, M, s1, s2, K));
        }

        sc.close();
    }

    static String similarString(int n, int m, String s, String t, int k) {
        // Frequency arrays for each character a-z
        int[] freqS = new int[26];
        int[] freqT = new int[26];

        // Count frequencies for string s
        for (char c : s.toCharArray()) {
            freqS[c - 'a']++;
        }

        // Count frequencies for string t
        for (char c : t.toCharArray()) {
            freqT[c - 'a']++;
        }

        // Check the frequency difference
        for (int i = 0; i < 26; i++) {
            if (Math.abs(freqS[i] - freqT[i]) > k) {
                return "No";
            }
        }

        return "Yes";
    }
}

// Explanation:

// Main Method:
// Reads the number of test cases.
// For each test case, reads N, M, and K, and the two strings s1 and s2.
// Calls the similarString function to determine if the strings are similar and
// prints the result.

// similarString Function:
// Initializes two arrays freqS and freqT to count the frequencies of each
// character (from 'a' to 'z') in s and t respectively.
// Iterates over each character in s and increments the corresponding index in
// freqS.
// Does the same for t with freqT.
// Checks if the absolute difference in frequencies for each character is within
// the allowed limit k.
// Returns "Yes" if all differences are within the limit, otherwise returns
// "No".
