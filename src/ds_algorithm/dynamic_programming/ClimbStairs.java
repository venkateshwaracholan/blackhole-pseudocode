/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming;

import static ds_algorithm.divide_and_conquer.BinarySearchIte.binarySearch;

/**
 *
 * @author venkateshwarans
 */

/*
  https://leetcode.com/problems/climbing-stairs/
  Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

  Note: Given n will be a positive integer.

  Example 1:

  Input: 2
  Output: 2
  Explanation: There are two ways to climb to the top.
  1. 1 step + 1 step
  2. 2 steps
  Example 2:

  Input: 3
  Output: 3
  Explanation: There are three ways to climb to the top.
  1. 1 step + 1 step + 1 step
  2. 1 step + 2 steps
  3. 2 steps + 1 step
*/

public class ClimbStairs {
  static boolean show = true;
//  public static int climbStairs(int n){
//    int prev = 0;
//    int cur = 1;
//    for(int i=0;i<n;i++){
//        int temp = cur;
//        cur = cur+prev;
//        prev = temp;
//    }
//    return cur;
//  }
  
  public static int climbStairs(int n){
    int prev = 0;
    int cur = 1;
    for(int i=1;i<=n;i++){
      int temp = prev;
      prev = cur;
      cur += temp;
    }
    return cur;
  }

  public static void main(String[] args){
    test(climbStairs(1), 1);
    test(climbStairs(2), 2);
    test(climbStairs(3), 3);
    test(climbStairs(4), 5);
    test(climbStairs(5), 8);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
