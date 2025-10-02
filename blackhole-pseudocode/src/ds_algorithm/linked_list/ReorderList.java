/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;
import java.util.Stack;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/reorder-list/description/

public class ReorderList {
    
    /*
    * ONE LINERS — Quick Reference:
    *
    * Reorder List (Stack): push all nodes into stack, set c=stack.size()/2, cur=head; while(c>0) save cur.next as next and stack.pop() as rev, then chain alternately (cur.next=rev, rev.next=next), move cur=next, decrement c; after loop set cur.next=null (avoid cycle) → O(n), O(n)
    * Reorder List (Mid Reversal + Merge): find mid (odd → exact mid, even → mid+1) with slow/fast pointers, reverse second half from slow to end, set cur=head; while(cur!=slow) traverse up to mid, save cur.next as next and rev.next as revNext to preserve chains, chain cur.next=rev and rev.next=next, advance cur=next, rev=revNext, after loop set cur.next=null (tail termination to avoid cycle) → O(n), O(1) extra space.
    * Reorder List (Recursive bottom-up with [head] for left(reference not value)): traverse to end (right moves bottom-up), carry left pointer as [head]; on unwind save next=left[0].next, chain left[0].next=right and right.next=next, advance left[0]=next; stop if (left[0].next == null) (short list) or if (left[0].next == right) (middle reached, odd/even handled) { left[0].next=null }; e.g., even: [l=2,l.next=3,r=4]→[l=3,l.next=3,r=3]→stop→1→4→2→3, odd: [l=2,l.next=3,r=5]→[l=3,l.next=4,r=4]→stop→1→5→2→4→3 → O(n), O(n) recursion stack.
    */


    /*
    * ONE LINER => Reorder List (Stack): push all nodes into stack, set c=stack.size()/2, cur=head; while(c>0) save cur.next as next and stack.pop() as rev, then chain alternately (cur.next=rev, rev.next=next), move cur=next, decrement c; after loop set cur.next=null (avoid cycle) → O(n), O(n).
    *
    * Approach: Reorder list using stack to access nodes from the end
    * - Traverse list and push all nodes onto a stack.
    * - Compute half length (stack.size()/2) → only first half needs interleaving.
    * - Reset cur=head; while(c>0):
    *     - Save cur.next in next (needed, else forward chain lost).
    *     - Save stack.pop() in rev (last node to insert).
    *     - Chain alternately: cur.next=rev, rev.next=next.
    *     - Move cur=next, decrement c.
    * - After loop, terminate with cur.next=null (avoid cycle, since last popped node may still point into earlier chain).
    *
    * Time Complexity: O(n).
    * Space Complexity: O(n).
    *
    * Key Insight: Must save both `next` and `rev` — `next` preserves forward traversal, `rev` cleanly wires the popped node back in; final null termination prevents cycles.
    */

