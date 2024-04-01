import java.util.HashMap;

class Node {
    int val;
    Node next, random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}

public class LinkedDeepCopyRandomList  {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> nodeMap = new HashMap<>();
        Node curr = head;

        // Create copies of nodes without setting next and random pointers
        while (curr != null) {
            nodeMap.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        // Set next and random pointers for copied nodes using the map
        while (curr != null) {
            nodeMap.get(curr).next = nodeMap.get(curr.next);
            nodeMap.get(curr).random = nodeMap.get(curr.random);
            // Node copy = nodeMap.get(curr);
            // copy.next = nodeMap.get(curr.next);
            // copy.random = nodeMap.get(curr.random);
            curr = curr.next;
        }

        return nodeMap.get(head);
    }
    public static void main(String[] args) {
        // Sample usage
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Assigning random pointers for testing
        head.random = head.next.next;
        head.next.random = head.next.next.next;
        head.next.next.random = head;

        LinkedDeepCopyRandomList solution = new LinkedDeepCopyRandomList();
        Node deepCopy = solution.copyRandomList(head);

        System.out.println("Original List:");
        printList(head);
        System.out.println("Deep Copy List:");
        printList(deepCopy);
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print("val: " + current.val);
            if (current.random != null) {
                System.out.print(", random: " + current.random.val);
            } else {
                System.out.print(", random: null");
            }
            System.out.println();
            current = current.next;
        }
    }
}
