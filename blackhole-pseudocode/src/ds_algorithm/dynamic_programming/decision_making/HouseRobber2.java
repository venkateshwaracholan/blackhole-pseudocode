/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.decision_making;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

// https://leetcode.com/problems/house-robber-ii/

    
public class HouseRobber2 {
    
    
    //APPROACH 1 DFS => EDGE CASE: if(n==1)return nums[0], ret max of rob(0,n-1) and rob(1,n)
    //                  rob(i=0, n) =>  i>=n ret 0, ret max of (rob(next house), nums[i] + rob(next next house))
    //
    public int rob(int[] nums) {
        if(nums.length==1)return nums[0];
        return Math.max(rob(nums,1,nums.length),rob(nums,0,nums.length-1));
    }
    public int rob(int[] nums, int i, int n) {
        if(i>=n) return 0;
        return Math.max(rob(nums,i+1,n),nums[i]+rob(nums,i+2,n));
    }
    //APPROACH 1.2 DFS + Integer[] dp =>  EDGE CASE: if(n==1)return nums[0], ret  max of rob(0,n-1) and rob(1,n)
    //                  rob(i=0, n) =>  i>=n ret 0, if(d[i]!=null) ret dp[i], ret dp[i] =  max of (rob(next house), nums[i] + rob(next next house))
    public int robMemo(int[] nums) {
        int n = nums.length;
        if(n==1)return nums[0];
        return Math.max(robMemo(nums,0,n-1,new Integer[n]), robMemo(nums,1,n,new Integer[n]));
    }
    public int robMemo(int[] nums, int i, int n, Integer[] dp) {
        if(i>=n) return 0;
        if(dp[i]!=null) return dp[i];
        return dp[i] = Math.max(robMemo(nums,i+1,n,dp),nums[i]+robMemo(nums,i+2,n,dp));
    }
    
    
    
    
    
    //APPROACH 2 Ite + Integer[] dp =>  EDGE CASE: if(n==1)return nums[0], ret  max of rob(0,n-1) and rob(1,n)
    //                  rob(i=0, n) =>  dp[i+2] = max(nums[i]+dp[i], dp[i+1]) return last dp value
    // Time: O(2n) space: O(1) 
    // the core idea still stays same as house robber 1
    // but if we ro house 1 we cant rob house n.
    // we need to run this twice without 1st house and 2nd run without house n.
    // length check for 1 is necessary, becox we are skipping 1 value in each call
    public int rob3(int[] nums) {
        if(nums.length==1) return nums[0];
        return Math.max(rob(nums,0,nums.length-1), rob(nums,1,nums.length));
    }
    public int rob3(int[] nums, int i,int n) {
        int[] dp = new int[n+2];
        for(;i<n;i++)
            dp[i+2] = Math.max(dp[i+1],nums[i]+dp[i]);
        return dp[n+1];
    }
    //APPROACH 2.2 Ite + Integer[] dp =>  EDGE CASE: if(n==1)return nums[0], fill 2 dps with single loop, dp1 and dp2, ret max( dp1[n], dp2[n+1])
    //                  dp[i+2] = max(nums[i]+dp[i], dp[i+1])  dp[i+3] = max(nums[i+1]+dp[i+1], dp[i+2])
    public int rob4(int[] nums) {
        if(nums.length==1) return nums[0];
        int n = nums.length;
        int[] dp1 = new int[n+2], dp2 = new int[n+2];
        for(int i=0;i<n-1;i++){
            dp1[i+2] = Math.max(dp1[i+1],nums[i]+dp1[i]);
            dp2[i+3] = Math.max(dp2[i+2],nums[i+1]+dp2[i+1]);
        }
        return Math.max( dp1[n], dp2[n+1]);
    }
    
    
    
    
    
    //APPROACH 3 Ite + 2 variables => EDGE CASE: if(n==1)return nums[0], ret  max of rob(0,n-1) and rob(1,n)
    //                      int n = max(nums[i]+prev, cur) prev = cur,cur =n  return cur
    
    public int rob5(int[] nums) {
        if(nums.length==1) return nums[0];
        return Math.max(rob5(nums,0,nums.length-1), rob5(nums,1,nums.length));
    }
    public int rob5(int[] nums,int i, int n) {
        int prev = 0, cur=0;
        for(;i<n;i++){
            int t = Math.max(nums[i]+prev, cur);
            prev = cur;
            cur = t;
        }
        return cur;
    } 
    
    //APPROACH 3.2 Ite + 2 variables => EDGE CASE: if(n==1)return nums[0],  get 2 ans with single loop,
    //                      int n = max(nums[i]+prev, cur) prev = cur,cur =n  return cur
    //in same loop
    public int rob6(int[] nums) {
        int n = nums.length;
        if(n==1)return nums[0];
        int p1=0, c1=0, p2=0,c2=0;
        for(int i=0;i<n-1;i++){
            int t1 = Math.max(nums[i]+p1, c1);
            p1=c1;
            c1=t1;
            int t2 = Math.max(nums[i+1]+p2, c2);
            p2=c2;
            c2=t2;
        }
        return Math.max(c1,c2);
    }
}
