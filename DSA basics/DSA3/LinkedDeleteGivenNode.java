class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedDeleteGivenNode  {
    public static void deleteGivenNode(ListNode node) {
        if (node == null || node.next == null) {
            return; // Cannot delete the last node or null node
        }
        
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
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

        // Deleting node with value 3
        ListNode nodeToDelete = head.next.next;
        deleteGivenNode(nodeToDelete);

        System.out.println("List after deleting node with value 3:");
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
