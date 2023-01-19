/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/merge-two-sorted-lists/description/
public class MergeTwoSortedList {
    
    //APPROACH 1 rec, return other if null, if val less, node.next = rec(move that node to right), return node
    
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
    //APPROACH 1.2 rec, return other if null, if val less, node.next = rec(move that node to right), return node
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
    
    
    //APPROACH 2 ite,, use dummy node, while both not null, cur.next=node, cur=cur.next, move l1 and l2 resp, cur.next = put the remaining list at end
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode w = new ListNode();
        ListNode cur = w;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                cur.next = l1;
                l1=l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        } 
        cur.next = l1==null ? l2 : l1;
        return w.next;
    }
     //APPROACH 2.2 ite, use dummy node, while either not null, 4 checks, val is inf if null, move l1 and l2 if not null
    public ListNode mergeTwoLists4(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(list1!=null||list2!=null){
            int v1 = list1!=null?list1.val:Integer.MAX_VALUE;
            int v2 = list2!=null?list2.val:Integer.MAX_VALUE;
            if(v1<v2){
                cur.next= list1;
                list1=list1!=null?list1.next:list1;
            }else{
                cur.next= list2;
                list2=list2!=null?list2.next:list2;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    // without dummy head node
    public ListNode mergeTwoLists5(ListNode l1, ListNode l2) {
        ListNode root = null, cur = null;
        while(l1!=null || l2!=null){
            int v1 = l1!=null ? l1.val : Integer.MAX_VALUE;
            int v2 = l2!=null ? l2.val : Integer.MAX_VALUE;
            if(v1<v2){
                if(root==null) root=l1;
                else cur.next = l1;
                cur = l1;
                l1 = l1!=null ? l1.next : l1;
            }else{
                if(root==null) root=l2;
                else cur.next = l2;
                cur = l2;
                l2 = l2!=null ? l2.next : l2;
            }   
        }
        return root;
    }
    
    //if u dont understand above, read this, dont waste time
    // 
    public ListNode mergeTwoLists6(ListNode list1, ListNode list2) {
        ListNode dummy = null;
        ListNode cur = null;
        while(list1!=null||list2!=null){
            int v1 = list1!=null?list1.val:Integer.MAX_VALUE;
            int v2 = list2!=null?list2.val:Integer.MAX_VALUE;
            if(v1<v2){
                if(dummy==null) dummy=cur=list1; 
                else {
                    cur.next= list1;
                    cur=cur.next;
                }
                list1=list1!=null?list1.next:list1;
            }else{
                if(dummy==null) dummy=cur=list2; 
                else {
                    cur.next= list2;
                    cur=cur.next;
                }
                cur=list2;
                list2=list2!=null?list2.next:list2;
            }
        }
        return dummy;
    }
    
    
    public static void main(String[] args){
        System.out.println("HAHAH");
        ListNode l= new ListNode(0);
        System.out.println(l.val);
    }
}
