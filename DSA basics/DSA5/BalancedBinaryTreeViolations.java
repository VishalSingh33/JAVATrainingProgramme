import java.util.*;

// Problem Description: You are given a binary tree. You need to count the number of 
// balanced binary  tree violations in the given tree.

// Input format
// Line 1: Number of nodes in the Binary Tree (N)
// Line 2: N space separated node values. The position of the Nodes on this 
// line will be used to refer to them in the below lines, starting from 1.
// Line 3 to N+2: Lines specifying the child nodes for each of the N nodes

// Format of each line (space separated): Parent_node Left_child_node Right_child_node
// Parent_node - Node at this Position on Line 2 is the Node to which we are 
// assigning the Left and Right child here

// Left_child_node - Node at this position on Line 2 is the left child. 
// Specify -1 if there is no Left child.

// Right_child_node - Node at this position on Line 2 is the right child. 
// Specify -1 if there is no Right child.

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class BalancedBinaryTreeViolations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = scanner.nextInt();
        }

        TreeNode[] nodes = new TreeNode[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new TreeNode(values[i]);
        }

        for (int i = 0; i < N; i++) {
            int parent = scanner.nextInt();
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            if (left != -1) {
                nodes[parent - 1].left = nodes[left - 1];
            }
            if (right != -1) {
                nodes[parent - 1].right = nodes[right - 1];
            }
        }
        scanner.close();

        BalancedBinaryTreeViolations sol = new BalancedBinaryTreeViolations();
        int violations = sol.findNumberOfBtVoilations(nodes[0]);
        System.out.println(violations);
    }

    public int findNumberOfBtVoilations(TreeNode root) {
        return countViolations(root).violations;
    }

    private class TreeInfo {
        int height;
        int violations;

        TreeInfo(int height, int violations) {
            this.height = height;
            this.violations = violations;
        }
    }

    private TreeInfo countViolations(TreeNode node) {
        if (node == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftInfo = countViolations(node.left);
        TreeInfo rightInfo = countViolations(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int violations = leftInfo.violations + rightInfo.violations;

        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            violations++;
        }

        return new TreeInfo(height, violations);
    }
}

// TreeNode Class: A simple definition to hold the value and references to left
// and right children.
// Input Parsing:
// Read the number of nodes and their values.
// Create an array of TreeNode objects.
// Read the parent-child relationships to build the tree structure.
// Counting Violations:
// The countViolations method uses a helper class TreeInfo to store the height
// and number of violations.
// This method performs a post-order traversal to calculate the height of each
// subtree and count the number of balance violations.
// If the height difference between the left and right subtrees of any node is
// greater than 1, it's counted as a violation.
// This approach ensures that we traverse the tree only once, making it
// efficient with a time complexity of O(N), where N is the number of nodes in
// the tree.
