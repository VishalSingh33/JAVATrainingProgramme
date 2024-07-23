import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {
    private Map<Character, String> phoneMap = new HashMap<>();
    private List<String> combinations = new ArrayList<>();
    
    public LetterCombinationsOfPhoneNumber() {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }
    
    public List<String> letterCombinationsOfPhoneNumber(String digits) {
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        
        generateCombinations(digits, 0, new StringBuilder());
        return combinations;
    }
    
    private void generateCombinations(String digits, int index, StringBuilder current) {
        if (index == digits.length()) {
            combinations.add(current.toString());
            return;
        }
        
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            generateCombinations(digits, index + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();
        String digits = "23"; // Example input
        List<String> result = solution.letterCombinationsOfPhoneNumber(digits);
        System.out.println(result);
    }
}
