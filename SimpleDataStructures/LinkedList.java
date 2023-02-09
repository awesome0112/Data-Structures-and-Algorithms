package SimpleDataStructures;

public class LinkedList {
    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    // Print the Elements of a Linked List
    static void printLinkedList(SinglyLinkedListNode head) {
        while(head != null) {
            System.out.println(head.data);
            head = head.next;
        }

    }

    // Insert a Node at the Tail of a Linked List
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        if(head == null) return new SinglyLinkedListNode(data);
        SinglyLinkedListNode p = head;
        while(p.next != null) {
            p = p.next;
        }
        p.next = new SinglyLinkedListNode(data);
        return head;
    }

    // Insert a node at the head of a linked list
    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {
        if(llist == null) return new SinglyLinkedListNode(data);
        SinglyLinkedListNode x = new SinglyLinkedListNode(data);
        x.next = llist;
        llist = x;
        return llist;
    }

    // Insert a node at a specific position in a linked list
    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        // Write your code here
        if(llist == null) return new SinglyLinkedListNode(data);
        SinglyLinkedListNode i = llist;
        int count = 0;
        while(count < position - 1 && i != null) {
            i = i.next;
            count++;
        }
        SinglyLinkedListNode x = new SinglyLinkedListNode(data);
        x.next = i.next;
        i.next = x;
        return llist;
    }

    // Delete a Node
    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
        // Write your code here
        if(position == 0) {
            llist = llist.next;
            return llist;
        }
        int count = 0;
        SinglyLinkedListNode first = llist.next;
        SinglyLinkedListNode second = llist;
        while(count < position - 1 && first != null) {
            first = first.next;
            second = second.next;
            count++;
        }
        second.next = first.next;
        first.next = null;
        return llist;

    }

    // Print in Reverse
    public static void reversePrint(SinglyLinkedListNode llist) {
        // Write your code here
        if(llist == null) return;
        reversePrint(llist.next);
        System.out.println(llist.data);
    }

    // Reverse a linked list
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode p = new SinglyLinkedListNode(llist.data);
        llist = llist.next;
        while(llist != null) {
            p = insertNodeAtHead(p, llist.data);
            llist = llist.next;
        }
        return p;
    }

    // Compare two linked lists
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        while(head1 != null && head2 != null) {
            if(head1.data != head2.data) return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        if((head1 == null && head2 != null) || (head1 != null && head2 == null)) return false;
        return true;
    }

    // Merge two sorted linked lists
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode res = new SinglyLinkedListNode(Math.min(head1.data, head2.data));
        if(head1.data < head2.data) head1 = head1.next;
        else head2 = head2.next;
        while(head1 != null && head2 != null) {
            if(head1.data < head2.data) {
                res = insertNodeAtTail(res, head1.data);
                head1 = head1.next;
            } else {
                res = insertNodeAtTail(res, head2.data);
                head2 = head2.next;
            }
        }
        while(head1 != null) {
            res = insertNodeAtTail(res, head1.data);
            head1 = head1.next;
        }
        while(head2 != null) {
            res = insertNodeAtTail(res, head2.data);
            head2 = head2.next;
        }
        return res;
    }

    // Get Node Value
    static int sizeOf(SinglyLinkedListNode llist) {
        int count = 0;
        while(llist != null) {
            count++;
            llist = llist.next;
        }
        return count;
    }
    public static int getNode(SinglyLinkedListNode llist, int positionFromTail) {
        // Write your code here
        int size = sizeOf(llist);
        int pos = size - positionFromTail - 1;
        int count = 0;
        while(llist != null && count <= pos) {
            if(count == pos) {
                return llist.data;
            } else {
                count++;
                llist = llist.next;
            }
        }
        return 0;
    }
}
