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

public class BTcommonAncestor {
    public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = commonAncestor(root.left, p, q);
        TreeNode right = commonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root; // Current node is the lowest common ancestor
        }

        return left != null ? left : right;
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

        BTcommonAncestor solution = new BTcommonAncestor();
        TreeNode p = root.left.left; // Node 4
        TreeNode q = root.left.right; // Node 5

        TreeNode ancestor = solution.commonAncestor(root, p, q);
        System.out.println("Lowest common ancestor: " + ancestor.val);
    }
}
