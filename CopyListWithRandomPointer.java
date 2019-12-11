package OA2;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    //recursion
    Map<Node,Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        if (this.map.containsKey(head)) {
            return this.map.get(head);
        }

        Node node = new Node(head.val,null,null);

        this.map.put(head,node);


        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);

        return node;
    }

    public Node copyRandomList_Interative(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val,null,null);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }


    //Amazon 原版

    class ALNode{
        public int value;
        public ALNode next;
        public ALNode random;

        public ALNode() {
            this.value = -1;
            this.next = null;
            this.random = null;
        }

        public ALNode(int val) {
            this.value = val;
            this.random = null;
            this.next = null;
        }
    }

    public ALNode deepCopy(ALNode head) {
        if (head == null) {
            return null;
        }

        ALNode iter = head;
        ALNode next;

        while (iter != null) {
            next = iter.next;
            ALNode copy = new ALNode(iter.value);
            iter.next = copy;
            copy.next = next;
            iter = next;
        }

        iter = head;

        while (iter != null && iter.next != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        iter = head;
        ALNode newHead = new ALNode();
        ALNode copy,copyIter = newHead;

        while (iter != null && iter.next != null) {
            next = iter.next.next;
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;
            iter.next = next;
            iter = next;
        }

        return newHead.next;
    }
}
