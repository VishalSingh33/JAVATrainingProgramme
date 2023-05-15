import java.util.Scanner;
import java.util.*;

class ThreeSum {

    public List<List<Integer>> threeSum(Integer[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting the array to efficiently handle duplicates and search for triplets
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Avoid duplicates for the first element of the triplet
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                // Fix the first element and find the other two
                int target = -nums[i];
                // Pointer to the second element
                int left = i + 1;
                // Pointer to the third element
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[left] + nums[right];

                    if (sum == target) {
                        // Found a triplet with the required sum
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Avoid duplicates for the second and third elements of the triplet
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Sum is smaller, move the left pointer
                    } else {
                        right--; // Sum is larger, move the right pointer
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        Integer nums[] = new Integer[n];
        for (Integer i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        List<List<Integer>> result = new ThreeSum().threeSum(nums);
        for (Integer i = 0; i < result.size(); ++i) {
            System.out.printf("%d %d %d\n", result.get(i).get(0),
                    result.get(i).get(1),
                    result.get(i).get(2));
        }
    }
};