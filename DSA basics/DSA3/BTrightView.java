import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

public class BTrightView {
    public ArrayList<Long> rightViewBinaryTree(TreeNode root) {
        ArrayList<Long> rightView = new ArrayList<>();
        if (root == null) {
            return rightView;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Long rightMost = null;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // The rightmost node at each level will be added to the result
                if (i == size - 1) {
                    rightMost = (long) current.val;
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            if (rightMost != null) {
                rightView.add(rightMost);
            }
        }

        return rightView;
    }

    public static void main(String[] args) {
        // Sample usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        BTrightView solution = new BTrightView();
        ArrayList<Long> rightView = solution.rightViewBinaryTree(root);
        System.out.println("Right view of the binary tree: " + rightView);
    }
}
