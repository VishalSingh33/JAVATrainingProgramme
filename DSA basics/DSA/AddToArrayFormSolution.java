import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddToArrayFormSolution {

    public List<Integer> addToArrayForm(int[] num, int k) {
        
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int i = num.length - 1;

        while (i >= 0 || k > 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += num[i];
                i--;
            }
            if (k > 0) {
                sum += k % 10;
                k /= 10;
            }
            carry = sum / 10;
            result.add(sum % 10);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        AddToArrayFormSolution solution = new AddToArrayFormSolution();
        int[] num1 = {9,9,9,9,9,9,9,9,9,9};
        int k1 = 1;
        System.out.println(solution.addToArrayForm(num1, k1)); // Output: [1, 2, 3, 4]

        // int[] num2 = {2, 7, 4};
        // int k2 = 181;
        // System.out.println(solution.addToArrayForm(num2, k2)); // Output: [4, 5, 5]
    }
}
