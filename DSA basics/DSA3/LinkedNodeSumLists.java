class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedNodeSumLists {

    public static ListNode sumLists1(ListNode head1, ListNode head2) {

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        head1 = reverseList(head1);
        head2 = reverseList(head2);

        ListNode current1 = head1;
        ListNode current2 = head2;
        ListNode result = null;
        ListNode prev = null;
        int carry = 0;

        while (current1 != null || current2 != null || carry != 0) {
            int sum = carry;

            if (current1 != null) {
                sum += current1.val;
                current1 = current1.next;
            }
            if (current2 != null) {
                sum += current2.val;
                current2 = current2.next;
            }

            carry = sum / 10;
            sum %= 10;

            if (result == null) {
                result = new ListNode(sum);
                prev = result;
            } else {
                prev.next = new ListNode(sum);
                prev = prev.next;
            }
        }

        return reverseList(result);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(9);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        System.out.println("List 1:");
        printList(head1);

        System.out.println("List 2:");
        printList(head2);

        ListNode result = sumLists1(head1, head2);

        System.out.println("Sum of lists in reverse order:");
        printList(result);
    }
}
