class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedSortList  {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Return if the list has zero or one node
        }

        // Find the middle of the list
        ListNode middle = findMiddle(head);

        ListNode nextToMiddle = middle.next;
        middle.next = null; // Split the list into two halves

        // Recursively sort the two halves
        ListNode left = sortList(head);
        ListNode right = sortList(nextToMiddle);

        // Merge the sorted halves
        return merge(left, right);
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode result = new ListNode(0); // Dummy node for the merged list
        ListNode current = result;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // Append any remaining nodes from left or right list
        if (left != null) {
            current.next = left;
        }
        if (right != null) {
            current.next = right;
        }

        return result.next; // Return the sorted merged list
    }

    public static void main(String[] args) {
        // Sample usage
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        LinkedSortList solution = new LinkedSortList();
        ListNode sortedList = solution.sortList(head);

        // Print the sorted list for verification
        printList(sortedList);
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
