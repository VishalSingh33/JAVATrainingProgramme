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

public class BSTinorderSuccessor  {
    public int inorderSuccessor(TreeNode root, TreeNode givenNode) {
        if (givenNode.right != null) {
            // If givenNode has a right subtree, find the leftmost node in that subtree
            TreeNode successor = givenNode.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor.val;
        } else {
            // If givenNode doesn't have a right subtree, trace back to find the successor
            int successorVal = Integer.MAX_VALUE;
            while (root != null) {
                if (givenNode.val < root.val) {
                    successorVal = root.val;
                    root = root.left;
                } else if (givenNode.val > root.val) {
                    root = root.right;
                } else {
                    break; // Given node found, exit loop
                }
            }
            return successorVal == Integer.MAX_VALUE ? -1 : successorVal;
        }
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

        BSTinorderSuccessor  solution = new BSTinorderSuccessor ();
        TreeNode givenNode = root.left.right; // Node with value 4
        int successorVal = solution.inorderSuccessor(root, givenNode);

        if (successorVal != -1) {
            System.out.println("Inorder Successor of " + givenNode.val + " is " + successorVal);
        } else {
            System.out.println("No Inorder Successor found for " + givenNode.val);
        }
    }
}
