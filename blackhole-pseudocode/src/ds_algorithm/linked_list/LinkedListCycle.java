/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;
import java.util.*;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/linked-list-cycle/description/
 
public class LinkedListCycle {

    /*
    * ONE LINERS — Quick Reference:
    *
    * Cycle Detection Brute (HashSet): traverse list, store visited nodes in set, if a node repeats return true → O(n), O(n).
    * Cycle Detection (Floyd’s Tortoise and Hare): slow=head, fast=head, move slow by 1 and fast by 2 steps, then check if slow equals fast (only after both moves, coz they are initially equal) → O(n), O(1).
    */

    
    /*
    * ONE LINER => Cycle Detection Brute (HashSet): traverse list, store visited nodes in set, if a node repeats return true → O(n), O(n).
    *
    * Approach: Detect cycle in linked list using extra memory
    * - Create a HashSet to store visited nodes.
    * - While head is not null:
    *     - If current node exists in set → cycle detected, return true.
    *     - Else add current node to set.
    *     - Move head to next node (head=head.next).
    * - If loop exits → no cycle found, return false.
    *
    * Time Complexity: O(n) — each node is visited once.
    * Space Complexity: O(n) — stores all visited nodes.
    *
    * Rationale: Stores all visited nodes to detect revisits (cycle) at the cost of extra memory.
    */

    public boolean hasCycleButeSet(ListNode head) {
        var set = new HashSet<ListNode>();
        while(head!=null){
            if(set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /*
    * ONE LINER => Cycle Detection (Floyd’s Tortoise and Hare): slow=head, fast=head, move slow by 1 and fast by 2 steps, then check if slow equals fast (only after both moves, coz they are initially equal) → O(n), O(1).
    *
    * Approach: Detect cycle in linked list using Floyd’s cycle-finding algorithm
    * - Initialize two pointers: slow=head, fast=head.
    * - While fast and fast.next are not null:
    *     - Move slow pointer by one step (slow=slow.next).
    *     - Move fast pointer by two steps (fast=fast.next.next).
    *     - Only after both moves, check if slow equals fast → if true, cycle detected, return true (important: check happens only after moves since they start equal).
    * - If loop exits → no cycle found, return false.
    *
    * Time Complexity: O(n) — slow and fast traverse at most n steps before meeting or terminating.
    * Space Complexity: O(1) — constant extra space.
    *
    * Rationale: Moving both pointers before equality check avoids false positives and ensures correctness, since slow and fast start at the same node.
    */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast = fast.next.next;
            if(slow.equals(fast))return true;
        }
        return false;
    }
    


























    //unwanted approach, dont tr this

    //APPROACH 1 (ite or rec) changing value of node to 0xcafebabe, lol 
    // Time O(n) space: O(1)
    // modifying LIst values, bad approach
    // but was fun
    public boolean hasCycle3(ListNode head) {
        while(head!=null){
            if(head.val == 0xcafebabe) return true;
            head.val = 0xcafebabe;
            head=head.next;
        }
        return false;
    }
    // same as above even shorter
    public boolean hasCycle4(ListNode head) {
        return (head!=null) && (head.val == (head.val = 0xcafebabe) || hasCycle(head.next));
    }
  
}
