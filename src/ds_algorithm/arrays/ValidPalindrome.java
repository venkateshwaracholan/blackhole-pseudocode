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


// https://leetcode.com/problems/valid-palindrome/

public class ValidPalindrome {
  public static boolean isPalindrome(String s){
    int i=0,j=s.length()-1;
    while(i<j){
      char c1 = s.charAt(i);
      char c2 = s.charAt(j);
      if(!(Character.isLetter(c1) || Character.isDigit(c1))){
        i++;continue;
      }
      if(!(Character.isLetter(c2) || Character.isDigit(c2))){
        j--;continue;
      }
      if(Character.toLowerCase(c1)!=Character.toLowerCase(c2))
        return false;
      i++;j--;
    }
    return true;
  }
  
  public static boolean isPalindrome2(String s){
    int i=0,j=s.length()-1;
    while(i<j){
      char c1 = s.charAt(i);
      char c2 = s.charAt(j);
      if(!(Character.isLetter(c1) || Character.isDigit(c1))){
        i++;
      }else if(!(Character.isLetter(c2) || Character.isDigit(c2))){
        j--;
      }else if(Character.toLowerCase(c1)!=Character.toLowerCase(c2))
        return false;
      else{
        i++;j--;
      }
    }
    return true;
  }
  
  public static void main(String args[]){
    
    System.out.println('9' + 0);
    
  }
  
  // this actually consumes more space than required by prev solution
  public boolean isPalindromespace(String s) {
    int[] charMap = new int[256];
    for (int i = 0; i < 10; i++) {
        charMap['0' + i] = 1; 
    }
    for (int i = 0; i < 26; i++) {
        charMap['a' + i] = 1;
        charMap['A' + i] = 1;
    }
    int start = 0, stop = s.length() - 1;
    while (start < stop) {
        if (charMap[s.charAt(start)] == 0) {
            start++;
        }
        else if (charMap[s.charAt(stop)] == 0) {
            stop--;
        }
        else if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(stop--))) {
            return false;
        }
    }
    return true;
}
}
