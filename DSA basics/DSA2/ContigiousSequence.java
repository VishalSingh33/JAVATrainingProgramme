public class ContigiousSequence {
    public static void main(String[] args) {

        int arr[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int n = 8;

        long maxSum = arr[0], currSum = arr[0];

        if (currSum < 0) {
            currSum = 0;
        }
        for (int i = 1; i < n; i++) {

            currSum = currSum + arr[i];

            if (currSum > maxSum) {
                maxSum = currSum;
            }

            if (currSum < 0) {
                currSum = 0;
            }
        }
        // for (int i = 1; i < n; i++) {
        // currSum = Math.max(arr[i], currSum + arr[i]);
        // maxSum = Math.max(maxSum, currSum);
        // }
        // return maxSum;
        System.out.println(maxSum);
    }

}
