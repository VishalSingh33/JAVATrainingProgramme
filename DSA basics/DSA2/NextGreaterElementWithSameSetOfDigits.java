public class NextGreaterElementWithSameSetOfDigits {
    public static void main(String[] args) {

        // Given a positive integer n, find the smallest integer which has exactly the
        // same digits existing in the integer n and is greater in value than n. If no
        // such positive integer exists, return -1.

        int n = 12;

        String numberStr = String.valueOf(n);
        char[] numbers = numberStr.toCharArray();

        // boolean found = false;
        // for (int i = numbers.length - 1; i >= 1; i--) {
        // if (numbers[i] > numbers[i-1]) {
        // char temp = numbers[i];
        // numbers[i] = numbers[i-1];
        // numbers[i-1] = temp;
        // found = true;
        // break;
        // }
        // }
        // if (!found || n < 0) {
        // return -1;
        // }
        // StringBuilder stbr = new StringBuilder();
        // for (int i = 0; i < numbers.length; i++) {
        // stbr.append(numbers[i]);
        // }
        // int stbrNumber = Integer.parseInt(stbr.toString());
        // return stbrNumber;

        int i = numbers.length - 1;
        while (i > 0 && numbers[i] <= numbers[i - 1]) {
            i--;
        }

        if (i == 0) {
            // return -1; // No greater number with the same digits exists
            System.out.println(-1);
        }

        int j = numbers.length - 1;
        while (numbers[j] <= numbers[i - 1]) {
            j--;
        }

        // Swap the two elements
        char temp = numbers[i - 1];
        numbers[i - 1] = numbers[j];
        numbers[j] = temp;

        // Sort the digits to the right of the swapped index
        j = numbers.length - 1;
        while (i < j) {
            temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
            i++;
            j--;
        }

        int nextGreaterNumber = Integer.parseInt(new String(numbers));
        // return nextGreaterNumber;
        System.out.println(nextGreaterNumber);
    }

}
