/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/merge-two-sorted-lists/description/

public class MergeTwoSortedList {
    
    
    // Time O(m+n) Space: O(1)
    // approach: recursion
    // if l1 null, return l2
    // if l2 null return l1
    // if l1 val < l2 val
    // l1.next = call recusion and move l1 to right and return l1
    // else l2.next = call recusion move l2 to right and return l2
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<=l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    
    
    // same as above alternate impl
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode node = new ListNode(0);
        if(l1.val<=l2.val){
            node = l1;
            l1 = l1.next;
        }
        else{
            node = l2;
            l2 = l2.next;
        }
        node.next = mergeTwoLists2(l1, l2);
        return node;
    }
}
