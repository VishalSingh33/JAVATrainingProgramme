import java.util.Stack;

public class NextLargerElement {
    public static void main(String[] args) {

        // Given an array A having N elements, the task is to find the next greater
        // element(NGE) for each element of the array in order of their appearance in
        // the array. If no such element exists, output -1. This should be achieved with
        // a time complexity of O(n).

        int nums[] = { 2, 1, 3, 2, 4 };
        int n = 5;

        int[] st = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                st[i] = -1;
            } else {
                st[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        // return st;
        for (int i = 0; i < n; i++) {
            System.out.print(st[i] + " ");
        }
    }

}
