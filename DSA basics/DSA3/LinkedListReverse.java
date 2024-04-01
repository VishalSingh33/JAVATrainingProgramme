import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedListReverse {
    public ListNode reverseLinkedList(ListNode head) {
        // if (head == null || head.next == null) {
        //     return head; // If the list is empty or has only one node, return as it is
        // }
        // Stack<Integer> stack = new Stack<>();
        // ListNode current = head;

        // // Push all node values into the stack
        // while (current != null) {
        //     stack.push(current.val);
        //     current = current.next;
        // }
        // current = head; // Reset current to head for iteration

        // // Pop values from the stack and update the linked list node values
        // while (!stack.isEmpty()) {
        //     current.val = stack.pop();
        //     current = current.next;
        // }
        // return head; // Return the head of the reversed linked list
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    public static void main(String[] args) {
        // Example Usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListReverse reverseLinkedList = new LinkedListReverse();
        ListNode reversedHead = reverseLinkedList.reverseLinkedList(head);

        // Printing the reversed linked list
        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }
    }
}
