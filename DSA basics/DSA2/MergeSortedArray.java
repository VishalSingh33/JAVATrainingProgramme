public class MergeSortedArray {
    public static void main(String[] args) {

        int[] nums1 = { 1, 3, 6 };
        int m = 3;
        int[] nums2 = { 2, 4, 7 };
        int n = 3;

        int i = 0, j = 0, k = 0;
        int[] nums = new int[n + m];
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums[k] = nums1[i];
                i++;
                k++;
            } else {
                nums[k] = nums2[j];
                j++;
                k++;
            }
        }
        while (i < m) {
            nums[k] = nums1[i];
            i++;
            k++;
        }
        while (j < n) {
            nums[k] = nums2[j];
            j++;
            k++;
        }
        // return nums;
        System.out.println(nums);
    }

}
