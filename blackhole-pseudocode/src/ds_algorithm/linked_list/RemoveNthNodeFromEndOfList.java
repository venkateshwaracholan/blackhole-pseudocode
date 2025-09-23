/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

public class RemoveNthNodeFromEndOfList {
    
    
    //APPROACH 1 brute ite twice,get count and move c-n times and remap
    // Time O(2n) space O(1)
    // first find count
    // find x=c-n;
    // f thats 0, first elem, then return head.next;
    // or move cur to next x times
    // cur.next = cur.next.next;
    // return head
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        int c=0;
        for(;cur!=null;c++)
            cur=cur.next;
        int x = c-n;
        if(x==0) return head.next;
        cur= head;
        while(x-->1)
            cur = cur.next;
        cur.next = cur.next.next;
        return head;
    }
    
    //APPROACH 2 rec+n[],bottom up, dec n[0]-- if n==0 return parent or return cur
    // Time O(n) space: O(n)call stack
    // recursive approach, my own unique approach i came up with instantly
    // use recursion to get to right
    // use int[] to create heap memory for permanacy
    // bring node x fom recursion
    // subrtact n when recursion gets out
    // map node.next to x;
    // if n==0 then return x
    // else return node;
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        return removeNthFromEnd2(head, new int[]{n});
    }
    public ListNode removeNthFromEnd2(ListNode cur, int n[]) {
        if(cur==null) return cur;
        ListNode x = removeNthFromEnd2(cur.next, n);
        cur.next = x;
        if(--n[0]==0) return x;
        else return cur;
    }
    
    
    //APPROACH 3 ite+slowfast,move fast n times then move both at equal paces, edge case if fast==null,return head.next
    // Time O(n) space O(1)
    // using two pointers
    // first move fats pointer n times
    // if fast is null then return head.next;
    // then until fats !=null && fast.next !=null
    // move slow and fast
    // slow.next = slow.next.next;
    //return head
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode slow = head, fast= head;
        for(int i=0;i<n;i++) 
            fast = fast.next; 
        if(fast==null) return head.next; 
        while(fast!=null&& fast.next!=null){
            fast = fast.next;
            slow=slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    //APPROACH 3.2 ite+slowfast,move fast n times then move both at equal paces, with dummy node we can avoid edge case// if fast==null,return head.next
    // we have a dummy node, saem as above we dont have to care abt edge cases
    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode slow = dummy, fast= dummy;
        for(int i=0;i<n;i++) 
            fast = fast.next; 
        while(fast!=null&& fast.next!=null){
            fast = fast.next;
            slow=slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
    
    
    
}
