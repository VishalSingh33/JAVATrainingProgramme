import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    long val;
    TreeNode left;
    TreeNode right;

    TreeNode(long val) {
        this.val = val;
    }
}

public class ZigzagLevelOrder {
    public List<List<Long>> zigzagTraversal(TreeNode root) {
        List<List<Long>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Long> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (leftToRight) {
                    currentLevel.addLast(node.val);
                } else {
                    currentLevel.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        // Example Usage (Creating a simple tree structure)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ZigzagLevelOrder zigzagLevelOrder = new ZigzagLevelOrder();
        List<List<Long>> zigzagResult = zigzagLevelOrder.zigzagTraversal(root);

        // Printing the zigzag traversal result
        for (List<Long> level : zigzagResult) {
            for (Long val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
