import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedRemoveDuplicates  {
    public static void removeDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            if (!set.contains(curr.val)) {
                set.add(curr.val);
                prev = curr;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        // Sample usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);

        System.out.println("Original List:");
        printList(head);

        removeDuplicates(head);

        System.out.println("List after removing duplicates:");
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
