import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

        int[] nums = { 2, 4, 5, 9, 8 };
        int target = 7;
        Map<Integer, Integer> complementMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement)) {
                // return new int[] { complementMap.get(complement), i };
                System.out.println(new int[] { complementMap.get(complement), i });
            }
            complementMap.put(nums[i], i);
        }
        // return new int[0];
        System.out.println(new int[0]);
    }

}
