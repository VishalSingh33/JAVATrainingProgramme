//min and max freq
public class Characters {

    public static void main(String[] args) {
        //string is declared
        String str = "ncwcuhcwedhewzzzzzzzzzzzzzzzzzzzz ubebc nbwbeubc uwbnicbucwd";
        //to cal string length
        int[] freq = new int[str.length()];
        int i, j, min, max;
        //initiate fronm zero
        char minChar = str.charAt(0), maxChar = str.charAt(0);
        //method converts the given string into a sequence of characters
        char string[] = str.toCharArray();

        for (i = 0; i < str.length(); i++) {
            freq[i] = 1;
            for (j = i + 1; j < str.length(); j++) {
                if (string[i] == string[j]) {
                    freq[i]++;
                    string[j] = '0';
                }
            }
        }
        //cal min freq
        min = max = freq[0];
        for (i = 0; i < freq.length; i++) {
            if (min > freq[i] && freq[i] != '0') {
                min = freq[i];
                minChar = string[i];
            }
            //cal max freq
            if (max < freq[i]) {
                max = freq[i];
                maxChar = string[i];
            }
        }
        System.out.println("Minimum occurring character: " + minChar);
        System.out.println("Maximum occurring character: " + maxChar);
    }
}
