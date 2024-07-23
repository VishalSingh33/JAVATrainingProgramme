import java.util.Arrays;

// Kth smallest element in array

public class KthSmallestElement {
    static int findKthSmallest(int[] arr, int k) {
        
        // Sort the array
        Arrays.sort(arr);
        // Return the Kth element (index k-1 due to 0-based indexing)
        return arr[k - 1];

    //     if (k < 1 || k > arr.length) {
    //         throw new IllegalArgumentException("Invalid value of k");
    //     }
    //     return quickSelect(arr, 0, arr.length - 1, k - 1);
    // }

    // static int quickSelect(int[] arr, int left, int right, int k) {
    //     if (left == right) {
    //         return arr[left];
    //     }

    //     int pivotIndex = partition(arr, left, right);
    //     if (k == pivotIndex) {
    //         return arr[k];
    //     } else if (k < pivotIndex) {
    //         return quickSelect(arr, left, pivotIndex - 1, k);
    //     } else {
    //         return quickSelect(arr, pivotIndex + 1, right, k);
    //     }
    // }

    // static int partition(int[] arr, int left, int right) {
    //     int pivot = arr[right];
    //     int i = left - 1;

    //     for (int j = left; j < right; j++) {
    //         if (arr[j] <= pivot) {
    //             i++;
    //             swap(arr, i, j);
    //         }
    //     }

    //     swap(arr, i + 1, right);
    //     return i + 1;
    // }

    // static void swap(int[] arr, int i, int j) {
    //     int temp = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = temp;
    
 }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 4, 1, 5, 9, 2, 6 };
        int k = 3; // Find the 3rd smallest element

        int result = findKthSmallest(arr, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}
