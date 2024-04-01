import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BTboundaryTraversal {
    public ArrayList<Long> binaryTreeBoundaryTraversal(TreeNode root) {
        ArrayList<Long> result = new ArrayList<>();
        if (root != null) {
            result.add((long) root.val); // Add root node

            // Traverse left boundary except for leaf node
            traverseLeftBoundary(root.left, result);

            // Traverse leaf nodes
            traverseLeaves(root.left, result);
            traverseLeaves(root.right, result);

            // Traverse right boundary except for leaf and root nodes
            traverseRightBoundary(root.right, result);
        }
        return result;
    }

    // Helper method to traverse left boundary
    private void traverseLeftBoundary(TreeNode node, ArrayList<Long> result) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        result.add((long) node.val);
        if (node.left != null) {
            traverseLeftBoundary(node.left, result);
        } else {
            traverseLeftBoundary(node.right, result);
        }
    }

    // Helper method to traverse leaf nodes
    private void traverseLeaves(TreeNode node, ArrayList<Long> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add((long) node.val);
            return;
        }
        traverseLeaves(node.left, result);
        traverseLeaves(node.right, result);
    }

    // Helper method to traverse right boundary
    private void traverseRightBoundary(TreeNode node, ArrayList<Long> result) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        if (node.right != null) {
            traverseRightBoundary(node.right, result);
        } else {
            traverseRightBoundary(node.left, result);
        }
        result.add((long) node.val);
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        BTboundaryTraversal solution = new BTboundaryTraversal();
        ArrayList<Long> boundaryTraversal = solution.binaryTreeBoundaryTraversal(root);
        System.out.println("Boundary traversal of the binary tree: " + boundaryTraversal);
    }
}
