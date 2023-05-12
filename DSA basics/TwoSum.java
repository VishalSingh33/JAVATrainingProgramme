import java.io.*;
import java.util.*;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement)) {
                return new int[] { complementMap.get(complement), i };
            }
            complementMap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);
        // int[] numbers = new int[scanner.nextInt()];
        // for (int i = 0; i < numbers.length; i++)
        // numbers[i] = scanner.nextInt();
        // int target = scanner.nextInt();
        // scanner.close();

        int[] numbers = { 2, 7, 5, 3, 11, 15 };
        int target = 20;

        int[] complements = new TwoSum().twoSum(numbers, target);
        System.out.print(" " + complements[0] + "  " + complements[1] + " ");
    }
}
