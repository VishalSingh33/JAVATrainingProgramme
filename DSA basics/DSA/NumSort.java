import java.util.Arrays;

public class NumSort {
    public static void main(String[] args) {

        // Given an array, where integers are written as strings, sort the array and
        // return it, with the elements still being strings.

        // Note that the number of digits in each element may go up to 10^6.

        int n = 5;
        String[] arr = { "3", "30", "1", "124", "54644" };

        Arrays.sort(arr, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        for(String num: arr){
            System.out.println(num + " ");

        }
    }
}
