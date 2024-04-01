class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListCycleDetection {
    public boolean linkedListCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // No cycle if there are less than two nodes
        }

        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by 1 step
            fast = fast.next.next; // Move fast pointer by 2 steps

            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false; // No cycle found
    }

    public static void main(String[] args) {
        // Sample usage
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // Create a cycle at node with value 2

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2; // Create a cycle at node with value 2

        LinkedListCycleDetection solution = new LinkedListCycleDetection();
        System.out.println("List 1 has cycle: " + solution.linkedListCycle(head1));
        System.out.println("List 2 has cycle: " + solution.linkedListCycle(head2));
    }
}
