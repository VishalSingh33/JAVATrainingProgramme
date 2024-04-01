class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedInsertIntoSortedCircularList  {
    public ListNode insertIntoSortedCircularList(ListNode head, int insertVal) {
        if (head == null) {
            ListNode newNode = new ListNode(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        ListNode curr = head;
        do {
            ListNode next = curr.next;

            if ((curr.val <= insertVal && insertVal <= next.val) || 
               (curr.val > next.val && (insertVal >= curr.val || insertVal <= next.val))) {
                ListNode newNode = new ListNode(insertVal);
                newNode.next = next;
                curr.next = newNode;
                return head;
            }

            curr = next;
        } while (curr != head);

        ListNode newNode = new ListNode(insertVal);
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        // Sample usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = head;

        LinkedInsertIntoSortedCircularList solution = new LinkedInsertIntoSortedCircularList();
        ListNode updatedList = solution.insertIntoSortedCircularList(head, 2);
        printCircularList(updatedList);
    }

    public static void printCircularList(ListNode head) {
        if (head == null) return;

        ListNode current = head;
        do {
            System.out.print(current.val + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }
}
