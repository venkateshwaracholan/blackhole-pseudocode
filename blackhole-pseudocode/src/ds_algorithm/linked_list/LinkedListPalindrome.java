/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;


/**
 *
 * @author venkateshwarans
 */
public class LinkedListPalindrome {
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
  
  public static ListNode linkedListPalindromeRec2Alt1(ListNode node, ListNode start){
    if(node==null){
      return start;
    }
    ListNode n = linkedListPalindromeRec2Alt1(node.next, start);
    if(n!=null && n.val == node.val){
      return n.next == null ? start : n.next;
    }else{
      return null;
    }
  }
  
  // 3 2 1 2 3
  public static boolean linkedListPalindromeIte(ListNode head){
    ListNode fast = head,slow = head;
    boolean ans = true;
    while(fast!=null && fast.next!=null){
      fast = fast.next.next;
      slow = slow.next;
    }
    
//    for(; fast!=null && fast.next!=null;slow = slow.next, fast = fast.next.next){
//    }
    if(fast!=null){
      slow = slow.next;
    }
    ListNode rev = reverse(slow);
    ListNode temp = head;
    while(rev!=null && temp!=null){
      if(rev.val!=temp.val){
        ans = false;break;
      }
      rev = rev.next;
      temp = temp.next;
    }
    reverse(slow);
    return ans;
  }
  
  public static ListNode reverse(ListNode node){
    return reverse(null, node);
  }
  public static ListNode reverse(ListNode prev, ListNode node){
    if(node==null) return prev;
    ListNode next = node.next;
    node.next = prev;
    return reverse(node, next);
  }
  
  public static void main(String args[]){
    SinglyLinkedList l1;
    l1 = new SinglyLinkedList(new int[]{1});
    test(linkedListPalindromeRec(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,1});
    test(linkedListPalindromeRec(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
    test(linkedListPalindromeRec(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2});
    test(linkedListPalindromeRec(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{2,2,2});
    test(linkedListPalindromeRec(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{2,2,1});
    test(linkedListPalindromeRec(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
    test(linkedListPalindromeRec(l1.head), false);
    
    
    l1 = new SinglyLinkedList(new int[]{1});
    test(linkedListPalindromeIte(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,1});
    test(linkedListPalindromeIte(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
    test(linkedListPalindromeIte(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2});
    test(linkedListPalindromeIte(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{2,2,2});
    test(linkedListPalindromeIte(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{2,2,1});
    test(linkedListPalindromeIte(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
    test(linkedListPalindromeIte(l1.head), false);


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
    
    System.out.println("TEST>>>>>>>");
    
    l1 = new SinglyLinkedList(new int[]{1});
    test(linkedListPalindromeTest(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,1});
    test(linkedListPalindromeTest(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2,1});
    test(linkedListPalindromeTest(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{1,2,2});
    test(linkedListPalindromeTest(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{2,2,2});
    test(linkedListPalindromeTest(l1.head), true);
    l1 = new SinglyLinkedList(new int[]{2,2,1});
    test(linkedListPalindromeTest(l1.head), false);
    l1 = new SinglyLinkedList(new int[]{1,2,2,3,2});
    test(linkedListPalindromeTest(l1.head), false);
  }
  
  // 1 2 2 1 N
  // 1 2 1 N
  // 1 2 N
  // 1 N
  public static boolean linkedListPalindromeTest(ListNode head){
    ListNode slow = head,fast = head, temp = head;
    for(; fast!=null && fast.next!=null;slow = slow.next, fast = fast.next.next){
    }
    slow = reverse(slow);
    boolean ans = true;
    ListNode temp2 = slow;
    while(temp2!=null && temp!=null){
      if(temp2.val != temp.val){
        ans =  false;
      }
      temp2 = temp2.next;
      temp = temp.next;
    }
    reverse(slow);
    SinglyLinkedList.print(head);
    return ans;
  }
  
  public static void test(boolean got, boolean exp){
    System.out.println(got==exp);
//    System.out.println("got     : "+got);
//    System.out.println("expected: "+exp);
  }
}
