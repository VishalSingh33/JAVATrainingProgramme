import java.util.HashSet;
import java.util.Set;

public class MaximumSumDistinctSubarraysLengthK {

    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0, currSum = 0;
        int windowStart = 0;
        Set<Integer> set = new HashSet<>();

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            // Remove elements until the subarray becomes valid (distinct elements)
            while (set.contains(nums[windowEnd]) || windowEnd - windowStart >= k) {
                set.remove(nums[windowStart]);
                currSum -= nums[windowStart];  // Remove the value from currSum
                windowStart++;
            }

            // Add the current element
            set.add(nums[windowEnd]);
            currSum += nums[windowEnd];

            // Check if the current window is of length k and update maxSum
            if (windowEnd - windowStart + 1 == k) {
                maxSum = Math.max(maxSum, currSum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        MaximumSumDistinctSubarraysLengthK solution = new MaximumSumDistinctSubarraysLengthK();

        int[] nums = {1, 2, 3, 4, 2, 3, 5, 1, 2};
        int k = 3;
        System.out.println("Maximum sum of distinct subarrays of length " + k + ": " + solution.maximumSubarraySum(nums, k)); // Output: 10 (subarray: [, , ])
        
        // Example 1: Test case
        int[] nums1 = {1, 2, 3, 4, 5};
        int k1 = 3;
        System.out.println("Maximum sum of distinct subarrays of length nums1: " + solution.maximumSubarraySum(nums1, k1));  // Output: 12 (subarray: [3, 4, 5])

        // Example 2: Test case
        int[] nums2 = {5, 3, 5, 1, 3, 2, 4};
        int k2 = 4;
        System.out.println("Maximum sum of distinct subarrays of length nums2: " + solution.maximumSubarraySum(nums2, k2));  // Output: 12 (subarray: [1, 3, 2, 4])
        
        // Example 3: Test case
        int[] nums3 = {4, 2, 4, 5, 6};
        int k3 = 3;
        System.out.println("Maximum sum of distinct subarrays of length nums3: " + solution.maximumSubarraySum(nums3, k3));  // Output: 15 (subarray: [4, 5, 6])
    }
}
