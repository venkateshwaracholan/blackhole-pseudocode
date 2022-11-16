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
public class MiddleOfLinkedList {
  // ALWAYS remember n/2 will end in middle and second if even,
  // n-1/2 will end in middle and first if even.
  
  public static ListNode middleNode(ListNode head) {
    int c = 0;
    ListNode temp = head;
    while(temp!=null){
        c++;
        temp=temp.next;
    }
    temp = head; int x = 0;
    while(temp!=null){
        if(c/2==x)
            return temp;
        temp=temp.next;
        x++;
    }
    return null;
  }
  // slow and fast pointers
  public static ListNode middleNodeFloyds(ListNode head) {
    ListNode slow = head, fast = head;
    while(fast!=null && fast.next!=null){
        slow= slow.next;
        fast = fast.next.next;
    }
    return slow;
  }
}


// 1 2 3 4 5
// 5 5 2
// 6 6 3