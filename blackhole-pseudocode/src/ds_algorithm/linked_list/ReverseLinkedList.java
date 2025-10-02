/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

import com.google.gson.Gson;
import ds_algorithm.linked_list.SinglyLinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import ds_algorithm.utils.ArrayUtils;
/**
 *
 * @author venkateshwarans
 */


// https://leetcode.com/problems/reverse-linked-list/description/


//1->2->3->4->5->N

public class ReverseLinkedList {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Iterative Reverse List: prev=null, cur=head, while cur!=null store next(cur.next before modifying cur.next), reverse link(cur.next=prev), move pointers(prev=cur, cur=next), return prev → O(n), O(1).
    * Top‑down Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, store next(cur.next), reverse link(cur.next=prev), recurse with(next,cur) → O(n), O(n) (stack).
    * Bottom‑up Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, recurse head=reverseList(cur.next,cur) without changing head until recursion unwinds (because reversal happens bottom‑up), after recursion unwinds reverse link via cur(cur.next=prev), return head level by level → O(n), O(n) (stack).
    * Bottom‑up Recursive Reverse List (Single Method): base case: head==null || head.next==null (handle empty list and return last node as new head), recurse last=reverseListBottomUpSingleMethod(head.next) without changing last until recursion unwinds (because reversal happens bottom‑up), then reverse link via head.next.next=head (modify head here), then mark head.next=null to terminate old link so that first element’s next becomes null, preserve last as new head to return → O(n), O(n) (stack).
    */

  
    /*
    * ONE LINER => Iterative Reverse List: prev=null, cur=head, while cur!=null store next(cur.next before modifying cur.next), reverse link(cur.next=prev), move pointers(prev=cur, cur=next), return prev → O(n), O(1).
    *
    * Approach: Iterative reversal of a singly linked list
    * - Initialize prev=null (to mark new list end), cur=head (start of list).
    * - While cur is not null:
    *     - Store next node (next=cur.next) → to not lose access to rest of list.
    *     - Reverse link (cur.next=prev) → flips pointer direction.
    *     - Move pointers (prev=cur, cur=next) → progress through list.
    * - Return prev → new head of reversed list.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(1) — constant extra space.
    *
    * Rationale: Iteratively reverses pointers to reverse list in-place without extra space.
    */
    public ListNode reverseListIte(ListNode head) {
        ListNode prev = null, cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    
    /*
    * ONE LINER => Top‑down Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, store next(cur.next), reverse link(cur.next=prev), recurse with(next,cur) → O(n), O(n) (stack).
    *
    * Approach: Top‑down Recursive reversal of a singly linked list (implement and call reverseList(head,null))
    * - Call reverseList(cur=head,prev=null) → prev initially null (implement and call step).
    * - Base case: if cur is null → return prev (new head).
    * - Store next node (next=cur.next) → to not lose the rest of the list.
    * - Reverse link (cur.next=prev) → flips pointer direction.
    * - Recurse with remaining list (reverseList(next,cur)) → progress through list.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack of depth n.
    *
    * Rationale: Elegant top‑down reversal that uses recursion to reverse links while unwinding the call stack.
    */
    public ListNode reverseListTopDown(ListNode head) {
        return reverseListTopDown(head, null);
    }

    public ListNode reverseListTopDown(ListNode cur, ListNode prev){
        if(cur==null){
            return prev;
        }
        ListNode next = cur.next;
        cur.next = prev;
        return reverseListTopDown(next,cur);
    }
    
    /*
    * ONE LINER => Bottom‑up Recursive Reverse List: implement and call reverseList(cur=head,prev=null), base case: if cur==null return prev, recurse head=reverseList(cur.next,cur) without changing head until recursion unwinds (because reversal happens bottom‑up), after recursion unwinds reverse link via cur(cur.next=prev), return head level by level → O(n), O(n) (stack).
    *
    * Approach: Bottom‑up Recursive reversal of a singly linked list
    * - Implement and call reverseList(cur=head,prev=null) → prev initially null.
    * - Base case: if cur is null → return prev (new head).
    * - Recurse first (head=reverseList(cur.next,cur)) without changing head until recursion unwinds
    *   (ensures reversal happens bottom‑up and head is preserved until the final unwind).
    * - After recursion unwinds level by level, reverse link (cur.next=prev).
    * - Return head found at deepest call.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack of depth n.
    *
    * Rationale: Bottom‑up recursion ensures reversal happens in reverse call order and head is preserved until final unwind.
    */
    public ListNode reverseListBottomUp(ListNode head) {
        return reverseListBottomUp(head, null);
    }

    public ListNode reverseListBottomUp(ListNode cur, ListNode prev){
        if(cur==null){
            return prev;
        }
        ListNode head = reverseListBottomUp(cur.next,cur);
        cur.next = prev;
        return head;
    }
    
    
    /*
    * ONE LINER => Bottom‑up Recursive Reverse List (Single Method): base case: head==null || head.next==null (handle empty list and return last node as new head), recurse last=reverseListBottomUpSingleMethod(head.next) without changing last until recursion unwinds (because reversal happens bottom‑up), then reverse link via head.next.next=head (modify head here), then mark head.next=null to terminate old link so that first element’s next becomes null, preserve last as new head to return → O(n), O(n) (stack).
    *
    * Approach: Bottom‑up Recursive reversal in a single method
    * - Base case: if head==null || head.next==null → return head (handles empty list and captures last node as new head).
    * - Recurse: last=reverseListBottomUpSingleMethod(head.next) without changing last until recursion unwinds → ensures reversal happens bottom‑up.
    * - Reverse link: head.next.next=head (modifies head here to point backward).
    * - Terminate old link: head.next=null so that first element’s next becomes null.
    * - Preserve and return last from deepest recursion call as new head (do not change last inside recursion).
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack of depth n.
    *
    * Rationale: Bottom‑up recursion with single method preserves last and reverses links correctly, avoiding premature modification of the final new head, and ensures first element’s next becomes null to end the reversed list.
    */
    public ListNode reverseListBottomUpSingleMethod(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode last = reverseListBottomUpSingleMethod(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    
 
  public static void main(String args[]){
      
  }
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(ArrayUtils.reverse(exp));
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
}



// please check what you are passing through the recursion.
// you solved iterative easily, but took time to solve with both the recursiev methods, use example to derive.