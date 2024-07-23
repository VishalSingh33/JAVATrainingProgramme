import java.util.ArrayList;

public class CycleDetectionUndirectedGraph {
    // DFS
    public static String detectCycle(int nodes, ArrayList<ArrayList<Integer>> edges) {
        
        if (nodes <= 0 || edges == null) {
            return "Invalid input";
        }
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build adjacency list representation of the graph
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0) - 1; // Adjust for 1-based indexing
            int v = edge.get(1) - 1;
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u); // For undirected graph, add both directions
        }

        boolean[] visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            if (!visited[i] && hasCycle(adjacencyList, i, -1, visited)) {
                return "Yes"; // Cycle exists
            }
        }

        return "No"; // No cycle
    }

    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList, int node, int parent,
            boolean[] visited) {
        visited[node] = true;

        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(adjacencyList, neighbor, node, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int nodes = 4;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>() {
            {
                add(1);
                add(2);
            }
        });
        edges.add(new ArrayList<>() {
            {
                add(2);
                add(3);
            }
        });
        edges.add(new ArrayList<>() {
            {
                add(3);
                add(4);
            }
        });
        edges.add(new ArrayList<>() {
            {
                add(4);
                add(2);
            }
        });

        String result = detectCycle(nodes, edges);
        System.out.println("Does the undirected graph have a cycle? " + result);
    }
}
