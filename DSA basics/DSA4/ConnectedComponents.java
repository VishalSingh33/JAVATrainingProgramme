import java.util.*;

// Given an undirected graph, you have to find the number of connected components in that graph.

// Input format
// First line contains 2 integers N and E, representing the number of nodes and edges respectively.

// Next E lines contain 2 space separated integers U and V, representing an edge between nodes U and V. 
// Nodes are numbered from 1 to N

public class ConnectedComponents {

    private static int connectedComponentsInGraph(int n, ArrayList<ArrayList<Integer>> edges) {
        // DFS
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adjacencyList.get(u - 1).add(v);
            adjacencyList.get(v - 1).add(u); // Undirected graph
        }

        boolean[] visited = new boolean[n];
        int connectedComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i + 1, adjacencyList, visited);
                connectedComponents++;
            }
        }
        return connectedComponents;
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited) {
        visited[node - 1] = true;

        for (int neighbor : adjacencyList.get(node - 1)) {
            if (!visited[neighbor - 1]) {
                dfs(neighbor, adjacencyList, visited);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6; // Number of nodes
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(4, 5)));
        edges.add(new ArrayList<>(Arrays.asList(5, 6)));

        System.out.println(connectedComponentsInGraph(n, edges)); // Output: 2
    }
}
