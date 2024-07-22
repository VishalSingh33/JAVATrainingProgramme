import java.util.*;
// Problem Description
// You are given N packets of Donuts, each packet containing a varying number of donuts. 
// You have to pick total B packets from either left or right end to get the maximum number of donuts.

// Input format
// First line will contain two space separated integers N and B respectively.
// Second line will contain N space separated A[i] - specifying the number of donuts in each packet.

public class Donuts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int B = scanner.nextInt();
        List<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(scanner.nextInt());
        }
        scanner.close();
        System.out.println(maxDonuts(N, B, A));
    }

    static long maxDonuts(int N, int B, List<Integer> A) {
        long[] prefixLeft = new long[B + 1];
        long[] prefixRight = new long[B + 1];

        // Calculate prefix sums for the left side
        for (int i = 0; i < B && i < N; i++) {
            prefixLeft[i + 1] = prefixLeft[i] + A.get(i);
        }

        // Calculate prefix sums for the right side
        for (int i = 0; i < B && i < N; i++) {
            prefixRight[i + 1] = prefixRight[i] + A.get(N - 1 - i);
        }

        long maxDonuts = 0;

        // Find the maximum sum by combining the left and right prefix sums
        for (int i = 0; i <= B; i++) {
            long currentDonuts = prefixLeft[i] + prefixRight[B - i];
            if (currentDonuts > maxDonuts) {
                maxDonuts = currentDonuts;
            }
        }

        return maxDonuts;
    }
}

// Prefix Sums:
// prefixLeft array stores sums of the first i packets from the left.
// prefixRight array stores sums of the first i packets from the right.
// Combining Prefix Sums:

// Iterate over possible values of i (where i is the number of packets taken
// from the left), and for each i, calculate the total donuts by summing
// prefixLeft[i] and prefixRight[B - i].
// Tracking Maximum:

// Keep track of the maximum sum found during the iteration.