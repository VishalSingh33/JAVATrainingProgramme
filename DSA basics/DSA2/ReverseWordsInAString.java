public class ReverseWordsInAString {
    public static void main(String[] args) {
        
        String s = "hello world";
        String[] words = s.split("\\s+");
        StringBuilder reversed = new StringBuilder();
    
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");  
            }
        }
        // return reversed.toString();
        System.out.println(reversed.toString());
    }
    
}
