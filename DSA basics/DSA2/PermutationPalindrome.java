import java.util.HashMap;
import java.util.Map;

public class PermutationPalindrome {
    public static void main(String[] args) {

        String s = "nnaamm";

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int[] charCount = new int[128];

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        int oddfreq = 0;
        for (int count : frequencyMap.values()) {
            if (count % 2 != 0) {
                oddfreq++;
            }
        }
        if (oddfreq > 1) {
            System.out.println(0);
        }
        System.out.println(1);

    }
}
