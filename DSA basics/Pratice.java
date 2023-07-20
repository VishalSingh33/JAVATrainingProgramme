import java.util.*;

public class Solution {

   
    static String jeeResult(String marks) {

      String[] words = marks.split(" ");
      ArrayList<String> list = new ArrayList<>();

      for(String word : words)
      {
        list.add(word);
      }

      Collections.max(list);
      Collections.min(list);

      return String.valueOf(Collections.max(list) + " " + Collections.min(list));
    }
    }