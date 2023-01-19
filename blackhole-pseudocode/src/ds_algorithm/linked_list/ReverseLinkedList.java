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

public class ReverseLinkedList {
  
    //APPROACH 1 prev+ite
    // Time O(n) space: O(1)
    // iterte with prev and cur
    // take next, remap cur.next to prev 
    // move prev and cur acc 
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
    
    //APPROACH 2 prev+rec, top down, cur=null, return prev
    // Time O(n) space: O(n) n in call stack
    // same aas above, just recursive
    public ListNode reverseList(ListNode head) {
        return reverseList(null, head);
    }
    public ListNode reverseList(ListNode prev, ListNode cur) {
        if(cur==null) return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return reverseList(cur, next);
    }
    
    //APPROACH 3 prev+rec, bottom down, cur=null, return prevs, for returning assign in temp var
    public ListNode reverseList2(ListNode head) {
        return reverseList2(head,null);
    }
    public ListNode reverseList2(ListNode head, ListNode prev) {
        if(head==null) return prev;
        ListNode x = reverseList2(head.next,head);
        head.next = prev;
        return x;
    }
    
    
    //APPROACH 4 rec, bottom down, cur==null || cur.next==null, return , cur.next.next =cur, cur.next=null
    // 1,2,3,4,5
    // Time O(n) space: O(n) n in call stack, bottom up
    // base case cur==null or nu.next==null means last node or head is null
    // keep last node in x to return that
    // to rechain cur.next.next = cur meaning remapping cur's next to point to cur itself
    // cur.next = null, useful for removing the chain at 1st node
    public ListNode reverseListRec(ListNode cur) {
        if(cur==null || cur.next==null) return cur;
        ListNode x =  reverseList(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return x;
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