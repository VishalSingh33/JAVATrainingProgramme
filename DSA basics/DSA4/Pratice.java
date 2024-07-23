import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class Pratice {
    public ListNode reverseLinkedList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode copy = head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){

            slow = fast.next;
            fast = fast.next.next;

            if(fast.next == null){

                return slow;

            }

        }
        return slow;
        
        
    }

    // 1 2 3 4 5

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
