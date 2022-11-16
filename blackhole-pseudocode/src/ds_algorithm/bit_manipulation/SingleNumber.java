/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author venkateshwarans
 */

/*
  https://leetcode.com/problems/single-number/
  136. Single Number
  Easy

  Share
  Given a non-empty array of integers, every element appears twice except for one. Find that single one.

  Note:

  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

  Example 1:

  Input: [2,2,1]
  Output: 1
  Example 2:

  Input: [4,1,2,1,2]
  Output: 4
*/


public class SingleNumber {
  static boolean show = true;
  
  // Time: O(n) space: O(1)
  public static int singleNumber(int[] nums) {
    int xor = 0;
    for(int i=0;i<nums.length;i++){
       xor = xor^nums[i]; 
    }
    return xor;
  }

  public static void main(String[] args){
    test(singleNumber(new int[]{1,2,2}), 1);
    test(singleNumber(new int[]{4,1,2,1,2}), 4);
    test(singleNumber(new int[]{1,5,4,6,4,1,6}), 5);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
