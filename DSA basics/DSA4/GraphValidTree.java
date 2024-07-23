import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Q: Check if the Tree is Graph or not

// You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges 
// where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

// Return 1 if the edges of the given graph make up a valid tree, and 0 otherwise.
// (Note : A graph is a tree if it has the following properties.
// It is connected
// It has no cycle.)

public class GraphValidTree {
    public static int graphValidTree(int n, int[][] edges) {

        // implementation using BFS:
        if (n <= 0 || edges == null) {
            return 0; // Invalid input
        }

        // Create an adjacency list to represent the graph
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the adjacency list using the given edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0); // Start BFS from node 0

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                } else {
                    // If a neighbor is already visited and not the parent, then there is a cycle
                    if (neighbor != node && !adjacencyList.get(neighbor).contains(node)) {
                        return 0; // Cycle detected, not a valid tree
                    }
                }
            }
        }
        // Check if all nodes are visited after BFS traversal
        for (boolean nodeVisited : visited) {
            if (!nodeVisited) {
                return 0; // Not all nodes are reachable, not a valid tree
            }
        }
        return 1;

        // // implementation using DFS:
        // if (n <= 0 || edges == null) {
        // return 0; // Invalid input
        // }

        // // Create an adjacency list to represent the graph
        // ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        // for (int i = 0; i < n; i++) {
        // adjacencyList.add(new ArrayList<>());
        // }

        // // Build the adjacency list using the given edges
        // for (int[] edge : edges) {
        // int u = edge[0];
        // int v = edge[1];
        // adjacencyList.get(u).add(v);
        // adjacencyList.get(v).add(u);
        // }

        // boolean[] visited = new boolean[n];
        // // Check if the graph is connected and has no cycle
        // if (hasCycle(adjacencyList, visited, 0, -1)) {
        // return 0; // Cycle detected, not a valid tree
        // }

        // // Check if all nodes are visited after DFS traversal
        // for (boolean nodeVisited : visited) {
        // if (!nodeVisited) {
        // return 0; // Not all nodes are reachable, not a valid tree
        // }
        // }

        // return 1; // Valid tree
        // }

        // private static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList,
        // boolean[] visited, int node, int parent) {
        // visited[node] = true;

        // for (int neighbor : adjacencyList.get(node)) {
        // if (!visited[neighbor]) {
        // if (hasCycle(adjacencyList, visited, neighbor, node)) {
        // return true; // Cycle detected
        // }
        // } else if (neighbor != parent) {
        // return true; // Back edge detected, cycle exists
        // }
        // }
        // return false; // No cycle found
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } };

        int isValidTree = graphValidTree(n, edges);
        System.out.println("Is the graph a valid tree? " + isValidTree);
    }
}
