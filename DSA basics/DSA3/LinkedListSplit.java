import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedListSplit {
    public List<ListNode> splitLinkedList(ListNode head) {
        ListNode oddHead = null, evenHead = null;
        ListNode oddTail = null, evenTail = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val % 2 == 0) {
                if (evenHead == null) {
                    evenHead = curr;
                    evenTail = curr;
                } else {
                    evenTail.next = curr;
                    evenTail = evenTail.next;
                }
            } else {
                if (oddHead == null) {
                    oddHead = curr;
                    oddTail = curr;
                } else {
                    oddTail.next = curr;
                    oddTail = oddTail.next;
                }
            }
            curr = next;
        }

        if (evenTail != null) {
            evenTail.next = null;
        }
        if (oddTail != null) {
            oddTail.next = null;
        }

        List<ListNode> result = new ArrayList<>();
        result.add(oddHead);
        result.add(evenHead);

        return result;
    }

    public static void main(String[] args) {
        // Example Usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListSplit linkedListSplit = new LinkedListSplit();
        List<ListNode> splitHeads = linkedListSplit.splitLinkedList(head);

        // Printing the odd and even lists
        for (ListNode splitHead : splitHeads) {
            while (splitHead != null) {
                System.out.print(splitHead.val + " ");
                splitHead = splitHead.next;
            }
            System.out.println();
        }
    }
}
