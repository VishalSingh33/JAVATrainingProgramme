import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {

        // Given a string, find the length of the longest substring which has no
        // repeating characters.
        // Output is an integer representing the longest substring with no repeating
        // characters.

        String s = "cdddddd";
        // StringBuilder stbr = new StringBuilder();
        // if (s == null || s.length() == 0) {
        // // return 0;
        // System.out.println(0);
        // }
        // int maxLength = 0;
        // int start = 0;
        // int n = s.length();
        // int[] charIndex = new int[256]; // Assuming ASCII characters
        // Arrays.fill(charIndex, -1);

        // for (int end = 0; end < n; end++) {
        // char currentChar = s.charAt(end);
        // if (charIndex[currentChar] != -1) {
        // start = Math.max(start, charIndex[currentChar] + 1);
        // }
        // charIndex[currentChar] = end;
        // maxLength = Math.max(maxLength, end - start + 1);
        // }
        Set<Character> charSet = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;

        while (right < s.length()) {
            if (!charSet.contains(s.charAt(right))) {
                // If the character is not in the set, add it to the set and expand the window
                charSet.add(s.charAt(right));
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                // If the character is in the set, remove the leftmost character and shrink the
                // window
                charSet.remove(s.charAt(left));
                left++;
            }
        }
        System.out.println(maxLength); // return maxLength;
    }

}
