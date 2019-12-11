package OA2;

import java.util.List;

public class mergeTwoSortedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode ptr = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ptr.next = l1;
                l1 = l1.next;
            }
            if (l1.val > l2.val) {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }

        ptr.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
