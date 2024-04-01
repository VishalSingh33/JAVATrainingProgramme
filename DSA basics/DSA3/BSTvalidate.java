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

public class BSTvalidate  {
    boolean validateBinarySearchTree(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Long min, Long max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        return isValidBST(node.left, min, (long) node.val) && 
                 isValidBST(node.right, (long) node.val, max);
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        BSTvalidate solution = new BSTvalidate();
        boolean isValidBST = solution.validateBinarySearchTree(root);

        if (isValidBST) {
            System.out.println("The binary tree is a valid BST.");
        } else {
            System.out.println("The binary tree is not a valid BST.");
        }
    }
}
