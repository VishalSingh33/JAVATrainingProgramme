import java.util.Arrays;

// Problem Description
// Mukesh is a factory worker who works in a city which is far from his home. 
// Because of the pandemic he has lost his job. So, he decided of going back to his city. 
// There are N cities which are connected by some number of busses, trains and flights. 
// Since Mukesh has lost his job he doesn’t wants to spend a lot of money in travelling. 
// So he decided to go by bus. You are given an array bus where bus[i] = [fromi, toi, pricei] 
// indicates that there is a bus from city fromi to city toi with cost pricei. He can change 
// the bus at most k times. You are also given three integers, src (the city where he works), 
// dest (the city where he wants to go) and an integer k. Since Mukesh doesn’t wants to spend 
// to much money, print the cheapest price from src to dest with at most k stops. If there is 
// no such route, print -1.

// Input format
// Given an integer N.(Number of cities)
// Given an integer M.(Number of busses)
// Next N lines contains 3 integers each.(Bus from cityi to cityj with a cost of x).
// Next line conatins 3 integers. (src,dest,k).

public class Pandemic {
    public static void main(String[] args) {
        int n = 5;
        int[][] bus = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 3, 100},
            {3, 4, 100},
            {0, 2, 500}
        };
        int src = 0;
        int dest = 4;
        int k = 2;
        int m = 5;
        
        int result = pandemic(n, bus, src, dest, k, m);
        System.out.println(result);
    }

    static int pandemic(int n, int[][] bus, int src, int dest, int k, int m) {
        // Initialize the distance array
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;

        // Perform the Bellman-Ford relaxation for at most k+1 times
        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(cost, n);
            for (int[] route : bus) {
                int u = route[0];
                int v = route[1];
                int w = route[2];
                
                if (cost[u] != Integer.MAX_VALUE && cost[u] + w < temp[v]) {
                    temp[v] = cost[u] + w;
                }
            }
            cost = temp;
        }

        return cost[dest] == Integer.MAX_VALUE ? -1 : cost[dest];
    }
}

// Explanation:
// Initialize Distances:

// We use an array cost to store the minimum cost to reach each city. Initialize all values to Integer.MAX_VALUE except the source city, which is set to 0.
// Relax Edges:

// We perform the relaxation process up to k+1 times (because with k bus rides, there can be at most k+1 edges in the path).
// In each iteration, we make a copy of the current cost array and update it based on the bus routes.
// Check for Path:

// After the relaxation process, we check the cost to reach the destination city. If it is still Integer.MAX_VALUE, it means there is no valid path within k bus rides, and we return -1. Otherwise, we return the minimum cost.
