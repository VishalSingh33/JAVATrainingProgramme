import java.util.*;

// Problem Description
// Given an integer array nums, find the contiguous subarray within an array 
// (containing at least one number) which has the largest product.

// Input format
// First line contains an integer N - Size of array nums.
// Second line contains N integers.

public class MaximumProductSubarray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int N = scanner.nextInt();

        // Read the array elements
        Vector<Integer> nums = new Vector<>();
        for (int i = 0; i < N; i++) {
            nums.add(scanner.nextInt());
        }

        // Find and print the maximum product subarray
        System.out.println(maximumProductSubarray(nums));
    }

    private static long maximumProductSubarray(Vector<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }

        long maxProduct = nums.get(0);
        long minProduct = nums.get(0);
        long result = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            int current = nums.get(i);

            // Update maxProduct and minProduct
            if (current < 0) {
                long temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(current, maxProduct * current);
            minProduct = Math.min(current, minProduct * current);

            // Update the result
            result = Math.max(result, maxProduct);
        }

        return result;
    }
}

// Explanation
// Initialization:
// maxProduct, minProduct, and result are initialized with the first element of
// the array.

// Iteration:
// For each element, if the element is negative, swap maxProduct and minProduct
// because multiplying by a negative number inverts the roles of max and min.
// Update maxProduct as the maximum of the current element and the product of
// the current element with the previous maxProduct.
// Update minProduct as the minimum of the current element and the product of
// the current element with the previous minProduct.
// Update result to be the maximum value of result and maxProduct.

// Output:
// The maximum product of a contiguous subarray is stored in result, which is
// printed as the final output.
// This solution efficiently computes the maximum product subarray with a time
// complexity of O(N) and a space complexity of O(1), making it suitable for
// large inputs.
