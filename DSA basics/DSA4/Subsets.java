import java.util.ArrayList;
import java.util.List;


// Given a set of distinct integers, nums, return all possible subsets (the power set).
// Note: The solution set must not contain duplicate subsets.

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
        result.add(new ArrayList<>(current)); // Add the current subset to the result

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // Include the current element in the subset
            backtrack(result, current, nums, i + 1); // Recursive call with updated start index
            current.remove(current.size() - 1); // Backtrack (remove the last element)
        }
    }

    public static void main(String[] args) {
        Subsets subsetsGenerator = new Subsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subsetsGenerator.subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
