import java.util.ArrayList;
import java.util.Arrays;

public class MaxAreaOfIsland {

    // Given a non-empty 2D array grid of 0s and 1s, an island is a group of 1s
    // (representing land) connected 4-directionally (horizontal or vertical). You
    // may assume all four edges of the grid are surrounded by water. Find the
    // maximum area of an island in the given 2D array (If there is no island, the
    // maximum area is 0).

    // Input format
    // First line contains 2 space separated integers N and M, representing the
    // number of rows and columns respectively.
    // Next N lines contain M space separated integers which can be 0 or 1.

    public static int maxAreaOfIsland(ArrayList<ArrayList<Integer>> grid) {
        //DFS
        if (grid == null || grid.size() == 0) {
            return 0;
        }
        int maxArea = 0;
        int rows = grid.size();
        int cols = grid.get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).get(j) == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private static int dfs(ArrayList<ArrayList<Integer>> grid, int row, int col) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        if (row < 0 || col < 0 || row >= rows || col >= cols || grid.get(row).get(col) == 0) {
            return 0;
        }

        grid.get(row).set(col, 0); // Mark current cell as visited

        int area = 1;
        area += dfs(grid, row - 1, col); // Up
        area += dfs(grid, row + 1, col); // Down
        area += dfs(grid, row, col - 1); // Left
        area += dfs(grid, row, col + 1); // Right

        return area;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)));
        grid.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)));
        grid.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1)));
        grid.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1)));

        System.out.println(maxAreaOfIsland(grid)); // Output: 4
    }
}
