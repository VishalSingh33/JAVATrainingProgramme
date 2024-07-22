import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Problem Description
// There are N cities and roads between some of the cities. 
// Most of the roads have been damaged due to rains. We have to repair the
// roads to connect the cities again. There is a fixed cost to repair a particular
// road. Find the minimum cost to connect all the cities by repairing the roads.

// Note: If the cost of repairing a road is 0, that means it is not damaged.

// Input format
// First-line contains two space-separated numbers N and M where N denotes
//  the number of cities and M denotes the number of roads, respectively. 
// (Cities are numbered from 1 to N)

// Next M lines contain three space-separated numbers U V and W, where U and V denote
// the city numbers between which the road exists and W denotes the cost to repair that road.

class Edge {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

public class MinCostOfRoad {

    static class UnionFind {
        private int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) {
                return false;
            }

            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }

    public static long minCostOfRoad(int n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (ArrayList<Integer> edge : edges) {
            edgeList.add(new Edge(edge.get(0) - 1, edge.get(1) - 1, edge.get(2)));
        }

        Collections.sort(edgeList, Comparator.comparingInt(e -> e.weight));

        UnionFind uf = new UnionFind(n);
        long totalCost = 0;

        for (Edge edge : edgeList) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.weight;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(scanner.nextInt());
            edge.add(scanner.nextInt());
            edge.add(scanner.nextInt());
            edges.add(edge);
        }
        scanner.close();

        System.out.println(minCostOfRoad(n, edges));
    }
}

// Kruskal's Algorithm
// Kruskal's algorithm works by sorting all the edges in increasing order of
// their weights and then adding edges to the MST one by one while ensuring no
// cycles are formed (using a Union-Find data structure).

// Implementation Steps:
// Read the input values.
// Sort the edges by their weight.
// Initialize Union-Find to keep track of connected components.
// Iterate over the sorted edges and add them to the MST if they connect two
// different components.
// Sum up the costs of the selected edges to get the minimum cost.