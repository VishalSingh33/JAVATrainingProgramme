public class MaximumSubarraySumSizeK {
    public static void main(String[] args) {

        int[] arr = { 100, 200, 300, 400 };
        int n = 4;
        int k = 2;

        int currSum = 0;

        for (int i = 0; i < k; i++) {
            currSum += arr[i];
        }
        int maxSum = currSum;

        for (int i = k; i < n; i++) {

            currSum = currSum + arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, currSum);
        }
        System.out.println(maxSum); // return maxSum;
    }
}
