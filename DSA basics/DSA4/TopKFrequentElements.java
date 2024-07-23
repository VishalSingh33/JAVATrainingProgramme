import java.util.*;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a)); // Use map.get() to compare frequencies

        for (Integer key : map.keySet()) { // Iterate through keys, not values
            maxHeap.offer(key); // Add key (element) to the max heap
        }

        for (int i = 0; i < k; i++) { // Use '<' instead of '<=' here
            list.add(maxHeap.poll()); // Add the most frequent element to the result list
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> result = topKFrequent(nums, k);
        System.out.println("The " + k + " most frequent elements are: " + result);
    }
}
