package ds_algorithm.dynamic_programming.array_string_dp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/counting-bits

public class CountingBits {
  
//  Time O:(n) space: O(n)
//  core idea: DP
//  finding the last bit alone using i%2 and tehn reusing other bits from the dp array
//  we ight shift i to get x which is i stripped off last bit
  public int[] countBits(int num) {
      int dp[] = new int[num+1];
      for(int i=1;i<=num;i++){
          dp[i] = dp[i>>1];
          if(i%2==1)
              dp[i]++;
      }
      return dp;
  }
  
//  this is a short hand for the same above solution
//  looks fascinating isnt it?
  
  public int[] countBitsShort(int num) {
    int dp[] = new int[num+1];
    for(int i=1;i<=num;i++) dp[i] = dp[i>>1] + (i & 1);
    return dp;
  }
  
//  does the same as the ablove logic, but its a variant
  public int[] countBitsVariant(int num) {
      int dp[] = new int[num+1];
      for(int i=1;i<=num;i++) dp[i] = dp[i/2] + (i%2);
      return dp;
  }
  
  
//  Time O:(n) space: O(n)
//  core idea: DP
//  i & (i-1) will reset the least significant 1 => 111 & 110 = 110, 110 & 101 = 100
//  we can use that to get a number with least significant one changed to 0
//  and from teh dp we can get that bumber's bits and add 1
//  triky but, easy to understand
  public int[] countBitsVersion2(int num) {
      int dp[] = new int[num+1];
      for(int i=1;i<=num;i++) dp[i] = dp[i & (i-1)] + 1;
      return dp;
  }
  
  public static void main(String args[]){
    System.out.println(4&3);
  }
}
