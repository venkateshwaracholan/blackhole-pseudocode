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

    
    /*
    * ONE LINERS — Quick Reference:\
    *
    * Remove Nth Node From End (Two Pass): traverse list with cur=head while(cur!=null) increment len (count total nodes), if len==n return head.next (first node removed), else reset cur=head, for i=0;i<len-n-1;i++ cur=cur.next (land on node before target), set cur.next=cur.next.next (skip target) and return head → O(2n), O(1) (explicit bounds and pointer advances without extra memory).
    * Remove Nth Node From End (Recursive with Reference): call recursion(head, new int[]{n}) (passing as reference), if(cur==null) return cur (base case); call recursion(cur.next, n) (recursion call); on unwind set cur.next=next (preserve chain after possible skip from return value), decrement n[0] (compare with 0, not 1), if n[0]==0 return next (skip target), else return cur → O(n), O(n) recursion stack (single pass bottom-up without explicit length counting).
    * Remove Nth Node From End (Two Pointers): set prev=head, cur=head; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; if cur==null return head.next (target is first node); else while(cur.next!=null) (ensures prev lands on node before target) advance both prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node) → O(n), O(1).
    * Remove Nth Node From End (Two Pointers + Dummy): create dummy=0,dummy.next=head (dummy removes first element removal edge case), set prev=dummy,cur=dummy; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; while(cur.next!=null) (ensures prev lands on node before target) advance prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node); return dummy.next (coz we added dummy) → O(n), O(1).
    */

    
    /*
    * ONE LINER => Remove Nth Node From End (Two Pass): traverse list with cur=head while(cur!=null) increment len (count total nodes), if len==n return head.next (first node removed), else reset cur=head, for i=0;i<len-n-1;i++ cur=cur.next (land on node before target), set cur.next=cur.next.next (skip target) and return head → O(2n), O(1) (explicit bounds and pointer advances without extra memory).
    *
    * Approach: Remove Nth node from end of linked list using two passes
    * - First pass:
    *     - cur=head; while(cur!=null) advance cur=cur.next, increment len → counts length (len).
    * - If len==n → target is head, remove it by returning head.next.
    * - Second pass:
    *     - Reset cur=head.
    *     - Traverse len-n-1 steps (for i=0;i<len-n-1;i++) to land on node before target.
    *     - Set cur.next=cur.next.next to skip target node.
    * - Return head.
    *
    * Time Complexity: O(2n) — one full traversal for length counting plus another to land before target node.
    * Space Complexity: O(1) — constant extra space.
    *
    * Key Insight: Exact bounds in the second pass (len-n-1) ensure cur lands precisely on the node before the one to remove so pointer rewiring is straightforward; len==n case handles removal of the head node without extra logic.
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        int len = 0;
        while(cur!=null){
            cur = cur.next;
            len++;
        }
        if(len==n){
            return head.next;
        }
        cur = head;
        for(int i=0;i<len-n-1;i++){
            cur = cur.next;
        }
        cur.next = cur.next.next; 
        return head;
    }
    
    /*
    * ONE LINER => Remove Nth Node From End (Recursive with Reference): call recursion(head, new int[]{n}) (passing as reference), if(cur==null) return cur (base case); call recursion(cur.next, n) (recursion call); on unwind set cur.next=next (preserve chain after possible skip from return value), decrement n[0] (compare with 0, not 1), if n[0]==0 return next (skip target), else return cur → O(n), O(n) recursion stack (single pass bottom-up without explicit length counting).
    *
    * Approach: Remove Nth node from end of linked list recursively carrying n as reference
    * - Initial Call: call removeNthFromEndRecReference(head, new int[]{n}) (passing as reference) → pass head and n wrapped in array to carry reference across recursion calls.
    * - Base Case: if(cur==null) return cur → reached end of list.
    * - Recursion Call: call removeNthFromEndRecReference(cur.next, n) (passing as reference) → traverse to list end before processing.
    * - On recursion unwind:
    *     - Save returned next node from deeper recursion → next.
    *     - Set cur.next=next to preserve chain after possible skip from return value.
    *     - Decrement n[0] (compare with 0, not 1) to count nodes from end.
    *     - If n[0]==0 → target node found, skip it by returning next (removes node).
    *     - Else return cur to continue rewiring chain.
    * - Final return from top-level call is new head of modified list.
    *
    * Time Complexity: O(n) — single full traversal of list.
    * Absolute Time Complexity: O(n) traversal = O(2n) (since n recursive calls + n rewiring steps).
    * Space Complexity: O(n) — recursion stack space proportional to list length.
    *
    * Key Insight: Passing n as array lets recursion carry it by reference, allowing a bottom‑up count from end without explicit length calculation; cur.next=next preserves the chain after possible skip, decrement n[0] and comparing with 0 detects the node to skip, and returning cur vs next rewires the list appropriately without extra variables or second pass.
    */
    public ListNode removeNthFromEndRecReference(ListNode head, int n) {
        return removeNthFromEndRecReference(head, new int[]{n});
    }
    public ListNode removeNthFromEndRecReference(ListNode cur, int n[]) {
        if(cur==null){
            return cur;
        }
        ListNode next = removeNthFromEndRecReference(cur.next, n);
        cur.next = next;
        n[0]--;
        if(n[0]==0){
            return next;
        }
        return cur;
    }
    
    
    /*
    * ONE LINER => Remove Nth Node From End (Two Pointers): set prev=head, cur=head; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; if cur==null return head.next (target is first node); else while(cur.next!=null) (ensures prev lands on node before target) advance both prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node) → O(n), O(1).
    *
    * Approach: Remove Nth node from end of linked list using two-pointer technique
    * - Initialize prev=head, cur=head.
    * - Advance cur by n steps:
    *     - while(n>0): cur=cur.next, n-- → positions cur n steps ahead of prev so the gap is exactly n nodes.
    * - If cur==null → n equals list length, target is the first node, so return head.next.
    * - Else traverse until cur.next==null:
    *     - while(cur.next!=null) (ensures prev lands on node before target): prev=prev.next, cur=cur.next.
    * - Skip target node: prev.next=prev.next.next.
    * - Return head → modified list without nth node from end.
    *
    * Time Complexity: O(n) — one pass of the list (n steps to advance cur + up to n steps traversal).
    * Absolute Time Complexity: O(2n) — worst case is advancing cur (n steps) plus traversal to end (n steps).
    * Space Complexity: O(1) — constant extra space.
    *
    * Key Insight: Two-pointer gap method allows removing nth node in single pass without length counting; advancing cur by n ensures prev lands immediately before target node; prev.next=prev.next.next rewires chain to skip target; cur==null handles edge case where first node is target.
    */
    public ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        ListNode prev = head,cur = head;
        while(n>0){
            cur = cur.next;
            n--;
        }
        if(cur==null){
            return head.next;
        }
        while(cur.next!=null){
            prev = prev.next;
            cur = cur.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    /*
    * ONE LINER => Remove Nth Node From End (Two Pointers + Dummy): create dummy=0,dummy.next=head (dummy removes first element removal edge case), set prev=dummy,cur=dummy; advance cur by n steps (while n>0 cur=cur.next, n--) to create n‑node gap; while(cur.next!=null) (ensures prev lands on node before target) advance prev=prev.next and cur=cur.next; after loop prev.next=prev.next.next (skip target node); return dummy.next (coz we added dummy) → O(n), O(1).
    *
    * Approach: Remove Nth node from end using two-pointer technique with dummy node to simplify edge cases
    * - Create dummy node with dummy.next=head to handle removal of first node cleanly.
    * - Initialize prev=dummy, cur=dummy.
    * - Advance cur by n steps:
    *     - while(n>0): cur=cur.next, n-- → creates gap of n between cur and prev.
    * - Traverse until cur.next==null:
    *     - while(cur.next!=null) (ensures prev lands on node before target): prev=prev.next, cur=cur.next.
    * - Skip target node: prev.next=prev.next.next.
    * - Return dummy.next (coz we added dummy).
    *
    * Time Complexity: O(n) — one pass through list.
    * Absolute Time Complexity: O(2n) — n steps to advance cur + up to n steps traversal.
    * Space Complexity: O(1) — constant extra space.
    *
    * Key Insight: Dummy node removes first element removal edge case; two-pointer gap method lands prev before target node; prev.next=prev.next.next rewires chain to skip target cleanly; returning dummy.next (coz we added dummy) ensures correct updated head.
    */
    public ListNode removeNthFromEndTwoPointersDummy(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy,cur = dummy;
        dummy.next = head;
        while(n>0){
            cur = cur.next;
            n--;
        }
        while(cur.next!=null){
            prev = prev.next;
            cur = cur.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }
    
    
    
}
