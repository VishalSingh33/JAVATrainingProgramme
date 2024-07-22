import java.util.*;

// Problem Description
// You are given a list of cities and flights that operate between the cities, 
// along with their price. You are also given a source city and a destination city. 
// Your task is to find the cheapest price to go from source to destination city with 
// a maximum of K flight changes. If there is no such route, output -1.

// Note: The flights are not bidirectional.

// Note: There will not be any duplicated flights or self cycles.

// Input format
// The first line contains two space separated integers, N and M, where N is the 
// number of cities and M is the number of flights, respectively. Cities are numbered from 1 to N.

// The next M lines have three space separated integers, U, V and W. The flight 
// goes from city numbered U to city numbered V and W is the price for the flight.

// The next line will contain 3 space separated integers U, V and K. U and V 
// represent the source city number and destination city number, while K represents 
// the maximum number of flight changes.

public class CheapestFlights {
    public static int cheapestFlights(int n, ArrayList<ArrayList<Integer>> flightList, int source, int des, int k) {
        // Initialize the dp table
        int[][] dp = new int[k + 2][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][source] = 0;

        // Fill the dp table
        for (int i = 1; i <= k + 1; i++) {
            dp[i][source] = 0;
            for (ArrayList<Integer> flight : flightList) {
                int u = flight.get(0);
                int v = flight.get(1);
                int w = flight.get(2);
                if (dp[i - 1][u] != Integer.MAX_VALUE) {
                    dp[i][v] = Math.min(dp[i][v], dp[i - 1][u] + w);
                }
            }
        }

        // Find the minimum cost to reach the destination with up to k stops
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= k + 1; i++) {
            minCost = Math.min(minCost, dp[i][des]);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> flightList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            ArrayList<Integer> flight = new ArrayList<>(Arrays.asList(u, v, w));
            flightList.add(flight);
        }

        int source = scanner.nextInt();
        int des = scanner.nextInt();
        int k = scanner.nextInt();

        System.out.println(cheapestFlights(n, flightList, source, des, k));
        scanner.close();
    }
}

// Explanation:
// Method Signature: The method signature now correctly matches the given
// problem statement, using flightList instead of flight to avoid naming
// conflicts.
// Variable Usage: The variables source and des are used consistently within the
// method.
// Dynamic Programming Table Update: The DP table updates and calculations are
// performed correctly to determine the minimum cost with at most k stops.