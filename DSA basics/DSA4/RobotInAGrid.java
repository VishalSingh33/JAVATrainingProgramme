import java.util.*;

// Imagine a robot sitting in the upper left corner of a grid with R rows and C columns. 
// The robot can only move in two directions, right and down. Certain cells are "off limits" such 
// that the robot cannot step on them. Find a path for the robot from the top left to the bottom right.

// Input format
// First line will contain two space separated integers N and M, where N denotes the number of 
// rows and M denotes the number of columns in the grid, respectively.

// Next N lines will have M space separated integers which represent the grid. 
// A 0 value means the robot can enter this cell and 1 value means this cell is blocked.

public class RobotInAGrid {
    public static Vector<String> robotInAGrid(int[][] grid, int n, int m) {

        Vector<String> path = new Vector<>();
        if (grid == null || n <= 0 || m <= 0 || grid[0][0] == 1
                || grid[n - 1][m - 1] == 1) {
            path.add("Not Possible");
            return path;
        }

        boolean[][] visited = new boolean[n][m];
        if (findPath(grid, n, m, 0, 0, path, visited)) {
            path.add("1 1"); // Add the starting position to the path
            reversePath(path); // Reverse the path to correct the order
        } else {
            path.add("Not Possible");
            // path.clear(); // No valid path found, clear the path vector
        }
        return path;
    }

    private static boolean findPath(int[][] grid,
            int n, int m, int row, int col, Vector<String> path, boolean[][] visited) {

        if (row < 0 || row >= n || col < 0 || col >= m
                || grid[row][col] == 1 || visited[row][col]) {

            return false; // Out of bounds, cell is off-limits, or already visited
        }

        visited[row][col] = true; // Mark current cell as visited

        if (row == n - 1 && col == m - 1) {
            return true; // Reached the bottom-right cell
        }

        // Try moving right
        if (findPath(grid, n, m, row, col + 1, path, visited)) {
            path.add((row + 1) + " " + (col + 2)); // Add the position to the path
            return true;
        }

        // Try moving down
        if (findPath(grid, n, m, row + 1, col, path, visited)) {
            path.add((row + 2) + " " + (col + 1)); // Add the position to the path
            return true;
        }

        return false;
    }

    private static void reversePath(Vector<String> path) {
        int left = 0;
        int right = path.size() - 1;
        while (left < right) {
            String temp = path.get(left);
            path.set(left, path.get(right));
            path.set(right, temp);
            left++;
            right--;
        }

    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0 },
                { 1, 1, 0 },
                { 0, 0, 0 }
        };
        int n = 3, m = 3;
        Vector<String> path = robotInAGrid(grid, n, m);
        for (String cell : path) {
            System.out.println(cell);
        }
    }
}