    public void reorderListStack(ListNode head) {
        ListNode cur = head;
        var stack = new Stack<ListNode>();
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;   
        }
        int c = stack.size()/2;
        cur = head;
        while(c>0){
            ListNode next = cur.next;
            ListNode rev = stack.pop();
            cur.next = rev;
            rev.next = next;
            cur = next;
            c--;
        }
        cur.next = null;
    }
    
    
    
    /*
    * ONE LINER => Reorder List (Mid Reversal + Merge): find mid (odd → exact mid, even → mid+1) with slow/fast pointers, reverse second half from slow to end, set cur=head; while(cur!=slow) traverse up to mid, save cur.next as next and rev.next as revNext to preserve chains, chain cur.next=rev and rev.next=next, advance cur=next, rev=revNext, after loop set cur.next=null (tail termination to avoid cycle) → O(n), O(1) extra space.
    *
    * Approach: Reorder list by splitting, reversing, and merging
    * - Find mid using slow/fast pointers:
    *     - slow moves one step, fast moves two steps.
    *     - Odd length → slow points to exact mid; even length → slow points to mid+1.
    * - Reverse second half from slow to end.
    * - Merge first half and reversed second half until mid:
    *     - cur=head; while(cur!=slow):
    *         - Save cur.next in next (preserve forward traversal).
    *         - Save rev.next in revNext (preserve reversed traversal).
    *         - Chain: cur.next=rev, rev.next=next.
    *         - Move cur=next, rev=revNext.
    * - After loop, set cur.next=null (tail termination, avoid cycle).
    *
    * Time Complexity: O(n).
    * Space Complexity: O(1) extra (reverse recursion is O(n) stack but can be iterative).
    *
    * Key Insight: Traversing until mid, saving variables before chaining, and explicitly terminating tail avoids cycle and ensures correct alternate merging for odd/even cases.
    */
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head,cur = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rev = reverse(slow);
        while(cur!=slow){
            ListNode next = cur.next;
            ListNode revNext = rev.next;
            cur.next = rev;
            rev.next = next;
            cur = next;
            rev = revNext;
        }
        cur.next = null;
    }

    public ListNode reverse(ListNode node){
        if(node.next==null){
            return node;
        }
        ListNode right = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return right;
    }



    /*
    * NON trivial Educational - exit condition and tail termination is tricky
    * ONE LINER => Reorder List (Recursive bottom-up with [head] for left(reference not value)): traverse to end (right moves bottom-up), carry left pointer as [head]; on unwind save next=left[0].next, chain left[0].next=right and right.next=next, advance left[0]=next; stop if (left[0].next == null) (short list) or if (left[0].next == right) (middle reached, odd/even handled) { left[0].next=null }; e.g., even: [l=2,l.next=3,r=4]→[l=3,l.next=3,r=3]→stop→1→4→2→3, odd: [l=2,l.next=3,r=5]→[l=3,l.next=4,r=4]→stop→1→5→2→4→3 → O(n), O(n) recursion stack.
    *
    * Approach: Reorder list recursively bottom-up
    * - Pass left pointer as single-element array [head] so it can be updated across recursion calls without losing reference.
    * - Right pointer moves from head to tail by recursion depth (bottom-up).
    * - On recursion unwind:
    *     - Save next = left[0].next (preserve forward chain before rewiring).
    *     - Chain: left[0].next = right, right.next = next.
    *     - Advance left[0] = next.
    * - Base Cases:
    *     - if (left[0].next == null) → stop (short list or fully processed).
    *     - if (left[0].next == right) → middle reached, handles odd/even cases, stop weaving, set left[0].next = null (avoid cycle).
    *
    * Example Traces (states AFTER chaining):
    *
    * Even case (4 nodes):
    *     Input: [1,2,3,4]
    *     Unwind:
    *         After first chain: [l=2,l.next=3,r=4]
    *         After second chain: [l=3,l.next=3,r=3] → stop
    *     Output: 1→4→2→3
    *
    * Odd case (5 nodes):
    *     Input: [1,2,3,4,5]
    *     Unwind:
    *         After first chain: [l=2,l.next=3,r=5]
    *         After second chain: [l=3,l.next=4,r=4] → stop
    *     Output: 1→5→2→4→3
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack.
    *
    * Key Insight: This is a bottom-up weaving process with left pointer carried safely as [head];
    * both odd and even lengths are handled by the same condition (left[0].next == right),
    * which detects the meeting point and stops weaving while avoiding cycles.
    */

    public void reorderListRec(ListNode head) {
        reorderListRec(head, new ListNode[]{head});
    }
    public void reorderListRec(ListNode right, ListNode left[]) {
        if(right==null){
            return;
        }
        reorderListRec(right.next, left);
        if(left[0].next==null){
            return;
        }
        ListNode next = left[0].next;
        left[0].next = right;
        right.next = next;
        left[0] = next;
        if(left[0].next==right){
            left[0].next = null;
        }
    }
}
