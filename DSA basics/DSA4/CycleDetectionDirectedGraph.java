import java.util.ArrayList;

public class CycleDetectionDirectedGraph {
    // DFS
    public static int cycleDirectedGraph(int n, int[][] edges) {
        
        if (n <= 0 || edges == null) {
            return 0; // Invalid input
        }
        // Create adjacency list representation of the graph
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build adjacency list from the given edges
        for (int[] edge : edges) {
            int u = edge[0] - 1; // Adjust for 1-based indexing
            int v = edge[1] - 1;
            adjacencyList.get(u).add(v); // Add directed edge from u to v
        }

        // Arrays to track visited nodes and recursion stack
        boolean[] visited = new boolean[n];
        boolean[] recursionStack = new boolean[n];

        // Check for cycles starting from each node
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasCycle(adjacencyList, i, visited, recursionStack)) {
                return 1; // Cycle detected
            }
        }

        return 0; // No cycle found
    }

    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList, int node,
            boolean[] visited, boolean[] recursionStack) {
        visited[node] = true;
        recursionStack[node] = true;

        // Explore neighbors of the current node
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor] && hasCycle(adjacencyList, neighbor, visited, recursionStack)) {
                return true; // Cycle found in the recursion
            } else if (recursionStack[neighbor]) {
                return true; // Back edge detected, cycle exists
            }
        }

        recursionStack[node] = false; // Remove from recursion stack
        return false; // No cycle found
    }

    public static void main(String[] args) {
        int nodes = 4;
        int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };

        int hasCycle = cycleDirectedGraph(nodes, edges);
        System.out.println("Does the directed graph have a cycle? " + (hasCycle == 1 ? "Yes" : "No"));
    }
}
