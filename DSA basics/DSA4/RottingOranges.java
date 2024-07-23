import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// n a given grid, each cell can have one of three values:
// Value 0 representing an empty cell
// Value 1 representing a fresh orange
// Value 2 representing a rotten orange
// Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
// Print the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, print -1 instead.

public class RottingOranges {
    // BFS
    public static int rottingOranges(ArrayList<ArrayList<Integer>> grid) {
        if (grid == null || grid.size() == 0 || grid.get(0).size() == 0) {
            return -1;
        }

        int rows = grid.size();
        int cols = grid.get(0).size();
        int freshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int minutes = 0;

        // Count fresh oranges and add rotten oranges to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).get(j) == 1) {
                    freshOranges++;
                } else if (grid.get(i).get(j) == 2) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : directions) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                            grid.get(newRow).get(newCol) == 1) {
                        grid.get(newRow).set(newCol, 2); // Mark as rotten
                        queue.offer(new int[] { newRow, newCol });
                        freshOranges--;
                    }
                }
            }
            minutes++;
        }

        return freshOranges == 0 ? minutes : -1;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(List.of(2, 1, 1)));
        grid.add(new ArrayList<>(List.of(1, 1, 0)));
        grid.add(new ArrayList<>(List.of(0, 1, 1)));

        System.out.println(rottingOranges(grid)); // Output: 4
    }
}
