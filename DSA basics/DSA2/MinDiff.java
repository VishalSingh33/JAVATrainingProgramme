import java.util.Arrays;

public class MinDiff {
    public static void main(String[] args) {

        int n = 3;
        int a[] = { 1, 2, 4 };

        Arrays.sort(a);
        int min = Integer.MAX_VALUE; // Set the initial min to a large value

        for (int i = 0; i < n - 1; i++) {
            min = Math.min(min, a[i + 1] - a[i]);
        }
        // return min;
        System.out.println(min);
    }
}
