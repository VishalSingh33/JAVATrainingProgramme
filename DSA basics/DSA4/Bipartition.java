import java.util.*;

// Given a set of N Countries which are numbered from 1 to N, split the Countries into two groups, 
// of any size, such that the following condition is met.

// If a Country dislikes another Country, they should not be in the same group. If dislikes[i] = [a, b], 
// we should not put Country numbered a and Country numbered b into the same group.

// Print out if this kind of partitioning is possible or not.

public class Bipartition {
    // BFS
    private static String possibleBipartition(int n, Vector<Vector<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adjacencyList.get(u - 1).add(v);
            adjacencyList.get(v - 1).add(u);
        }

        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                if (!bfs(i + 1, adjacencyList, colors)) {
                    return "No"; // Dislikes found within the same group
                }
            }
        }
        return "Yes"; // Bipartition possible
    }

    private static boolean bfs(int source, ArrayList<ArrayList<Integer>> adjacencyList, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        colors[source - 1] = 0; // Assign color 0 to the source node

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int neighbor : adjacencyList.get(currentNode - 1)) {
                if (colors[neighbor - 1] == -1) {
                    colors[neighbor - 1] = 1 - colors[currentNode - 1]; // Assign opposite color
                    queue.offer(neighbor);
                } else if (colors[neighbor - 1] == colors[currentNode - 1]) {
                    return false; // Dislikes found within the same group
                }
            }
        }

        return true; // No dislikes within the same group
    }

    public static void main(String[] args) {
        int n = 5;
        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(Arrays.asList(1, 2)));
        edges.add(new Vector<>(Arrays.asList(2, 3)));
        edges.add(new Vector<>(Arrays.asList(3, 4)));
        edges.add(new Vector<>(Arrays.asList(4, 5)));
        edges.add(new Vector<>(Arrays.asList(1, 5)));

        System.out.println(possibleBipartition(n, edges)); // Output: Yes
    }
}
