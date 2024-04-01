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

public class BSTlowestCommonAncestor {
    public TreeNode lowestCommonAncestorInBST(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = root;
        while (lca != null) {
            if (lca.val > p.val && lca.val > q.val) {
                lca = lca.left; // If both p and q are smaller, go left
            } else if (lca.val < p.val && lca.val < q.val) {
                lca = lca.right; // If both p and q are greater, go right
            } else {
                // Current node's value is between p and q
                // or one node is on the left and the other is on the right
                return lca; // This node is the LCA
            }
        }

        return null; // Return null if no common ancestor found
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left;
        TreeNode q = root.right;

        BSTlowestCommonAncestor solution = new BSTlowestCommonAncestor();
        TreeNode lca = solution.lowestCommonAncestorInBST(root, p, q);

        if (lca != null) {
            System.out.println("Lowest Common Ancestor: " + lca.val);
        } else {
            System.out.println("No Common Ancestor found!");
        }
    }
}
