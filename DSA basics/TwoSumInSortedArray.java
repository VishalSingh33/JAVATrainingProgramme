import java.util.*;

class TwoSumInSortedArray {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();

        boolean res = twoSumInSortedArray(n, arr, k);
        String ans = (res) ? "Present" : "Not Present";
        System.out.println(ans);
    }

    static boolean twoSumInSortedArray(int n, int[] arr, int k) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == k) {
                return true; // Two elements found that add up to the target
            } else if (sum < k) {
                left++; // Move the left pointer to consider larger elements
            } else {
                right--; // Move the right pointer to consider smaller elements
            }
        }

        return false; 

    }
}