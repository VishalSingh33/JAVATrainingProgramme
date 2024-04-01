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

public class BTmirror {
    public TreeNode mirrorBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Swap left and right subtrees
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively mirror left and right subtrees
        root.left = mirrorBinaryTree(root.left);
        root.right = mirrorBinaryTree(root.right);

        return root;
    }

    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Original tree:");
        inorderTraversal(root);
        System.out.println();

        BTmirror solution = new BTmirror();
        TreeNode mirroredRoot = solution.mirrorBinaryTree(root);

        System.out.println("Mirror image of the tree:");
        inorderTraversal(mirroredRoot);
        System.out.println();
    }
}
