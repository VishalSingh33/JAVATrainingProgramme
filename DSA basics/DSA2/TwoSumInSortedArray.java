public class TwoSumInSortedArray {
    public static void main(String[] args) {

        int n = 6;
        int[] arr = { 2, 5, 4, 8, 9, 7 };
        int k = 7;

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == k) {
                // return true;
                System.out.println(true);
                // Two elements found that add up to the target
            } else if (sum < k) {
                left++;
                // Move the left pointer to consider larger elements
            } else {
                right--;
                // Move the right pointer to consider smaller elements
            }
        }
        // return false;
        System.out.println(false);
    }

}
