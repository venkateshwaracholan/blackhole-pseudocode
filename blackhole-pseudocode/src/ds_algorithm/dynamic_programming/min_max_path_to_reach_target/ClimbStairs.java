/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.min_max_path_to_reach_target;

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
   
    //APPROACH
    //use this solution
    // reverse
    public int climbStairs1(int n) {
        if(n<0) return 0;
        if(n==0) return 1;
        return  climbStairs1(n-1)+climbStairs1(n-2);
    }
    // reverse dp
    public int climbStairs1DP(int n) {
        return  climbStairs1DP(n,new int[n+1]);
    }
    public int climbStairs1DP(int n,int[] dp) {
        if(n<0) return 0;
        if(n==0) return 1;
        if(dp[n]!=0) return dp[n];
        dp[n] = climbStairs1DP(n-1,dp)+climbStairs1DP(n-2,dp);
        return dp[n];
    }
    
    
    //APPROACH
    // iterative 1d
    public int climbStairs2(int n) {
        int[] dp =new int[n+2];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    //APPROACH
    // without dp 
    public static int climbStairs3(int n){
        int prev = 0;
        int cur = 1;
        for(int i=1;i<=n;i++){
          int temp = prev;
          prev = cur;
          cur += temp;
        }
        return cur;
    }
    
    
    
    
    
    
    
    
    
    
    
    //alt approaches, just for learning dont uses
    // forward
    // same as above with memo dp
    public int climbStairsMemo(int n) {
        int[] dp = new int[n];
        return climbStairsMemo(n,1,dp)+ climbStairsMemo(n,2,dp);
    }
    public int climbStairsMemo(int n, int i,int[]dp) {
        if(i>n) return 0;
        if(i==n) return 1;
        if(dp[i]!=0) return dp[i];
        dp[i] = climbStairsMemo(n,i+1,dp)+ climbStairsMemo(n,i+2,dp);
        return dp[i];
    }
    
    
    // reverse dp, with 1 and 2 values hardcoded
    public int climbStairs5(int n) {
        int[] dp =new int[n+2];
        dp[1]=1;
        dp[2]=2;
        return  climbStairs5(n,dp);
    }
    public int climbStairs5(int n,int[] dp) {
        if(dp[n]!=0) return dp[n];
        dp[n] = climbStairs5(n-1,dp)+climbStairs5(n-2,dp);
        return dp[n];
    }

    public static void main(String[] args){
    }

    public static void test(int got, int exp){
        System.out.println(got == exp);
        if(show || got != exp){
          System.out.println("got     : "+got);
          System.out.println("expected: "+exp);
        }
    }
}
