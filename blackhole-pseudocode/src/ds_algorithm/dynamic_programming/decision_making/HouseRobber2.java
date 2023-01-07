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
    
    

    //
    public int rob(int[] nums) {
        if(nums.length==1)return nums[0];
        return Math.max(rob(nums,1,nums.length),rob(nums,0,nums.length-1));
    }

    public int rob(int[] nums, int i, int n) {
        if(i>=n) return 0;
        return Math.max(rob(nums,i+1,n),nums[i]+rob(nums,i+2,n));
    }
    //
    public int rob2(int[] nums) {
        int n = nums.length;
        if(n==1)return nums[0];
        int[] dp1 = new int[n],dp2 = new int[n];
        Arrays.fill(dp1,-1);
        Arrays.fill(dp2,-1);
        return Math.max(rob2(nums,1,n, dp1),rob2(nums,0,n-1, dp2));
    }

    public int rob2(int[] nums, int i, int n, int dp[]) {
        if(i>=n) return 0;
        if(dp[i]!=-1) return dp[i];
        dp[i] = Math.max(rob2(nums,i+1,n,dp),nums[i]+rob2(nums,i+2,n,dp));
        return dp[i];
    }
    
    
    
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
    
    
    //
    public int rob5(int[] nums) {
        if(nums.length==1) return nums[0];
        return Math.max(rob(nums,0,nums.length-1), rob(nums,1,nums.length));
    }

    public int rob5(int[] nums, int i,int n) {
        int prev=0, prevprev=0;
        for(;i<n;i++){
            int temp = prev;
            prev = Math.max(prev,nums[i]+prevprev);
            prevprev = temp;
        }
        return prev;
    }
    
    //
    public int rob6(int[] nums) {
        if(nums.length==1) return nums[0];
        int n = nums.length;
        int prev1=0,prevprev1=0, prev2=0,prevprev2=0;
        for(int i=0;i<n-1;i++){
            int t1 = prev1;
            prev1 = Math.max(prev1,nums[i]+prevprev1);
            prevprev1 = t1;
            int t2 = prev2;
            prev2 = Math.max(prev2,nums[i+1]+prevprev2);
            prevprev2 = t2;
        }
        return Math.max( prev1, prev2);
    }
}
