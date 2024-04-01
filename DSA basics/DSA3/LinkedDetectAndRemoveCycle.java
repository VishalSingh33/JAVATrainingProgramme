class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedDetectAndRemoveCycle  {
    public boolean detectAndRemoveCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // No cycle if there are less than two nodes
        }
        
        if (head.next != null && head.next.next == head) {
            head.next.next = null; // Remove cycle if it's a self-loop
            return true;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null && fast.next.next != head) {
            slow = slow.next;
            fast = fast.next.next;
           
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            return false; // No cycle found
        }

        // Find the start of the cycle and remove it
        int k = 1;
        while (fast.next != slow) {
            fast = fast.next;
            k++;
        }

        ListNode thread = head;
        while (k-- > 0) {
            thread = thread.next;
        }

        while (thread.next != head.next) {
            head = head.next;
            thread = thread.next;
        }

        thread.next = null; // Remove the cycle
        return true;
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

        LinkedDetectAndRemoveCycle solution = new LinkedDetectAndRemoveCycle();
        System.out.println("Cycle removed in List 1: " + solution.detectAndRemoveCycle(head1));
        System.out.println("Cycle removed in List 2: " + solution.detectAndRemoveCycle(head2));
    }
}
