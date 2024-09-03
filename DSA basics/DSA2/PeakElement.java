public class PeakElement {
    public static void main(String[] args) {

        // A peak element is an element that is strictly greater than its neighbors.
        // Given an integer array nums, find a peak element, and return its index. If
        // the array contains multiple peaks, return the index to any of the peaks. You
        // may imagine that nums[-1] = nums[n] = -∞. You must write an algorithm that
        // runs in O(log n) time.

        int arr[] = { 1, 2, 3, 1 };

        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[mid + 1]) {
                right = mid; // Potential peak is on the left side
            } else {
                left = mid + 1; // Potential peak is on the right side
            }
        }
        // return left;
        System.out.println(left);
    }

}
