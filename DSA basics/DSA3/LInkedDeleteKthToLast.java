class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LInkedDeleteKthToLast  {
    public static ListNode deleteKthToLast(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        int size = 0;

        // Calculate the size of the list
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        int deleteIndex = size - k;
        if (deleteIndex == 0) {
            // If k is equal to the size of the list, delete the first node
            return head.next;
        }

        curr = head;
        for (int i = 0; i < deleteIndex; i++) {
            prev = curr;
            curr = curr.next;
        }

        if (prev != null) {
            // Delete the k-th node
            prev.next = curr.next;
        } else {
            // If prev is null, delete the head node
            head = head.next;
        }

        return head;
    }

    public static void main(String[] args) {
        // Sample usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        printList(head);

        int k = 2; // Delete 2nd to last node (k = 2)
        head = deleteKthToLast(head, k);

        System.out.println("List after deleting " + k + "th to last node:");
        printList(head);
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
