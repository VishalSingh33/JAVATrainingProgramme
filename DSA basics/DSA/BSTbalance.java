import java.util.*;

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

public class BSTbalance {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedList = new ArrayList<>();
        inorderTraversal(root, sortedList); // Perform inorder traversal to get sorted list
        return buildBalancedBST(sortedList, 0, sortedList.size() - 1);
    }

    private void inorderTraversal(TreeNode node, List<Integer> sortedList) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, sortedList);
        sortedList.add(node.val);
        inorderTraversal(node.right, sortedList);
    }

    private TreeNode buildBalancedBST(List<Integer> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode newNode = new TreeNode(sortedList.get(mid));
        newNode.left = buildBalancedBST(sortedList, start, mid - 1);
        newNode.right = buildBalancedBST(sortedList, mid + 1, end);
        return newNode;
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        BSTbalance solution = new BSTbalance();
        TreeNode balancedBST = solution.balanceBST(root);

        System.out.println("Balanced BST:");
        printInorder(balancedBST);
    }

    // Helper method to print inorder traversal
    private static void printInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}