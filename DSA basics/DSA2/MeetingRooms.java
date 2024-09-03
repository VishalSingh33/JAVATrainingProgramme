import java.util.Arrays;

public class MeetingRooms {
    public static void main(String[] args) {
        // Minimum number of room requirements for meeting/parking/

        int[][] intervals = { { 0, 20 }, { 5, 10 }, { 10, 15 } };
        // int n = 3;

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            System.out.println(0); // return 0;
        }
        int n = intervals.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int roomsNeeded = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            if (startTimes[i] < endTimes[endIndex]) {
                roomsNeeded++;
            } else {
                endIndex++;
            }
        }
        System.out.println(roomsNeeded); // return roomsNeeded;
    }

}
