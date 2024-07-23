import java.util.*;

// You are given a network of major and minor cities. The cities have bridges connecting them, 
// with each bridge having a distance of 1. Major cities have police stations and fall under their
// own jurisdiction. Jurisdiction of a minor city falls under the nearest major city. 
// Your task is to report the minimum distance between each city and it's nearest major city. 
// Note: For a major city, this distance will be 0.

// Note: All cities are connected, but maybe not directly.

// Input format
// First line will contain two space separated integers, N and B, representing the number of 
// cities and the number of bridges between them, respectively.

// Next B lines contain two space separated integers, X and Y, representing a bridge between city X and city Y.
//  Cities are numbered from 1 to N.

// Next line contains an integer S representing the number of major cities

// Next line contains S space separated integers, representing major cities

public class TownsAndPoliceStations {

    public static ArrayList<Integer> townsAndPoliceStations(int n, ArrayList<ArrayList<Integer>> edges,
            ArrayList<Integer> sources) {
        // Step 1: Initialize an array of ArrayLists to store node connections.
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Populate the array with empty ArrayLists.
        for (ArrayList<Integer> edge : edges) {
            // Step 3: Iterate through edges, adding connections to the array.
            int u = edge.get(0), v = edge.get(1);
            adjList.get(u).add(v);
            adjList.get(v).add(u); // For undirected graph, add both directions
        }

        // Step 4: Create a priority queue based on distances.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // Step 5: Initialize an array for node distances, setting all to infinity.
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Step 6: Iterate through source nodes, setting distances to 0 and adding to
        // the priority queue.
        for (int source : sources) {
            distances[source] = 0;
            pq.offer(new Node(source, 0));
        }

        // Step 7: While the priority queue is not empty:
        while (!pq.isEmpty()) {
            Node node = pq.poll(); // Extract the node with the minimum distance.
            int u = node.city, dist = node.distance;

            // Iterate through neighbors of the current node.
            for (int neighbor : adjList.get(u)) {
                int newDist = dist + 1; // Assuming edge weight is 1

                // Update distances of neighboring nodes if shorter path found, add them to the
                // queue.
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    pq.offer(new Node(neighbor, newDist));
                }
            }
        }

        // Step 8: Extract distances from the array and store in an ArrayList.
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            result.add(distances[i]);
        }

        // Step 9: Return the ArrayList of distances.
        return result;
    }

    // Node class for priority queue
    static class Node implements Comparable<Node> {
        int city;
        int distance;

        public Node(int city, int distance) {
            this.city = city;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void main(String[] args) {
        int n = 7; // Number of towns
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4)));
        edges.add(new ArrayList<>(Arrays.asList(2, 5)));
        edges.add(new ArrayList<>(Arrays.asList(3, 6)));
        edges.add(new ArrayList<>(Arrays.asList(3, 7)));

        ArrayList<Integer> sources = new ArrayList<>(Arrays.asList(1, 3)); // Police stations

        ArrayList<Integer> distances = townsAndPoliceStations(n, edges, sources);
        System.out.println("Minimum distances to nearest police stations:");
        for (int i = 0; i < distances.size(); i++) {
            System.out.println("Town " + (i + 1) + ": " + distances.get(i));
        }
    }
}
