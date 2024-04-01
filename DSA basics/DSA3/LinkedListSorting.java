class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedListSorting {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // Find the middle of the list
        ListNode middle = findMiddle(head);

        ListNode nextToMiddle = middle.next;
        middle.next = null;
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
        ListNode result = new ListNode(0);
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
        //// Attach the remaining nodes
        // current.next = (left != null) ? left : right;
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }
        return result.next; // Return the sorted merged list
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListSorting solution = new LinkedListSorting();

        // Example usage
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.println("Original List:");
        solution.printList(head);

        ListNode sortedList = solution.sortList(head);

        System.out.println("Sorted List:");
        solution.printList(sortedList);
    }
}
