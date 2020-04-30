/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.strings;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/backspace-string-compare/


import java.util.*;

public class BackspaceStringCompare {
  
// Time:O(m+n) Space:O(1) m - length of S and n = Length of T
// core idea: reverse iteration and two pointers
// when we encounter # we accumulate skip count in x and when we find other character we reduce skip count.
// until skipcount is empty and char is a non '#'
// compare char from S and T and false if doesnt match or when either of string traversal is completed before the other
  
  public static boolean backspaceCompare(String S, String T) {
    for(int i=S.length()-1, j=T.length()-1;i>=0 || j>=0;i--,j--){
      int x = 0, y = 0;
      while(i>=0){
        if(S.charAt(i)=='#'){
          x++;i--;
        }else if(x>0){
          x--;i--;
        }else
          break;
      } 
      while(j>=0){
        if(T.charAt(j)=='#'){
          y++;j--;
        }else if(y>0){
          y--;j--;
        }else
          break;
      }
      if(i>=0 && j>=0 && S.charAt(i)!=T.charAt(j))
        return false;
      
      // this line is a marvel we can also use XOR operator here
      // (i>=0 && j<0)||(j>=0 && i<0)
      // (i >= 0) != (j >= 0) 
      // (i >= 0) ^ (j >= 0)
      if ((i >= 0) != (j >= 0)) 
        return false;
    }
    return true;
  }
  
  
//  i j
//  p p   1
//  p n   0
//  n p   0
//  n n   1
  
  
  
  public String build(String S) {
    Stack<Character> ans = new Stack();
    for (char c: S.toCharArray()) {
        if (c != '#')
            ans.push(c);
        else if (!ans.empty())
            ans.pop();
    }
    return String.valueOf(ans);
  }

   public boolean backspaceComparestackALt(String S, String T) {
      return build(S).equals(build(T));
  }
   
   
   
   public boolean backspaceCompareStack(String S, String T) {
      Stack<Character> s = buildStack(S);
      Stack<Character> t = buildStack(T);
      if(s.size()!=t.size())
        return false;
      while(!s.isEmpty()){
        if(s.pop()!=t.pop())
          return false;
      }
      return true;
  }
   
  public static Stack<Character> buildStack(String S){
    Stack<Character> s = new Stack();
    for(int i=0;i<S.length();i++){
      if(S.charAt(i)!='#'){
          s.push(S.charAt(i));
      }else if(!s.isEmpty()){
          s.pop();
      }
    }
    return s;
  }
  
}
