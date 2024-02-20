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
    
    
    
    // TLE stringuilder + find peaks k times and remove + remove leading zeroes + zero length ans check
    // Time:O(nk) space: O(n)
    public String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        while(k>0){
            int i=0;
            while(i<sb.length()-1 && sb.charAt(i)<=sb.charAt(i+1)){
                i++;
            }
            sb.deleteCharAt(i);
            k--;
        }
        while(sb.length()>1 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        return sb.length()==0 ? "0" : sb.toString();
    }
    
    // TLE  store peaks in stack + remove k times + insert in front in sb + remove leading zeroes + zero length ans check
    // Time:O(n) space: O(n)
    public String removeKdigitsStack(String num, int k) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<num.length();i++){
            char digit = num.charAt(i);
            while(!stack.isEmpty() && digit<stack.peek() && k>0){
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        while(!stack.isEmpty() && k>0){
            stack.pop();
            k--;
        }
        while(!stack.isEmpty()){
            ans.insert(0, stack.pop());
        }
        while(ans.length()>1 && ans.charAt(0)=='0'){
            ans.deleteCharAt(0);
        }
        return ans.length()==0 ? "0" : ans.toString();
    }
    
    
    
  
    //  Time O(n) space O(n)
    //  approach: deque as stack,
    //  if something small comes and k>0 we can remove elemnts from stack unitl stack has smaller element that x
    //  or k is over
    //  now a loop to remove if k is still not zero
    //  now another loop to remove leading zeroes from front
    //  now another loop to append asn in stringbuilder
    //  final check to sed "0" if empty or the ans to string
    public String removeKdigits2(String num, int k) {
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

}
