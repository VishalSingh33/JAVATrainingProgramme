import java.util.Stack;

public class BackspaceStringCompare {
    public static void main(String[] args) {

        // Given 2 strings S and T containing lowercase and '#' characters. You have to
        // check whether these 2 strings are same or not when typed into an editor('#'
        // being the backspace character).

        String S = "aa#sddf";
        String T = "as#sddff#";

        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();

        for (char c : S.toCharArray()) {
            if (c != '#') {
                st1.push(c);
            } else if (!st1.isEmpty()) {
                st1.pop();
            }
        }
        for (char c : T.toCharArray()) {
            if (c != '#') {
                st2.push(c);
            } else if (!st2.isEmpty()) {
                st2.pop();
            }
        }
        if (st1.size() != st2.size()) {
            // return false;
            System.out.println(false);
        }
        while (!st1.isEmpty()) {
            if (st1.pop() != st2.pop()) {
                // return false;
                System.out.println(false);
            }
        }
        // return true;
        System.out.println(true);
    }

}
