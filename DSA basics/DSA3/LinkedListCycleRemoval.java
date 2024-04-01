class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListCycleRemoval {

    public boolean detectAndRemoveCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // No cycle possible in a list with 0 or 1 node
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return false; // No cycle found, return false
        }
        // If cycle detected, find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // Move fast to the node just before the cycle
        while (fast.next != slow) {
            fast = fast.next;
        }
        // Remove the cycle by breaking the link
        fast.next = null;
        return true;
    }

    public static void main(String[] args) {
        // Example usage
        LinkedListCycleRemoval cycleRemoval = new LinkedListCycleRemoval();

        // Creating a linked list with a cycle
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next; // Creating a cycle

        // Detect and remove the cycle
        boolean cycleRemoved = cycleRemoval.detectAndRemoveCycle(head);
        if (cycleRemoved) {
            System.out.println("Cycle removed from the linked list.");
        } else {
            System.out.println("No cycle found in the linked list.");
        }
    }
}
