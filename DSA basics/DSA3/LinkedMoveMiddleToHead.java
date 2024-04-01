class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
public class LinkedMoveMiddleToHead {
    public ListNode moveMiddleToHead(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // Disconnect the middle node from the list
        prev.next = slow.next;
        // Move the disconnected middle node to the head of the list
        slow.next = head;
        head = slow;

        return head;
}

 public static void main(String[] args) {
        // Example Usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        LinkedMoveMiddleToHead moveMiddleToHead = new LinkedMoveMiddleToHead();
        ListNode middleHead = moveMiddleToHead.moveMiddleToHead(head);

        // Printing the reversed linked list
        while (middleHead != null) {
            System.out.print(middleHead.val + " ");
            middleHead = middleHead.next;
        }
    }
}
