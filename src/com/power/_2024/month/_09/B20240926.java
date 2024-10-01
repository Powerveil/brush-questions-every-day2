package com.power._2024.month._09;

import com.power._2024.month.domain.ListNode;

public class B20240926 {


    /**
     * BM1 反转链表
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode ReverseList(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    /**
     * BM2 链表内指定区间反转
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        // write code here

        ListNode pre = null;

        ListNode firstTail = null;

        ListNode cur = head;
        ListNode secondTail = cur;
        if (m > 1) {
            for (int i = 0; i < m - 2; i++) {
                cur = cur.next;
            }

            firstTail = cur;
            cur = cur.next;
            secondTail = cur;
        }

        int len = n - m + 1;
        for (int i = 0; i < len; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        ListNode secondHead = pre;

        ListNode thirdHead = cur;
        if (m > 1) {
            firstTail.next = secondHead;
            secondTail.next = thirdHead;
        } else {
            if (cur != null) {
                secondTail.next = thirdHead;
            }
            return pre;
        }

        return head;
    }
}
