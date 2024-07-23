import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RouteBetweenNodes {
    // Implement Solution Here
    static boolean routeBetweenNodes(int source,
            int destination, int n, ArrayList<ArrayList<Integer>> edges) {

        // if (source == destination) {
        // return true; // Source and destination are the same node
        // }
        // Create the graph's adjacency list
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the adjacency list representation of the graph
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0) - 1; // Adjust for 1-based indexing
            int v = edge.get(1) - 1;
            adjacencyList.get(u).add(v); // Add directed edge from u to v
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source - 1); // Adjust for 0-based indexing
        visited[source - 1] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int neighbor : adjacencyList.get(currentNode)) {
                if (neighbor == destination - 1) {
                    return true; // Found a route to the destination
                }

                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

        edges.add(new ArrayList<>());
        edges.add(new ArrayList<>());

        edges.get(0).add(2);
        edges.get(1).add(1);
        edges.get(1).add(2);

        int source = 1;
        for (int neighbor : edges.get(source - 1)) {
            System.out.println(routeBetweenNodes(neighbor, 1, 2, edges) ? "true" : "false");
        }
    }
}
