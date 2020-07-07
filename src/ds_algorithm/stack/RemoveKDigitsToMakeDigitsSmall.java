/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.stack;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/remove-k-digits

import java.util.*;

public class RemoveKDigitsToMakeDigitsSmall {
  
//  Time O(n) space O(n)
//  approach: deque as stack,
//  if something small comes and k>0 we can remove elemnts from stack unitl stack has smaller element that x
//  or k is over
//  now a loop to remove if k is still not zero
//  now another loop to remove leading zeroes from front
//  now another loop to append asn in stringbuilder
//  final check to sed "0" if empty or the ans to string
  public String removeKdigits(String num, int k) {
      Deque<Character> dq = new ArrayDeque();
      for(int i=0;i<num.length();i++){
          char x = num.charAt(i);
          while(!dq.isEmpty() && k>0 && x<dq.peekLast()) {
              dq.pollLast();
              k--;
          }
          dq.add(x);
      }
      for(int i=0;i<k;i++) dq.pollLast();
      StringBuilder ans =new StringBuilder();
      while(!dq.isEmpty()) {
          if(dq.peek()!='0') break;
          else dq.poll();
      }
      while(!dq.isEmpty()) ans.append(dq.poll());
      return ans.length() == 0 ? "0" : ans.toString();
  }

//  Time O(n) space O(n)
//  approach: linkedlist as stack,
//  if something small comes and k>0 we can remove elemnts from stack unitl stack has smaller element that x
//  or k is over
//  now a loop to remove if k is still not zero
//  now another loop to remove leading zeroes from front with flag and append ans in stringbuilder
//  final check to sed "0" if empty or the ans to string  
  
  public String removeKdigitsLinkedList(String num, int k) {
    LinkedList<Character> stack = new LinkedList<Character>();
    for(char digit : num.toCharArray()) {
      while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
        stack.removeLast();
        k -= 1;
      }
      stack.addLast(digit);
    }
    for(int i=0; i<k; ++i) stack.removeLast();
    StringBuilder ret = new StringBuilder();
    boolean leadingZero = true;
    for(char digit: stack) {
      if(leadingZero && digit == '0') continue;
      leadingZero = false;
      ret.append(digit);
    }
    if (ret.length() == 0) return "0";
    return ret.toString();
  }
}
