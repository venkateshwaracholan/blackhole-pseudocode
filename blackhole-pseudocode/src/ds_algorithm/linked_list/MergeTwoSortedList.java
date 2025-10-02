/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/merge-two-sorted-lists/description/
public class MergeTwoSortedList {

    /*
    * ONE LINERS — Quick Reference:
    *
    * Recursive Merge: if list1 or list2 is null return the other (base case), else compare head values — if list1.val < list2.val (link list1 to merged remainder → list1.next = mergeTwoListsRec(list1.next,list2)) else (link list2 to merged remainder → list2.next = mergeTwoListsRec(list1,list2.next)), return the smaller head → O(n+m), O(n+m) (stack).
    * Iterative Merge (Dummy Node): dummy=0, cur=dummy, while both lists not null compare heads, attach smaller to cur.next, move chosen list pointer forward (list1=list1.next or list2=list2.next) and cur pointer forward (cur=cur.next), after loop attach remaining list (cur.next = list1 != null ? list1 : list2) → O(n+m), O(1).
    * Unsafe Merge Without Dummy: head=cur=null, loop while list1!=null || list2!=null (null handling with Integer.MAX_VALUE), pick smaller node as next, move chosen list pointer forward (list1=list1.next or list2=list2.next), if head==null assign head=cur=next else set cur.next=next and cur=next, return head → O(n+m), O(1).
    */


    
    //APPROACH 1 rec, return other if null, if val less, node.next = rec(move that node to right), return node
    
    /*
    * ONE LINER => Recursive Merge: if list1 or list2 is null return the other (base case), else compare head values — if list1.val < list2.val (link list1 to merged remainder → list1.next = mergeTwoListsRec(list1.next,list2)) else (link list2 to merged remainder → list2.next = mergeTwoListsRec(list1,list2.next)), return the smaller head → O(n+m), O(n+m) (stack).
    *
    * Approach: Recursive merge of two sorted linked lists
    * - Base cases:
    *     - if list1 == null → return list2 (remaining list2 is already sorted).
    *     - if list2 == null → return list1 (remaining list1 is already sorted).
    * - Compare list1.val and list2.val:
    *     - If list1.val < list2.val:
    *         - list1.next = mergeTwoListsRec(list1.next, list2) (link list1 to merged remainder, effectively placing list1 first in merged list).
    *         - return list1 (list1’s head is the new merged head for this step).
    *     - Else:
    *         - list2.next = mergeTwoListsRec(list1, list2.next) (link list2 to merged remainder, effectively placing list2 first in merged list).
    *         - return list2 (list2’s head is the new merged head for this step).
    *
    * Time Complexity: O(n+m) — each node from both lists is visited once.
    * Space Complexity: O(n+m) — recursion stack for total nodes in both lists.
    *
    * Rationale: This elegant recursion works by merging from the heads downward, always picking the smaller head and merging the rest, naturally building the merged sorted list without extra data structures.
    */
    public ListNode mergeTwoListsRec(ListNode list1, ListNode list2) {
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }
        if(list1.val<list2.val){
            list1.next = mergeTwoListsRec(list1.next,list2);
            return list1;
        }else{
            list2.next = mergeTwoListsRec(list1, list2.next);
            return list2;
        }
    }
    
    
    /*
    * ONE LINER => Iterative Merge: dummy=0, cur=dummy, while both lists not null compare heads, attach smaller to cur.next, move chosen list pointer forward (list1=list1.next or list2=list2.next) and cur pointer forward (cur=cur.next), after loop attach remaining list (cur.next = list1 != null ? list1 : list2) → O(n+m), O(1).
    *
    * Approach: Iterative merging of two sorted linked lists
    * - Create dummy node to simplify head handling (dummy=0, cur=dummy).
    * - While both lists are not null:
    *     - Compare list1.val and list2.val.
    *     - Attach smaller node to cur.next (preserve sort order).
    *     - Move chosen list pointer forward (list1=list1.next or list2=list2.next) → progress through list.
    *     - Move cur pointer forward (cur=cur.next) → build merged list.
    * - After loop, attach remaining list to cur.next (`cur.next = list1 != null ? list1 : list2`) → appends leftover nodes of whichever list is not null.
    * - Return dummy.next → new head of merged list.
    *
    * Time Complexity: O(n+m) — each node is visited once.
    * Space Complexity: O(1) — only constant extra space used.
    *
    * Rationale: Dummy node simplifies edge cases and pointer movement ensures linear merging without extra memory allocation.
    */
    public ListNode mergeTwoListsIte(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                cur.next = list1;
                list1 = list1.next;
            }
            else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1!=null ? list1 : list2;
        return dummy.next;
    }
    
    
    
    /*
    * ONE LINER => Unsafe(EDUCATIONAL) Merge Without Dummy: head=cur=null,loop while list1!=null || list2!=null (null handling with Integer.MAX_VALUE), pick smaller node as next, move chosen list pointer forward (list1=list1.next or list2=list2.next), if head==null assign head=cur=next else set cur.next=next and cur=next, return head → O(n+m), O(1).
    *
    * Approach: Merge two sorted linked lists without a dummy node
    * - head = cur = null
    * - Loop: while(list1!=null || list2!=null) → continues until both lists are exhausted.
    * - Null handling: if list1 or list2 is null, treat its value as Integer.MAX_VALUE for comparison.
    * - Select smaller node:
    *     - Store selected node in `next` so we have a reference before moving the list pointer.
    *     - Move pointer of chosen list (list1 or list2) forward (list1=list1.next or list2=list2.next).
    * - Head initialization:
    *     - If head==null → first element found, assign head=cur=next.
    *       (Both head and cur must be assigned because head tracks list start and cur tracks last appended node.)
    * - Node linking:
    *     - Else → cur.next=next (link current node to selected node).
    *     - Move cur forward (cur=next) to prepare for next link.
    * - Return head when done.
    *
    * Time Complexity: O(n+m) — each node visited once.
    * Space Complexity: O(1) — in-place merging without extra data structures.
    *
    * ⚠ Unsafe:  
    * - OR condition makes loop continue even after one list is exhausted, causing unnecessary comparisons.
    * - Integer.MAX_VALUE trick for null values is error-prone and affects readability.
    * - Requires explicit handling for head initialization and pointer movement.
    *
    * Recommendation: Prefer using a dummy node to avoid special-case handling and make logic cleaner.
    */
    public ListNode mergeTwoListsWithoutDummy(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode cur = null;
        while(list1!=null||list2!=null){
            int v1 = list1!=null?list1.val:Integer.MAX_VALUE;
            int v2 = list2!=null?list2.val:Integer.MAX_VALUE;
            ListNode next;
            if(v1<v2){
                next = list1;
                list1=list1.next;
            }else{
                next = list2;
                list2=list2.next;
            }
            if(head==null){
                head=cur=next;
            }
            else{
                cur.next = next;
                cur = next;
            }
        }
        return head;
    }
    
    
    
    
    public static void main(String[] args){
        System.out.println("HAHAH");
        ListNode l= new ListNode(0);
        System.out.println(l.val);
    }
}
