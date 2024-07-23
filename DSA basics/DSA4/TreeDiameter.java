import java.util.ArrayList;
import java.util.Vector;

public class TreeDiameter {

    private static int diameterOfTree(int n, Vector<Vector<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0) - 1; // Adjust for 1-based indexing
            int v = edge.get(1) - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u); // For undirected graph, add both directions
        }

        int[] result = dfs(1, adjList, new boolean[n + 1]);
        int furthestNode = result[0];
        int maxDistance1 = result[1];
        int[] finalResult = dfs(furthestNode, adjList, new boolean[n + 1]);
        int maxDistance2 = finalResult[1];

        return Math.max(maxDistance1, maxDistance2);
    }

    private static int[] dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        int maxDistance = 0;
        int furthestNode = node;

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                int[] result = dfs(neighbor, adjList, visited);
                int distanceToNeighbor = result[1] + 1;
                if (distanceToNeighbor > maxDistance) {
                    maxDistance = distanceToNeighbor;
                    furthestNode = result[0];
                }
            }
        }

        return new int[]{furthestNode, maxDistance};
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        Vector<Vector<Integer>> edges = new Vector<>();

        // Define the edges of the tree
        Vector<Integer> edge1 = new Vector<>();
        edge1.add(1);
        edge1.add(2);
        edges.add(edge1);

        Vector<Integer> edge2 = new Vector<>();
        edge2.add(1);
        edge2.add(3);
        edges.add(edge2);

        Vector<Integer> edge3 = new Vector<>();
        edge3.add(2);
        edge3.add(4);
        edges.add(edge3);

        Vector<Integer> edge4 = new Vector<>();
        edge4.add(2);
        edge4.add(5);
        edges.add(edge4);

        int diameter = diameterOfTree(n, edges);
        System.out.println("Diameter of the tree: " + diameter);
    }
}
