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

public class BTconstruct {
    public TreeNode constructBinaryTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, 
                      int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        int inIndex = 0; // Find index of current root in inorder traversal
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }
        root.left = buildTree(preorder, inorder, preStart + 1, inStart, inIndex - 1);
        root.right = buildTree(preorder, inorder, preStart + 
                        inIndex - inStart + 1, inIndex + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        // Sample usage
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        BTconstruct solution = new BTconstruct();
        TreeNode root = solution.constructBinaryTree(preorder, inorder);

        System.out.println("Preorder traversal:");
        printPreorder(root);
        System.out.println("\nInorder traversal:");
        printInorder(root);
    }

    // Helper method to print preorder traversal
    private static void printPreorder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
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
