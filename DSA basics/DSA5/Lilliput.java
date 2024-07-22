import java.util.*;

// Problem Description
// Welcome to Lilliput, the land of tiny people. Here, the person with smaller 
// height is considered more intelligent.

// You are given an array of N integers indicating all the Lilliputians standing 
// in a straight line in positions 0 to n-1. The values of the array indicate the 
// Lilliputian heights.

// You will be asked to do two types of actions - update of the form [u x h] 
// and query of the form [q x y]. In the update action [u x h], you have to update 
// the height of the Lilliputian at index x to value h. In the query action [q x y], 
// you have to output the most intelligent Lilliputian’s height in the index range 
// between x to y (both inclusive).

// Input format
// First line will have N, representing the number of Lilliputians in Lilliput.

// Second line will have N space separated integers denoting the height of each 
// Lilliputian standing in the line.

// Third line will have a number A denoting the number of actions.

// Next A lines will have one of the two actions, u or q.

// For the action of type u (update), the line will have u followed by two 
// space separated integers, x and h, where x indicates the index in the array 
// and h indicates the new height of the Lilliputian at that index.

// For the action of type q (query), the line will have q followed by two space 
// separated integers, x and y, where x indicates the start index in the array and y 
// indicates the end index in the array, between which (both inclusive) the most 
// intelligent Lilliputian’s height has to be found.

public class Lilliput {
    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int[] heights) {
            this.n = heights.length;
            tree = new int[4 * n];
            buildTree(heights, 0, n - 1, 0);
        }

        private void buildTree(int[] heights, int start, int end, int treeNode) {
            if (start == end) {
                tree[treeNode] = heights[start];
                return;
            }

            int mid = (start + end) / 2;
            buildTree(heights, start, mid, 2 * treeNode + 1);
            buildTree(heights, mid + 1, end, 2 * treeNode + 2);

            tree[treeNode] = Math.min(tree[2 * treeNode + 1], tree[2 * treeNode + 2]);
        }

        public void update(int index, int value) {
            updateUtil(0, n - 1, index, value, 0);
        }

        private void updateUtil(int start, int end, int idx, int value, int treeNode) {
            if (start == end) {
                tree[treeNode] = value;
                return;
            }

            int mid = (start + end) / 2;
            if (idx <= mid) {
                updateUtil(start, mid, idx, value, 2 * treeNode + 1);
            } else {
                updateUtil(mid + 1, end, idx, value, 2 * treeNode + 2);
            }

            tree[treeNode] = Math.min(tree[2 * treeNode + 1], tree[2 * treeNode + 2]);
        }

        public int query(int l, int r) {
            return queryUtil(0, n - 1, l, r, 0);
        }

        private int queryUtil(int start, int end, int l, int r, int treeNode) {
            if (start > r || end < l) {
                return Integer.MAX_VALUE;
            }

            if (start >= l && end <= r) {
                return tree[treeNode];
            }

            int mid = (start + end) / 2;
            int leftQuery = queryUtil(start, mid, l, r, 2 * treeNode + 1);
            int rightQuery = queryUtil(mid + 1, end, l, r, 2 * treeNode + 2);

            return Math.min(leftQuery, rightQuery);
        }
    }

    public static Vector<Integer> lilliput(int n, int heights[], int num_actions, String actions[]) {
        SegmentTree segmentTree = new SegmentTree(heights);
        Vector<Integer> results = new Vector<>();

        for (String action : actions) {
            String[] parts = action.split(" ");
            String type = parts[0];
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);

            if (type.equals("u")) {
                segmentTree.update(x, y);
            } else if (type.equals("q")) {
                results.add(segmentTree.query(x, y));
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }

        int num_actions = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        String[] actions = new String[num_actions];
        for (int i = 0; i < num_actions; i++) {
            actions[i] = scanner.nextLine();
        }

        Vector<Integer> result = lilliput(n, heights, num_actions, actions);
        for (int res : result) {
            System.out.println(res);
        }

        scanner.close();
    }
}

// Explanation:
// Segment Tree Construction:

// The SegmentTree class is built to handle both updates and queries. The tree
// is constructed using an array representation.
// The buildTree method initializes the segment tree with the minimum heights in
// each segment.
// Update Operation:

// The update method updates the height of a specific Lilliputian and adjusts
// the segment tree accordingly.
// Query Operation:

// The query method returns the minimum height (most intelligent Lilliputian) in
// the specified range.
// Main Logic:

// The lilliput method processes each action (update or query) and collects the
// results of the queries in a Vector.
// I/O Handling:

// The main function reads input, invokes the lilliput method, and prints the
// results.
// This approach ensures efficient handling of both update and query operations
// using the Segment Tree data structure.