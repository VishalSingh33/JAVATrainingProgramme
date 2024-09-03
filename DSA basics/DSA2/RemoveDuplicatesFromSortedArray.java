import java.util.ArrayList;
import java.util.HashMap;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {

        // remove the duplicates in-place, such that each element in the array appears
        // at most twice, and return the new length
        int n = 5;
        int[] arr = { 2, 2, 2, 3, 4, 4, 9 };

        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i]) || map.get(arr[i]) < 2) {
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
                list.add(arr[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        // return list.size();
        System.out.println(list.size());
    }

}
