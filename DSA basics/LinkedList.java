     class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public class LinkedList {
        Node head;

        public void insertAtBeginning(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        public void insertAtEnd(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public void deleteAtBeginning() {
            if (head != null) {
                head = head.next;
            } else {
                System.out.println("List is empty");
            }
        }

        public void deleteAtEnd() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            if (head.next == null) {
                head = null;
                return;
            }
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }

        public void insertAtPosition(int data, int position) {
            if (position < 0) {
                System.out.println("Invalid position");
                return;
            }
            if (position == 0) {
                insertAtBeginning(data);
            } else {
                Node newNode = new Node(data);
                Node current = head;
                int count = 0;
                while (current != null && count < position - 1) {
                    current = current.next;
                    count++;
                }
                if (current == null) {
                    System.out.println("Position out of range");
                } else {
                    newNode.next = current.next;
                    current.next = newNode;
                }
            }
        }

        public void deleteAtPosition(int position) {
            if (position < 0) {
                System.out.println("Invalid position");
                return;
            }
            if (position == 0) {
                deleteAtBeginning();
            } else {
                Node current = head;
                int count = 0;
                while (current != null && count < position - 1) {
                    current = current.next;
                    count++;
                }
                if (current == null || current.next == null) {
                    System.out.println("Position out of range");
                } else {
                    current.next = current.next.next;
                }
            }
        }

        public void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }

        public int size() {
            int count = 0;
            Node current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }

        public static void main(String[] args) {
            LinkedList linkedList = new LinkedList();
            linkedList.insertAtBeginning(1);
            linkedList.insertAtEnd(3);
            linkedList.insertAtPosition(2, 1);
            linkedList.printList(); // Output: 1 -> 2 -> 3 -> null
            linkedList.deleteAtBeginning();
            linkedList.deleteAtEnd();
            linkedList.printList(); // Output: 2 -> null
            System.out.println("Size: " + linkedList.size()); // Output: Size: 1
        }
    }
