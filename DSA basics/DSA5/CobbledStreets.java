import java.util.*;

// Problem Description
// Once upon a time, King Günther decided to visit all the towns in his kingdom. 
// It was necessary that all the streets that the King used would have to be cobbled with stone.

// Unfortunately, the towns didn’t have much money. 
// The citizens of major towns decided to pave only as many streets as
//  were absolutely necessary to reach every major building since the King
//  would only visit major buildings.

// Can you help the citizens of the town to find out which streets should be paved?

// Note: All major buildings are either at the end of a street or at an intersection.
// Note: In addition to that, you can assume that all buildings are connected by the given streets.

// Input format
// First line would contain T, the number of test cases
// Each test case will have the following lines:
// First line contains three space separated integers, p, n and m, representing
//  the price to pave one furlong of street, the number of main buildings in the town 
//  and the number of streets in the town respectively. (Buildings are numbered from 1 to n)

// Next m lines contain three space separated integers a, b and c, where a and b are 
// the building numbers between which the street runs and c is the length of the street in furlongs.

public class CobbledStreets {

    static class Edge {
        int u, v, length;

        Edge(int u, int v, int length) {
            this.u = u;
            this.v = v;
            this.length = length;
        }
    }

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

    public static int cobbledStreets(int n, ArrayList<ArrayList<Integer>> streets, int p) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (ArrayList<Integer> street : streets) {
            edges.add(new Edge(street.get(0) - 1, street.get(1) - 1, street.get(2)));
        }

        Collections.sort(edges, Comparator.comparingInt(e -> e.length));

        UnionFind uf = new UnionFind(n);
        int totalLength = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalLength += edge.length;
            }
        }

        return totalLength * p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            int p = scanner.nextInt();
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            ArrayList<ArrayList<Integer>> streets = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                ArrayList<Integer> street = new ArrayList<>();
                street.add(scanner.nextInt());
                street.add(scanner.nextInt());
                street.add(scanner.nextInt());
                streets.add(street);
            }

            System.out.println(cobbledStreets(n, streets, p));
        }

        scanner.close();
    }
}

// Edge Class: Represents a street with its endpoints and length.
// UnionFind Class: Manages the connected components using path compression and
// union by rank.
// cobbledStreets Method:
// Converts the input list of streets into a list of Edge objects.
// Sorts the edges by their length.
// Uses Union-Find to construct the MST, ensuring no cycles are formed.
// Computes the total cost by multiplying the sum of the lengths of the streets
// in the MST by the cost per furlong.
// Main Method: Handles multiple test cases, reads the input, and invokes the
// cobbledStreets method to compute and print the result for each test case.
// This approach ensures that we find the minimum cost to cobble the necessary
// streets to connect all major buildings.
