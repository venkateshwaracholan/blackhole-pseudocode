/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.linked_list;

/**
 *
 * @author venka
 */
public class AddTwoNumbers {
    // Ite
    // Time: O(max(n,m)) space: O(max(n,m))
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
        return ans;
    }
    
    // rec
    // Time: O(max(n,m)) space: O(max(n,m))
    public ListNode addTwoNumbersRec(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1,l2,0);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if(l1==null && l2==null && carry==0){
            return null;
        }
        int val1 = l1 != null ? l1.val : 0;
        int val2 = l2 != null ? l2.val : 0;
        int sum = val1+ val2 + carry;
        carry = sum/10;
        ListNode node = new ListNode(sum%10);
        l1 = l1!=null ? l1.next: l1;
        l2 = l2!=null ? l2.next: l2;
        node.next = addTwoNumbers(l1,l2,carry);
        return node;
    }
}
