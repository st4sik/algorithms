package com.algo.main;

class InsertSortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head != null) {
            ListNode sorted = head;

            head = head.next;
            sorted.next = null;

            while (head != null) {
                ListNode current = head;
                head = head.next;
                if (current.val < sorted.val) {
                    current.next = sorted;
                    sorted = current;
                } else {
                    ListNode listNode = sorted;
                    while (listNode.next != null && current.val > listNode.next.val) {
                        listNode = listNode.next;
                    }
                    current.next = listNode.next;
                    listNode.next = current;
                }
            }
            return sorted;
        }
        return null;
    }

    public static void main(String[] argc) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(-1);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        ListNode listNode = insertionSortList(a);

        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }

    }
}