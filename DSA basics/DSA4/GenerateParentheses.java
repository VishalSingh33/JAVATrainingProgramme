import java.util.ArrayList;
import java.util.List;

// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String current, int open, int close, int n) {
        if (current.length() == n * 2) {
            result.add(current);
            return;
        }

        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }
        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        int n = 3; // Number of pairs of parentheses
        List<String> result = gp.generateParenthesis(n);
        System.out.println(result);
    }
}
