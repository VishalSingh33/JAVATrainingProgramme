import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of distinct integers - candidates and a target integer target, return a 
// list of all unique combinations of candidates where the chosen numbers sum to target.

// You may return the combinations in sorted order.
// The same number may be chosen from candidates an unlimited number of times.
// Two combinations are unique if the frequency of at least one of the chosen numbers is different.
// Elements in each combination must be in non-decreasing order.
// If no combinations are possible, the program prints NA.

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort the candidates to ensure non-decreasing order
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int remaining, int start) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) {
                break; // Skip if the current candidate is greater than remaining
            }
            current.add(candidates[i]);
            backtrack(result, current, candidates, remaining - candidates[i], i); // Recursive call
            current.remove(current.size() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = combinationSum(candidates, target);
        if (combinations.isEmpty()) {
            System.out.println("NA");
        } else {
            for (List<Integer> combination : combinations) {
                System.out.println(combination);
            }
        }
    }
}
