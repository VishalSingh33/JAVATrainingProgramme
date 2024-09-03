public class MaxSumTriplet {
    public static void main(String[] args) {

        int n = 7;
        long arr[] = { 3, 7, 4, 2, 5, 7, 5 };
        long maxSum = 0;

        for (int i = 1; i < n - 1; i++) {
            long maxA = 0, maxB = arr[i], maxC = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < maxB) {
                    maxA = Math.max(arr[j], maxA);
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > maxB) {
                    maxC = Math.max(arr[j], maxC);
                }
            }
            if (maxA > 0 && maxC > 0) {
                maxSum = Math.max(maxA + maxB + maxC, maxSum);
            }

        }
        // return maxSum;
        System.out.println(maxSum);
    }

}
