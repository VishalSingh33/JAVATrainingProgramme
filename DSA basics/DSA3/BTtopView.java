import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

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

public class BTtopView {
    public ArrayList<Long> topViewBinaryTree(TreeNode root) {
        ArrayList<Long> topView = new ArrayList<>();
        if (root == null) {
            return topView;
        }

        Map<Integer, Long> topMap = new TreeMap<>();
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 0));

        while (!queue.isEmpty()) {
            QueueNode current = queue.poll();

            if (!topMap.containsKey(current.horizontalDistance)) {
                topMap.put(current.horizontalDistance, (long) current.node.val);
            }

            if (current.node.left != null) {
                queue.offer(new QueueNode(current.node.left, current.horizontalDistance - 1));
            }

            if (current.node.right != null) {
                queue.offer(new QueueNode(current.node.right, current.horizontalDistance + 1));
            }
        }

        // Extracting values from the TreeMap to the result ArrayList
        for (long value : topMap.values()) {
            topView.add(value);
        }

        return topView;
    }

    class QueueNode {
        TreeNode node;
        int horizontalDistance;

        QueueNode(TreeNode node, int hd) {
            this.node = node;
            horizontalDistance = hd;
        }
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

        BTtopView solution = new BTtopView();
        ArrayList<Long> topView = solution.topViewBinaryTree(root);
        System.out.println("Top view of the binary tree: " + topView);
    }
}
