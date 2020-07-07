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

// https://leetcode.com/problems/odd-even-linked-list/

public class OddEvenLinkedList {
  
//  Time: O(n) space: O(1)
//  odd, even and even head poinnter is necessary
//  evenhead is usedful n merging odd tail to even head
//  if even and its next exist we have to assign odd's next and itself to even.next
//  and even'snext and itself to odd's next
//  finally, adding odd's tail to even's head
//  return head as its in place
  public static ListNode oddEvenList(ListNode head) {
    if(head==null)return head;
    ListNode odd = head, even = head.next,evenHead= even;
    while(even!=null && even.next!=null){
        odd.next = even.next;
        odd = odd.next;
        even.next = odd.next;
        even = even.next;
    }
    odd.next = evenHead;
    return head;
  }
   
 public static void main(String args[]){
    SinglyLinkedList l1 = new SinglyLinkedList(new int[]{1,2,3,4,5,6});
    oddEvenList(l1.head);
  }
}
