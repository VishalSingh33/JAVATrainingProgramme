import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SubarraySumZero {
    public static void main(String[] args) {

        Vector<Integer> arr = new Vector<>(Arrays.asList(4, 2, -2, 5));
        String result = "No";
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 0 || entry.getValue() > 1) {
                // return "Yes";
                System.out.println("Yes");
            }
        }
        // return result;
        System.out.println(result);
    }

}
