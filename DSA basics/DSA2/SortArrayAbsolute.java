import java.util.Arrays;

public class SortArrayAbsolute {
    public static void main(String[] args) {

        // sort the array based on the absolute value of the elements.
        int n = 5;
        int arr[] = { 2, -5, 1, -2, 4 };

        Integer[] integerArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            integerArr[i] = arr[i];
        }
        Arrays.sort(integerArr, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));

        for (int i = 0; i < n; i++) {
            System.out.print(integerArr[i] + " ");
        }

    }

}
