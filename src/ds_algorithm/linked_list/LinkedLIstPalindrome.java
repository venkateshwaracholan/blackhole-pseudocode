/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

import static ds_algorithm.linked_list.reverseLinkeList.reverseLinkedListRec2;

/**
 *
 * @author venkateshwarans
 */
public class LinkedLIstPalindrome {
  public static boolean linkedListPalindromeRec(ListNode head){
    return linkedListPalindromeRec(head, new ListNode[]{head});
  }
  // 1 2 2 1
  public static boolean linkedListPalindromeRec(ListNode node, ListNode[] cur){
    if(node==null){
      return true;
    }
    boolean ans = linkedListPalindromeRec(node.next, cur);
    //System.out.println(node.val);
    if(node.val != cur[0].val){
      ans = false;
    }
    cur[0] = cur[0].next;
    return ans;
  }
  
  public static boolean linkedListPalindromeRec2(ListNode head){
    return linkedListPalindromeRec2(head,head) == head;
  }
  // 1 2 2 1
  public static ListNode linkedListPalindromeRec2(ListNode rev, ListNode cur){
    if(rev==null){
      return cur;
    }
    cur =  linkedListPalindromeRec2(rev.next, cur);
    if(cur==null || rev.val != cur.val){
      return null;
    }else{
      return cur.next!=null ? cur.next : rev;
    }
  }
  
  // 3 2 1 2 3
  public static boolean linkedListPalindromeIte(ListNode head){
    ListNode n1 = head;
    ListNode n2 = head;
    while(n2!=null && n2.next!=null){
      n1 = n1.next;
      n2 = n2.next.next;
    }
    if(n1!=null) n1 = n1.next;
    n1 = reverseLinkedListRec2(n1);
    ListNode temp1 = head;
    ListNode temp2 = n1;
    while(temp2!=null){
      //System.out.println(temp1.val+" : "+temp2.val);
      if(temp1.val!=temp2.val){
        return false;
      }
      temp1 = temp1.next;
      temp2 = temp2.next; 
    }
    reverseLinkedListRec2(n1);
    return true;
  }
  
  public static void main(String args[]){
    SinglyLinkedList l1;
//    l1 = new SinglyLinkedList(new int[]{1});
//    test(linkedListPalindromeRec(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,1});
//    test(linkedListPalindromeRec(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
//    test(linkedListPalindromeRec(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,2});
//    test(linkedListPalindromeRec(l1.head), false);
//    l1 = new SinglyLinkedList(new int[]{2,2,2});
//    test(linkedListPalindromeRec(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{2,2,1});
//    test(linkedListPalindromeRec(l1.head), false);
//    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
//    test(linkedListPalindromeRec(l1.head), false);
    
    
//    l1 = new SinglyLinkedList(new int[]{1});
//    test(linkedListPalindromeIte(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,1});
//    test(linkedListPalindromeIte(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
//    test(linkedListPalindromeIte(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{1,2,2});
//    test(linkedListPalindromeIte(l1.head), false);
//    l1 = new SinglyLinkedList(new int[]{2,2,2});
//    test(linkedListPalindromeIte(l1.head), true);
//    l1 = new SinglyLinkedList(new int[]{2,2,1});
//    test(linkedListPalindromeIte(l1.head), false);
//    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
//    test(linkedListPalindromeIte(l1.head), false);


    l1 = new SinglyLinkedList(new int[]{1});
    test(linkedListPalindromeRec2(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,1});
    test(linkedListPalindromeRec2(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
    test(linkedListPalindromeRec2(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2});
    test(linkedListPalindromeRec2(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{2,2,2});
    test(linkedListPalindromeRec2(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{2,2,1});
    test(linkedListPalindromeRec2(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
    test(linkedListPalindromeRec2(l1.head), false);
  }
  
  public static void test(boolean got, boolean exp){
    System.out.println(got==exp);
//    System.out.println("got     : "+got);
//    System.out.println("expected: "+exp);
  }
}
