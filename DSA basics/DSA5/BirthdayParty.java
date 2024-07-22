import java.util.Scanner;
import java.util.Stack;

// Problem Description
// N friends attended a birthday party. Their ages are specified in the array 
// and they are arranged in the order of arrival. For each of the people, find the 
// next younger person who arrived after them. If there is no younger person, return -1.

// Input format
// There are 2 lines of input:
// The first line contains an integer N denoting the number of friends.
// The second line contains N space separated positive integers denoting the 
// ages of friends arranged in order of arrival in the array A.

public class BirthdayParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] result = birthdayParty(arr, n);
        for (int r : result) {
            System.out.print(r + " ");
        }
        sc.close();
    }

    static int[] birthdayParty(int[] arr, int n) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Initialize all results to -1
        for (int i = 0; i < n; i++) {
            result[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            // While the stack is not empty and the current age is less than the
            // age of the person at index stack.peek()
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        return result;
    }
}

// Explanation:

// Input Reading:
// The main method reads the number of friends n and their ages into the array
// arr.

// Result Initialization:
// Initialize the result array with -1, assuming there might be no younger
// person for each friend.

// Using a Stack:
// Traverse the arr and use a stack to keep track of indices of friends.
// For each friend, check if there is any previous friend (whose index is on the
// stack) that has an age greater than the current friend's age. If found,
// update the result for that friend with the current friend's age and pop the
// stack.
// Push the current index onto the stack.

// Result Array:
// The result array contains the age of the next younger person for each friend
// or -1 if no such person exists.