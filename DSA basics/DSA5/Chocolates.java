import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Problem Description
// Given an integer array 'nums' where each element of the array represents the 
// number of chocolates each shop gets. It has been found that the number of 
// chocolates given to each shop is different. Rohan is a guy who wants to buy 
// some chocolates from any shop. His mother has given him a list . 
// The list contains shop numbers from where Rohan is allowed to buy chocolates. 
// Print all the possible combinations of the shops which can be there on that list.
// The list can also be empty.

// The solution set must not contain duplicate combinations. 
// You can print the combinations in any order.

// Input format
// Given an integer n.
// Given n space separated integers.

public class Chocolates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> result = chocolates(n, nums);
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
        sc.close();
    }

    static List<List<Integer>> chocolates(int n, int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to handle duplicates and maintain order
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList,
            int[] nums, int start) {

        result.add(new ArrayList<>(tempList)); // Add the current combination to the result
        for (int i = start; i < nums.length; i++) {
            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1])
                continue;
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1); // Recur with next index
            tempList.remove(tempList.size() - 1); // Remove the last element for backtracking
        }
    }
}

// Explanation:
// Input Reading:

// The main method reads the integer n and the array nums which represents
// the number of chocolates each shop gets.

// Sorting the Array:
// Arrays.sort(nums) sorts the array to help generate combinations in a sorted
// order and avoid duplicate combinations easily.
// Backtracking Function:

// The backtrack method is a recursive function that generates all possible
// combinations.
// result.add(new ArrayList<>(tempList)) adds the current combination to the
// result list.
// The loop iterates over the array starting from the current index start to
// avoid generating the same combination multiple times.
// if (i > start && nums[i] == nums[i - 1]) continue; skips duplicates by
// ensuring the same element is not used multiple times in the same combination.
// tempList.add(nums[i]) adds the current element to the temporary list.
// backtrack(result, tempList, nums, i + 1) recursively generates combinations
// including the current element.
// tempList.remove(tempList.size() - 1) removes the last element to backtrack
// and explore other combinations.
// Output:

// The main method prints each combination stored in the result list.
