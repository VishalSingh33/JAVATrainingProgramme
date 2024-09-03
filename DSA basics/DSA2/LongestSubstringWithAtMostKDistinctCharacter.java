import java.util.HashMap;

public class LongestSubstringWithAtMostKDistinctCharacter {
    // Given a string, find the length of the longest substring that contains at
    // most K distinct characters.
    public static void main(String[] args) {

        String s = "abacd";
        int k = 3;

        if (k <= 0 || s == null || s.isEmpty()) {
            // return 0;
            System.out.println(0);
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);

            while (map.size() > k) {
                char startChar = s.charAt(start);
                map.put(startChar, map.get(startChar) - 1);
                if (map.get(startChar) == 0) {
                    map.remove(startChar);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        System.out.println(maxLength); // return maxLength;

    }
}
