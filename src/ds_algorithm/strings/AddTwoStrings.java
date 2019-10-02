/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.strings;

/**
 *
 * @author venkateshwarans
 */

/*
415. Add Strings
https://leetcode.com/problems/add-strings/

Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/

public class AddTwoStrings {
  public static boolean show = false;
  
  public static String addTwoStrings(String s1, String s2) {
        int sum = 0;
        String result = "";
        int i=s1.length()-1,j=s2.length()-1;
        for(; i>=0 || j>=0 || sum==1;i--,j--){
            int n1 = i>=0 ? Character.getNumericValue(s1.charAt(i)) : 0;
            int n2 = j>=0 ? Character.getNumericValue(s2.charAt(j)) : 0;
            sum+=n1;
            sum+=n2;
            result = sum%10+result;
            sum=sum/10;
        }
        return result;
    }
  
  public static void main(String[] args){
    test(addTwoStrings("",""), "");
    test(addTwoStrings("","0"), "0");
    test(addTwoStrings("0","0"), "0");
    test(addTwoStrings("1","0"), "1");
    test(addTwoStrings("1","9"), "10");
    test(addTwoStrings("9999","1"), "10000");
    test(addTwoStrings("1","93898"), "93899");
    
  }
  
  public static void test(String got, String exp){
    System.out.println(got.equals(exp));
    if(show || !got.equals(exp)){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
  public static String addTwoStringsAlternate(String s1,String s2){
    int carry = 0;
        String result = "";
        int i=s1.length()-1,j=s2.length()-1;
        for(; i>=0 && j>=0;i--,j--){
            int sum = Character.getNumericValue(s1.charAt(i)) + Character.getNumericValue(s2.charAt(j))+carry;
            //System.out.println(s1.charAt(i)+" : "+s2.charAt(j));
            //System.out.println(sum%10);
            carry = sum/10;
            result = sum%10+result;
        }
        while(i>=0){
            int sum = Character.getNumericValue(s1.charAt(i)) + carry;
            carry = sum/10;
            result = sum%10+result;
            i--;
        }
        while(j>=0){
            int sum = Character.getNumericValue(s2.charAt(j)) + carry;
            carry = sum/10;
            result = sum%10+result;
            j--;
        }
        if(carry==1){
           result = carry+result; 
        }
        return result;
  }
  
}
