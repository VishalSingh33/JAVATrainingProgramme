import java.util.PriorityQueue;

public class KthLargest {
    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 5, 6, 4}; 
        int k = 3;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
       
        for (int num : nums) {
            minHeap.offer(num);

            // If the size of the min heap exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // The top element of the min heap will be the kth largest element
        System.out.println(minHeap.peek());
    }

}
