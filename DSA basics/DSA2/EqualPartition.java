import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EqualPartition {
	public static void main(String[] args) {

		int n = 899621; // 912689 989621
		String numberStr = String.valueOf(n);

		char[] numbers = numberStr.toCharArray();

		int i = numbers.length - 1;
        while (i > 0 && numbers[i] <= numbers[i - 1]) {
            i--;
        }

        if (i == 0) {
            System.out.println( -1); // No greater number with the same digits exists
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
		System.out.println(nextGreaterNumber);
	}

}
