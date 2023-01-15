/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/reorder-list/description/

public class ReorderList {
    
    // Time O(2n) space: o(n)
    // put linked list in stack, 
    // run upto stack size/2 times
    // stack pop should give list in reverse order
    // get n(last node) and next(sec node)
    // cur.next to n, n.next to next, cur=next
    // finally break the chain of cur cur.next to ull after breaking while
    public void reorderList(ListNode head) {
        Stack<ListNode> s = new Stack();
        ListNode cur = head;
        while(cur!=null){
            s.add(cur);
            cur = cur.next;
        }
        int c = s.size()/2;
        cur = head;
        while(c-->0){
            ListNode n = s.pop();
            ListNode next = cur.next;
            cur.next = n;
            n.next= next;
            cur = next;
        }
        cur.next = null;
    }
    
    //  Time O(n) space: o(n) call stack
    // using recursion bottom up to get last node
    // using ListNode[] to safely take head inside
    // remapping
    // exit condition lef[0].next==right make left[0].next as null, for odd the nodes match, for even it will point to self
    // thats y we have a if(left[0].next!=null) for exit
    public void reorderList(ListNode head) {
        reorderList(head, new ListNode[]{head});
    }
    public void reorderList(ListNode right, ListNode left[]) {
        if(right==null)return;
        reorderList(right.next, left);
        if(left[0].next!=null){
            ListNode next = left[0].next;
            left[0].next = right;
            right.next = next;
            left[0]=next;
        }
        if(left[0].next==right) left[0].next = null;
    }
    
    
    //  Time O(n) space: o(1) Iterative
    //first find mid using slw and fast pointers
    // the reverse from mid to n, get midnext, make mid.next to null
    // then merge two lists head, midnext
    public void reorderList(ListNode head) {
        ListNode cur=head, slow=head, fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow, midnext = slow.next;
        mid.next = null;
        midnext = reverse(midnext);
        while(cur!=null && midnext!=null){
            ListNode cnext = cur.next;
            ListNode mnext = midnext.next;
            cur.next = midnext;
            midnext.next = cnext;
            cur = cnext;
            midnext = mnext;
        }

    }

    public ListNode reverse(ListNode cur){
        if(cur==null||cur.next==null) return cur;
        ListNode x = reverse(cur.next);
        cur.next.next=cur;
        cur.next=null;
        return x;
    }
    // 1 2 3 n 
    // 5 4 n
    
    //same as above alternate merge strategy
    public void reorderList(ListNode head) {
        ListNode cur=head,slow=head,fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast= fast.next.next;
        }
        ListNode midnext=slow.next;
        slow.next = null;
        midnext = reverse(midnext);
        while(cur!=null && midnext!=null){
            ListNode cnext = cur.next;
            cur.next = midnext;
            cur= midnext;
            midnext = cnext;
        }
    }
    public ListNode reverse(ListNode cur){
        if(cur==null || cur.next==null) return cur;
        ListNode h = reverse(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return h;
    }
}
