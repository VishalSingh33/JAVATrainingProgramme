import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) {

        String s = "nagaram", t = "anagram";

        if ((s.length() != t.length()) || s == "" || t == "") {
            // return false;
            System.out.println(false);
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] != tArray[i]) {
                // return false;
                System.out.println(false);
            }
        }
        // return true;
        System.out.println(true);
    }

}
