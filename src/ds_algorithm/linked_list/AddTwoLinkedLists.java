/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

import com.google.gson.Gson;
import static ds_algorithm.linked_list.reverseLinkeList.reverseLinkedListRec2;
import ds_algorithm.utils.ArrayUtils;
import java.util.ArrayList;

/**
 *
 * @author venkateshwarans
 */
public class AddTwoLinkedLists {
  
  public static ListNode addLinkedListsIte(ListNode l1, ListNode l2){
    l1 = reverseLinkedListRec2(l1);
    l2 = reverseLinkedListRec2(l2);
    int sum = 0;
    ListNode res = new ListNode(0), cur = res;
    while(l1!=null || l2!=null || sum!=0){
      if(l1!=null){
        sum += l1.val;
        l1 = l1.next;
      }
      if(l2!=null){
        sum += l2.val;
        l2 = l2.next;
      }
      ListNode n = new ListNode(sum%10);
      sum/=10;
      cur.next = n;
      cur = n;
    }
    return reverseLinkedListRec2(res.next);
  }
  
  public static ListNode addLinkedListsRec(ListNode l1, ListNode l2){
    int len1 = 0,len2=0;
    for(ListNode temp =l1;temp!=null;temp=temp.next){
      len1++;
    }
    for(ListNode temp =l2;temp!=null;temp=temp.next){
      len2++;
    }
    ListNode n = addLinkedListsRec(l1,l2,len1,len2);
    return n.val==0 ? n.next : n;
  }
  // 7243
  //  564
  public static ListNode addLinkedListsRec(ListNode l1, ListNode l2, int len1,int len2){
    if(l1==null && l2==null){
      return new ListNode(0);
    }
    int sum = 0;
    ListNode next = null;
    if(len1==len2){
      next = addLinkedListsRec(l1.next, l2.next, len1-1, len2-1);
      sum = l1.val + l2.val +next.val;
    }else if(len1>len2){
      next = addLinkedListsRec(l1.next, l2, len1-1, len2);
      sum = l1.val +next.val;
    }else{
      next = addLinkedListsRec(l1, l2.next, len1, len2-1);
      sum = l2.val +next.val;
    }
    
    next.val = sum%10;
    ListNode n = new ListNode(sum/10);
    n.next = next;
    return n;
  }
  
  public static ListNode addLinkedListsIteAlt1(ListNode l1, ListNode l2){
    l1 = reverseLinkedListRec2(l1);
    l2 = reverseLinkedListRec2(l2);
    int sum = 0;
    ListNode res = null, cur = null;
    while(l1!=null || l2!=null || sum!=0){
      if(l1!=null){
        sum += l1.val;
        l1 = l1.next;
      }
      if(l2!=null){
        sum += l2.val;
        l2 = l2.next;
      }
      ListNode n = new ListNode(sum%10);
      sum/=10;
      if(res == null){
        res = n;
      }else{
        cur.next = n;
      }
      cur = n;
    }
    return reverseLinkedListRec2(res);
  }
  
  public static void main(String args[]){
    SinglyLinkedList l1, l2;
//    l1 = new SinglyLinkedList(new int[]{7,2,4,3});
//    l2 = new SinglyLinkedList(new int[]{5,6,4});
//    test(SinglyLinkedList.toList(addLinkedListsIte(l1.head, l2.head)), new int[]{7,8,0,7});
//    l1 = new SinglyLinkedList(new int[]{2});
//    l2 = new SinglyLinkedList(new int[]{2,2});
//    test(SinglyLinkedList.toList(addLinkedListsIte(l1.head, l2.head)), new int[]{2,4});
//    l1 = new SinglyLinkedList(new int[]{5});
//    l2 = new SinglyLinkedList(new int[]{5});
//    test(SinglyLinkedList.toList(addLinkedListsIte(l1.head, l2.head)), new int[]{1,0});
     
    l1 = new SinglyLinkedList(new int[]{7,2,4,3});
    l2 = new SinglyLinkedList(new int[]{5,6,4});
    test(SinglyLinkedList.toList(addLinkedListsRec(l1.head, l2.head)), new int[]{7,8,0,7});
    l1 = new SinglyLinkedList(new int[]{2});
    l2 = new SinglyLinkedList(new int[]{2,2});
    test(SinglyLinkedList.toList(addLinkedListsRec(l1.head, l2.head)), new int[]{2,4});
    l1 = new SinglyLinkedList(new int[]{5});
    l2 = new SinglyLinkedList(new int[]{5});
    test(SinglyLinkedList.toList(addLinkedListsRec(l1.head, l2.head)), new int[]{1,0});


  }
  
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
  public static ListNode addLinkedListsIteALt2(ListNode l1, ListNode l2){
    l1 = reverseLinkedListRec2(l1);
    l2 = reverseLinkedListRec2(l2);
    int sum = 0;
    ListNode res = null, cur = null;
    while(l1!=null || l2!=null){
      int n1 = 0;
      int n2 = 0;
      if(l1!=null){
        n1 = l1.val;
        l1 = l1.next;
      }
      if(l2!=null){
        n2 = l2.val;
        l2 = l2.next;
      }
      sum = n1+n2+sum;
      ListNode n = new ListNode(sum%10);
      sum/=10;
      if(res == null){
        res = n;
      }else{
        cur.next = n;
      }
      cur = n;
    }
    if(sum>0){
      ListNode n = new ListNode(sum%10);
      cur.next = n;
    }
    return reverseLinkedListRec2(res);
  }
}
