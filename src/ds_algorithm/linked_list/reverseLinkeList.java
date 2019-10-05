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
public class reverseLinkeList {
  
  public static ListNode reverseLinkedList(ListNode n){
    ListNode cur = n, prev = null;
    while(cur!=null){
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }
  // 1,2,3,4,5
  public static ListNode reverseLinkedListRec(ListNode cur){
    if(cur.next==null){
      return cur;
    }
    ListNode res = reverseLinkedListRec(cur.next);
    cur.next.next = cur;
    cur.next = null;
    return res;
  }
  
  public static ListNode reverseLinkedListRec2(ListNode cur){
    return reverseLinkedListRec2(null, cur);
  }
  
  public static ListNode reverseLinkedListRec2(ListNode prev, ListNode cur){
    if(cur==null){
      return prev;
    }
    ListNode next = cur.next;
    cur.next = prev;
    return reverseLinkedListRec2(cur, next);
  }
  
  public static void main(String args[]){
    SinglyLinkedList list;
    list = new SinglyLinkedList(new int[]{1,2,3,4,5});
    test(SinglyLinkedList.toList(reverseLinkedList(list.head)), new int[]{1,2,3,4,5});
    list = new SinglyLinkedList(new int[]{2,4});
    test(SinglyLinkedList.toList(reverseLinkedList(list.head)), new int[]{2,4});
    
    list = new SinglyLinkedList(new int[]{1,2,3,4,5});
    test(SinglyLinkedList.toList(reverseLinkedListRec(list.head)), new int[]{1,2,3,4,5});
    list = new SinglyLinkedList(new int[]{2,4});
    test(SinglyLinkedList.toList(reverseLinkedListRec(list.head)), new int[]{2,4});
    
    
    list = new SinglyLinkedList(new int[]{1,2,3,4,5});
    test(SinglyLinkedList.toList(reverseLinkedListRec2(list.head)), new int[]{1,2,3,4,5});
    list = new SinglyLinkedList(new int[]{2,4});
    test(SinglyLinkedList.toList(reverseLinkedListRec2(list.head)), new int[]{2,4});


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
