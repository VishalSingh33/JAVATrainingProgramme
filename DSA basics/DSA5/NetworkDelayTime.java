import java.util.*;

// Problem Description
// There are N network nodes, labelled 1 to N.

// You are given a list of signal travel times as directed edges times[i] = (u, v, w), 
// where u is the source node, v is the target node, and w is the time it takes for a 
// signal to travel from source to target.

// Now, we transmit a signal from a certain node K. How long will it take for 
// all nodes to receive the signal? If it is impossible, return -1.

// Input format
// First line contains two space separated integers N and E, where N represents 
// the number of network nodes and E represents the number of connections between 
// the nodes, respectively.

// Next E lines contain three space separated integers U,V and W, where U is 
// the source node number, V is the target node number and W is the time taken 
// for the signal to go from node U to node V. (Nodes are numbered from 1 to N)

// Next line contains an integer K, which is the node from which the signal is generated.

public class NetworkDelayTime {
    public static int networkDelayTime(int N, ArrayList<ArrayList<Integer>> edges, int K) {
        // Create an adjacency list to represent the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            graph.get(u).add(new int[] { v, w });
        }

        // Use a priority queue to implement Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { K, 0 }); // Start from node K with distance 0

        // Distance array to track the shortest path to each node
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        // Process the nodes in the priority queue
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dist[u])
                continue; // Skip if the current distance is not optimal

            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[] { v, dist[v] });
                }
            }
        }

        // Find the maximum distance from the source node
        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1; // If any node is unreachable
            maxDist = Math.max(maxDist, dist[i]);
        }

        return maxDist;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int E = scanner.nextInt();

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            ArrayList<Integer> edge = new ArrayList<>(Arrays.asList(u, v, w));
            edges.add(edge);
        }

        int K = scanner.nextInt();
        System.out.println(networkDelayTime(N, edges, K));
        scanner.close();
    }
}

// Graph Representation: An adjacency list is used to represent the graph, where
// each node points to a list of pairs representing its neighbors and the
// corresponding edge weights.

// Priority Queue: A priority queue (min-heap) is used to always extend the
// shortest known path, which is the core idea of Dijkstra's algorithm.

// Distance Array: An array dist is used to keep track of the shortest known
// distance to each node. Initially, all distances are set to infinity
// (Integer.MAX_VALUE), except for the starting node
// 𝐾
// K, which is set to 0.

// Relaxation: For each node processed, the distances to its neighbors are
// updated if a shorter path is found.

// Result: After processing all reachable nodes, the maximum distance in the
// dist array is found. If any node remains unreachable (dist[i] is still
// Integer.MAX_VALUE), the function returns -1. Otherwise, it returns the
// maximum distance.