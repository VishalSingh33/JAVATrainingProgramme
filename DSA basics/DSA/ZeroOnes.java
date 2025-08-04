public class ZeroOnes {
    public static void main(String[] args) {

        // Given a sorted array consisting of 0s and 1s only, find the index of the
        // first 1. If there’s no 1 present in the array, return -1

        int n = 4;
        int a[] = { 0, 0, 1, 1 };

        int left = 0;
        int right = a.length - 1;
        if (a[0] != 0) {
            // return 0;
            System.out.println(0);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == 1 && a[mid - 1] == 0) {
                // return mid;
                System.out.print(mid);
            } else if (a[mid] == 1) {
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        System.out.println(-1);
        // return -1;
    }
}
