public class SearchInRotatedSortedArray {
    public static void main(String[] args) {

        // An array sorted in ascending order is rotated about a pivot unknown to you.
        // Such an array is referred to as a rotated sorted array or a sorted-pivoted
        // array. For example : [1,2,3,4,5] is a sorted array while [3,4,5,1,2] is a
        // rotated sorted array.
        int[] nums = { 4, 5, 6, 9, 10, 2, 3 };
        int target = 8;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // return mid; // Target found at index mid
                System.out.println(mid);
            }

            // If left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Search in the left half
                } else {
                    left = mid + 1; // Search in the right half
                }
            }
            // If right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Search in the right half
                } else {
                    right = mid - 1; // Search in the left half
                }
            }
        }
        // return -1;
        System.out.println(-1);
    }

}
