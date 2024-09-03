import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {
        
        int n = 10; String s = "abcdeedcba";

        char[] arrS = s.toCharArray();

        StringBuilder stbr = new StringBuilder();

        Arrays.sort(arrS);

        for(char str: arrS){
            stbr.append(str);
        }
        // return stbr.toString();
        System.out.println(stbr.toString());
    }
    
}
