import java.util.*;

// Problem Description
// You are given an integer array nums and you have to return a new counts array. 
// The counts array has the property where counts[i] is the number of smaller elements 
// to the right of nums[i].

// Input format
// First line contains N, indicating the number of elements in the nums array.
// Next line contains N space separated integers.

public class Solution {
    public ArrayList<Integer> countOfSmallerNumberAfterSelf(int[] nums) {
        // Get the unique elements and sort them
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Create a mapping from element value to its rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 0;
        for (int num : set) {
            rankMap.put(num, rank++);
        }

        // Create a Fenwick Tree with size equal to the number of unique elements
        int[] fenwickTree = new int[rankMap.size()];

        // ArrayList to store the result
        ArrayList<Integer> result = new ArrayList<>(nums.length);

        // Process the elements from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            int currentNumRank = rankMap.get(nums[i]);
            result.add(getSum(fenwickTree, currentNumRank - 1));
            updateFenwickTree(fenwickTree, currentNumRank, 1);
        }

        // The result is in reverse order, reverse it before returning
        Collections.reverse(result);
        return result;
    }

    // Get the sum of frequencies up to the given index
    private int getSum(int[] fenwickTree, int index) {
        int sum = 0;
        while (index >= 0) {
            sum += fenwickTree[index];
            index = (index & (index + 1)) - 1;
        }
        return sum;
    }

    // Update the Fenwick Tree at the given index
    private void updateFenwickTree(int[] fenwickTree, int index, int value) {
        while (index < fenwickTree.length) {
            fenwickTree[index] += value;
            index = index | (index + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Solution solution = new Solution();
        ArrayList<Integer> result = solution.countOfSmallerNumberAfterSelf(nums);

        for (int count : result) {
            System.out.println(count);
        }

        scanner.close();
    }
}

// Explanation
// Set and Rank Mapping:

// We use a TreeSet to store unique elements of the array and sort them.
// We create a rank mapping using a HashMap to map each unique element to its
// rank (its position in the sorted unique list).
// Fenwick Tree (Binary Indexed Tree):

// We initialize a Fenwick Tree (an array) with a size equal to the number of
// unique elements.
// The Fenwick Tree helps in efficiently updating the frequencies and querying
// the sum of frequencies up to a given index.
// Processing Elements from Right to Left:

// For each element in the array (starting from the rightmost element), we find
// the rank of the element and query the Fenwick Tree to find the count of
// elements smaller than the current element.
// We update the Fenwick Tree to include the current element.
// Reverse the Result:

// Since we processed the elements from right to left, the result is in reverse
// order. We reverse the result before returning it.
// This approach ensures efficient handling of both updates and queries using
// the Fenwick Tree, leading to a time complexity of O(NlogN).