import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

// Topological Sort of a directed graph is a linear ordering of its vertices such that for every directed 
// edge (U,V) from vertex U to vertex V, U comes before V in the ordering.

// Given a directed graph, find the Topological ordering of its vertices.

// Note: A valid topological ordering of the graph will always be present i.e., there will be no cycle.

public class TopologicalSort {
    // DFS
    private static void dfs(int node, boolean[] visited,
            ArrayList<ArrayList<Integer>> adjacencyList, Stack<Integer> stack) {

        visited[node] = true;

        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adjacencyList, stack);
            }
        }
        stack.push(node);
    }

    public static Vector<Integer> topologicalOrdering(int n, Vector<Vector<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the adjacency list representation of the graph
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0) - 1; // Adjust for 1-based indexing
            int v = edge.get(1) - 1;
            adjacencyList.get(u).add(v);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // Perform Depth-First Search (DFS) to generate the topological ordering
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, adjacencyList, stack);
            }
        }

        Vector<Integer> topologicalOrder = new Vector<>();
        while (!stack.isEmpty()) {
            topologicalOrder.add(stack.pop() + 1); // Adjust for 1-based indexing
        }

        return topologicalOrder;
    }

    public static void main(String[] args) {
        Vector<Vector<Integer>> edges = new Vector<>();
        Vector<Integer> edge1 = new Vector<>();
        edge1.add(1);
        edge1.add(2);
        Vector<Integer> edge2 = new Vector<>();
        edge2.add(1);
        edge2.add(3);
        Vector<Integer> edge3 = new Vector<>();
        edge3.add(2);
        edge3.add(4);
        Vector<Integer> edge4 = new Vector<>();
        edge4.add(3);
        edge4.add(4);
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);

        Vector<Integer> topologicalOrder = topologicalOrdering(4, edges);
        System.out.println(topologicalOrder); // Output: [1, 3, 2, 4] or [1, 2, 3, 4]
    }
}
