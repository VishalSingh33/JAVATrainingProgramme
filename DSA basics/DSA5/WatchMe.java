import java.util.*;

// Problem Description
// Watch Me is a wierd and complicated movie series which has n seasons (0 to n-1). 
// The seasons of this series are to be watched in a particular order to understand the 
// series completely. Note that there can be more than one order to watch the seasons. 
// Your small brother wants to watch the series. He wants you to tell him an order in 
// which he can watch the series to understand completely. You are given an array 
// (prerequisites) and each element of the array is of the form [int a, int b] which 
// denotes that season b needs to be watched before season a. Return any valid sequence 
// in which your brother can watch to understand completely. Note that it might be impossible 
// to watch the series in any order. In that case return an empty array.

// Input format
// First line contains two space separated integers, numSeasons denoting the number of 
// seasons and n denoting the size of the prerequisites array.
// Next n line contains two space separated integers each.

public class WatchMe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSeasons = sc.nextInt();
        int n = sc.nextInt();
        int[][] pre = new int[n][2];

        for (int i = 0; i < n; i++) {
            pre[i][0] = sc.nextInt();
            pre[i][1] = sc.nextInt();
        }

        int[] result = watchMe(numSeasons, n, pre);
        if (result.length == 0) {
            System.out.println("It's impossible to watch the series in any order.");
        } else {
            for (int season : result) {
                System.out.print(season + " ");
            }
        }
        sc.close();
    }

    static int[] watchMe(int numSeasons, int n, int[][] pre) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numSeasons];

        // Initialize graph
        for (int i = 0; i < numSeasons; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph and fill in-degree array
        for (int[] pair : pre) {
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        // Initialize the queue with all nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numSeasons; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Perform topological sort
        int[] order = new int[numSeasons];
        int index = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current;

            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check if there was a cycle
        if (index != numSeasons) {
            return new int[0];
        }

        return order;
    }
}

// Explanation:
// Input Reading:

// The main method reads the number of seasons numSeasons, the number of
// prerequisite pairs n, and the prerequisites array pre.

// Graph Representation:
// An adjacency list graph is created to represent the graph of seasons.
// An inDegree array is used to store the in-degree of each season.

// Graph Construction:
// For each prerequisite pair, update the graph and the in-degree array.

// Topological Sort:
// Use a queue to perform BFS. Start with nodes with an in-degree of zero.
// Process each node, updating the in-degrees of its neighbors.
// If a neighbor's in-degree becomes zero, add it to the queue.

// Cycle Detection:
// If the number of nodes processed is less than the total number of nodes,
// return an empty array indicating that it's impossible to find a valid
// watching order.