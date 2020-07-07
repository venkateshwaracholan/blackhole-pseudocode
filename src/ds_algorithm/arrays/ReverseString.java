/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/reverse-string/

public class ReverseString {

// Time: O(n/2) space: O(1) 
  public void reverseString(char[] s) {
      for(int i=0;i<s.length/2;i++){
          char temp = s[i];
          s[i] = s[s.length-1-i];
          s[s.length-1-i] = temp;
      }   
  }
  
// Two pointers  
  public void reverseStringTwoPointers(char[] s) {
      int l = 0, r = s.length-1;
      while(l<r){
          char temp = s[l];
          s[l++] = s[r];
          s[r--] = temp;
      }   
  }
}
