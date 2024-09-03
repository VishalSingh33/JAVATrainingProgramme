public class CountOccurrences {
    public static void main(String[] args) {

        // Given a sorted integer array of length n with possible duplicate elements.
        // Find the number of occurrences of an integer k using binary search.

        int n = 5;
        int k = 2;
        int a[] = { -1, 2, 2, 4, 7, 4, 4 };

        int left = 0, right = n - 1, resultRight = -1, resultLeft = -1;

        // Finding the rightmost occurrence of k
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == k) {
                resultRight = mid;
                left = mid + 1; // Continue searching in the right half
            } else if (a[mid] > k) {
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        left = 0;
        right = n - 1;
        // Finding the leftmost occurrence of k
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == k) {
                resultLeft = mid;
                right = mid - 1; // Continue searching in the left half
            } else if (a[mid] > k) {
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        if (resultLeft >= 0 && resultRight >= 0) {
            // return resultRight - resultLeft + 1;
            System.out.println(resultRight - resultLeft + 1);
        }
        // return 0;
        System.out.println(0);
    }

}
