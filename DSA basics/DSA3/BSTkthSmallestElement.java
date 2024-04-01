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

public class BSTkthSmallestElement  {
    private int count; // Declare count variable
    private int result; // Declare result variable

    public int kthSmallestElementInABst(TreeNode root, int k) {
        count = 0; // Initialize count
        result = Integer.MIN_VALUE; // Initialize result

        inorderTraversal(root, k);

        return result;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inorderTraversal(node.right, k);
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        BSTkthSmallestElement solution = new BSTkthSmallestElement();
        int k = 3; // Find the 3rd smallest element
        int kthSmallest = solution.kthSmallestElementInABst(root, k);

        System.out.println("The " + k + "th smallest element is: " + kthSmallest);
    }
}
