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

// https://leetcode.com/problems/valid-parenthesis-string

public class ValidParanthesesWildCard {
  // Time: O(n) Space: O(1)
  // two pointer approach with validations
  public static boolean checkValidString(String s) {
      int l = s.length() - 1;
      int openCount = 0, closedCount = 0;
      for (int i = 0; i <= l; i++)
      {
          if (s.charAt(i) == '*' || s.charAt(i) == '(') openCount++;
          else openCount--;
          if (s.charAt(l - i) == '*' || s.charAt(l - i) == ')') closedCount++;
          else closedCount--;
          if (openCount < 0 || closedCount < 0) return false;
      }
      return true; 
 }
  
  // Time: O(n) Space: O(1)
  // two pointer approach with validations
  public boolean checkValidStringtwoPointer(String s) {
    int left= 0, right = 0;
    for(int i=0;i<s.length();i++){
      left += s.charAt(i)==')' ? -1 : 1; 
      right += s.charAt(s.length()-1-i)=='(' ? -1 : 1;
      if(left<0 || right<0)
        return false;
    }
    return true;
  }
  
  
}
