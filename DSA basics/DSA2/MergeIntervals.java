import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        // Given a collection of intervals, merge all overlapping intervals. The result
        // should only have mutually exclusive intervals - meaning that no number should
        // be common between two intervals, in the result.
        // Note: The merged intervals should be printed in incrsing order of strt val.

        // int[][] intervals = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        int[][] intervals = { { 1, 3 }, { 2, 4 }, { 5, 7 }, { 6, 8 } };

        if (intervals == null || intervals.length <= 1) {
            // return intervals;
            System.out.println(intervals);
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            int[] interval = intervals[i];

            if (interval[0] <= currentInterval[1]) {
                System.out.println(interval[0]);
                System.out.println(currentInterval[1]);
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                merged.add(currentInterval);
                currentInterval = interval;
            }
        }
        merged.add(currentInterval);
        // return merged.toArray(new int[merged.size()][]);

        // System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));

        for (int[] num : merged) {
            System.out.print(Arrays.toString(num));
        }
    }
}
