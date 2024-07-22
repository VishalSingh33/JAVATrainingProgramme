import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Problem Description
// A locality is in the shape of a n*m grid where each cell in a grid represents a house. 
// There is a serious virus outbreak in this locality. This deadly virus converts people 
// to zombies. Each cell in the grid has one of the following three values:

// 0 -> House is empty
// 1 -> House has people but not yet infected.
// 2 -> House is infected and has zombies.
// In one minute all the non-empty houses adjacent to an infected house get infected 
// and the people are converted into zombies 
// (Two houses are called adjacent if they share a common edge). If all the people are 
// infected then the government will have to bomb the locality.

// Print the minimum time after which all the non-empty houses get infected 
// (Basically no cells in the grid should be left 1. All cell values should be 2 or 0). 
// If all the non-empty houses will never get infected then print -1.

public class Zombies {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println(zombies(grid, n, m));
        sc.close();
    }

    static int zombies(int[][] grid, int n, int m) {
        int[] directions = { -1, 0, 1, 0, -1 }; // To easily get all 4 adjacent directions
        Queue<int[]> queue = new LinkedList<>();
        int humans = 0; // Count of cells with people

        // Initialize the queue with all initially infected cells and count humans
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    humans++;
                }
            }
        }

        if (humans == 0) {
            return 0; // No people to infect
        }

        int minutes = 0;

        // BFS to spread the infection
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infected = false;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + directions[d];
                    int ny = y + directions[d + 1];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2; // Infect the house
                        queue.add(new int[] { nx, ny });
                        humans--;
                        infected = true;
                    }
                }
            }

            if (infected) {
                minutes++;
            }
        }

        return humans == 0 ? minutes : -1;
    }
}

// Explanation:

// Input Reading:
// The grid dimensions and the grid itself are read from the input.

// Initialization:
// The queue is initialized with all cells that have zombies (2).
// The count of houses with people (humans) is initialized.

// BFS:
// The BFS process iterates minute by minute.
// For each cell that is currently infected, it tries to infect its adjacent
// non-empty houses.
// If any new infections occur in the current minute, the minute counter is
// incremented.

// Completion Check:
// If all houses with people get infected, the total time taken is returned.
// If there are still houses with people left after the BFS completes, -1 is
// returned.
