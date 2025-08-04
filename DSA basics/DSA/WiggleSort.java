import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WiggleSort {
    public static void main(String[] args) {

        // reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

        List<Integer> nums = Arrays.asList(1, 5, 1, 1, 6, 4);
        Collections.sort(nums);

        ArrayList<Integer> result = new ArrayList<>(nums);
        for (int i = 1; i < nums.size() - 1; i += 2) {
            int temp = result.get(i);
            result.set(i, result.get(i + 1));
            result.set(i + 1, temp);
        }
        // return result;
        System.out.println(result);
        
    }
}
