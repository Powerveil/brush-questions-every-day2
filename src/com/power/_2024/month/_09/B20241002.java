package com.power._2024.month._09;

import com.power._2024.month.domain.ListNode;

import java.util.*;

public class B20241002 {

    /**
     * BM4 合并两个排序的链表
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337
     *
     * @param pHead1 ListNode类
     * @param pHead2 ListNode类
     * @return ListNode类
     */
    public ListNode Merge (ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null) return pHead2;
        if (pHead2 == null) return pHead1;

        // write code here
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;

        while (pHead1 != null && pHead2 != null) {
            if (pHead1.val < pHead2.val) {
                cur.next = pHead1;
                pHead1 = pHead1.next;
            } else {
                cur.next = pHead2;
                pHead2 = pHead2.next;
            }
            cur = cur.next;
        }

        cur.next = pHead1 != null ? pHead1 : pHead2;

        return newHead.next;
    }

    /**
     * BM5 合并k个已排序的链表
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6
     *
     * @param lists ListNode类ArrayList
     * @return ListNode类
     */
    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        // write code here
        return div(lists, 0, lists.size() - 1);
    }

    public ListNode div (ArrayList<ListNode> lists, int start, int end) {
        // write code here
        if (start > end) {
            return null;
        } else if (start == end) {
            return lists.get(start);
        } else {
            int mid = (start + end) / 2;
            return Merge(div(lists, start, mid), div(lists, mid + 1, end));
        }
    }


    /**
     * BM6 判断链表中是否有环
     * https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        if (head.next == head) return true;

        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            // 在圈里面会相交，进入圈的位置不一样，产生一个偏移量b(慢指针进入后)，因为快指针速度快，设置为快追慢，慢指针领先偏移量b
            // 慢指针：y = x + b
            // 快指针：y = 2*x
            // 因为在圈里面，所以距离可以无限延长，不受长度的限制，y无上限，即x无上限
            //
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }
}
