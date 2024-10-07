import java.util.ArrayList;
import java.util.HashMap;

public class LargestSubarraySumZero {
    public static void main(String[] args) {

        int n = 6;
        int arr[] = { 2, 3, 1, -4, 0, 6 };

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] prefixSum = new int[n];
        int sum = 0, maxLength = 0, start = -1, end = -1;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            prefixSum[i] = sum;

            if (prefixSum[i] == 0) {
                start = 0;
                end = i;
                maxLength = i + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (map.containsKey(prefixSum[i])) {
                System.out.println("123 : " + map.get(prefixSum[i]));
                int len = i - map.get(prefixSum[i]);
                if (len > maxLength) {
                    start = map.get(prefixSum[i]) + 1;
                    end = i;
                    maxLength = len;
                }
            } else {
                map.put(prefixSum[i], i);
            }
        }
        ArrayList<Integer> listNew = new ArrayList<>();
        if (start == -1 || end == -1) {
            listNew.add(-1);
        } else {
            for (int i = start; i <= end; i++) {
                listNew.add(arr[i]);
            }
        }
        // return listNew;
        System.out.println(listNew);
    }

}
