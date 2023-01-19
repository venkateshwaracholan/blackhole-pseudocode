/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/linked-list-cycle/description/
 
public class LinkedListCycle {
    
    //APPROACH 1 ite slow fast pointers, 
    // Time O(n) space: O(1)
    // floyd cycle detection
    // two pointers, slow and fast pointers
    //if they become equal at some point, cycle is there
    public boolean hasCycle(ListNode head) {
        ListNode slow = head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast = fast.next.next;
            if(slow.equals(fast))return true;
        }
        return false;
    }
    
    //APPROACH 1 ite+set, 
    // Time O(n) space: O(n)
    // hashset, lame
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet();
        while(head!=null){
            if(set.contains(head)) return true;
            set.add(head);
            head=head.next;
        }
        return false;
    }
    
    //APPROACH 1 (ite or rec) changing value of node to 0xcafebabe, lol 
    // Time O(n) space: O(1)
    // modifying LIst values, bad approach
    // but was fun
    public boolean hasCycle(ListNode head) {
        while(head!=null){
            if(head.val == 0xcafebabe) return true;
            head.val = 0xcafebabe;
            head=head.next;
        }
        return false;
    }
    // same as above even shorter
    public boolean hasCycle(ListNode head) {
        return (head!=null) && (head.val == (head.val = 0xcafebabe) || hasCycle(head.next));
    }
  
}
