/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.linked_list;

import com.google.gson.Gson;
import static ds_algorithm.linked_list.ReverseLinkedList.*;
import ds_algorithm.utils.ArrayUtils;
import java.util.ArrayList;

/**
 *
 * @author venkateshwarans
 */
public class AddTwoNumbers2 {
    public ListNode reverseListRec(ListNode cur) {
        if(cur==null || cur.next==null) return cur;
        ListNode x =  reverseListRec(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return x;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseListRec(l1);
        l2 = reverseListRec(l2);
        ListNode ans = null,temp  = null;
        int carry = 0;
        while(l1!=null||l2!=null){
            int val1 = l1!=null? l1.val: 0;
            int val2 = l2!=null? l2.val: 0;
            l1 = l1!=null ? l1.next: l1;
            l2 = l2!=null ? l2.next: l2;
            int sum = val1+val2+carry;
            ListNode digit = new ListNode(sum%10);
            carry = sum/10;
            if(ans==null){
                ans = temp = digit;
            }else{
                temp.next = digit;
                temp = digit;
            }
        }
        if(carry>0){
            temp.next = new ListNode(1);
        }
        return reverseListRec(ans);
    }
  
    public ListNode addLinkedListsIte(ListNode l1, ListNode l2){
        l1 = reverseListRec(l1);
        l2 = reverseListRec(l2);
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
        return reverseListRec(res.next);
    }
  
    //recursive
    public ListNode addTwoNumbersRec(ListNode l1, ListNode l2){
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
    public ListNode addLinkedListsRec(ListNode l1, ListNode l2, int len1,int len2){
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
  
    
  
  public static void main(String args[]){

  }
  // 7,2,4,3
  //   5,6,4
  
  // 7,8,0,7
   
  
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
  
}
