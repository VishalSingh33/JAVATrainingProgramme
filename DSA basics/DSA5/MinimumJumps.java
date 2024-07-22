import java.util.ArrayList;
import java.util.Scanner;

// Problem Description: Given an array of positive numbers, where each element 
// represents the maximum number of hops that can be made forward from that element, 
// write a program to find the minimum number of jumps needed to reach the end of 
// the array (starting from the first element). Return -1 if it is not possible to 
// reach the end of the array. If an element is 0, then we cannot jump from that element.

// Note: A Jump indicates moving from one index to another using single or multiple 
// hops where a Hop indicates moving to the immediate next neighbor.

// Input format
// First line will have an integer N denoting the length of the array.
// Second line will have N space separated integers denoting the maximum 
// hops from each position in the array.

public class MinimumJumps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int n = scanner.nextInt();
        ArrayList<Integer> a = new ArrayList<>();

        // Read the array elements
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }

        // Find and print the minimum number of jumps needed to reach the end
        System.out.println(minimumJumps(n, a));
    }

    static int minimumJumps(int n, ArrayList<Integer> a) {
        if (n == 1)
            return 0; // If array has only one element, no jumps are needed
        if (a.get(0) == 0)
            return -1; // If the first element is 0, we can't move anywhere

        int maxReach = a.get(0); // The maximum index we can reach
        int step = a.get(0); // The steps we can still take
        int jump = 1; // We start by assuming we make the first jump

        for (int i = 1; i < n; i++) {
            if (i == n - 1)
                return jump; // If we've reached the end, return the number of jumps

            maxReach = Math.max(maxReach, i + a.get(i)); // Update the maximum reach
            step--; // Use a step to get to the current index

            if (step == 0) { // If no steps are left
                jump++; // We need to make another jump

                if (i >= maxReach)
                    return -1; // If we can't move forward, return -1

                step = maxReach - i;
                // Re-initialize the steps to the amount of steps to reach maxReach from
                // position i
            }
        }
        // If we exit the loop without having reached the end, return -1
        return -1;
    }
}

// Explanation
// Initialization:
// We handle the cases where the array length is 1 or the first element is 0.
// Traversal:
// We maintain maxReach, step, and jump.
// For each element, we update maxReach and decrement step.
// When step becomes 0, it means we need another jump, so we increment the jump
// counter and re-initialize step.
// Termination:
// If maxReach is greater than or equal to the last index, we return the number
// of jumps.
// If we can't move forward anymore, we return -1.