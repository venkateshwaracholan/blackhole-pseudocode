/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/longest-increasing-subsequence/description/

public class LongestIncreasingSubsequence {
   
    // Time O(2**n) sapce :O(n)
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums,-1,0);
    }

    public int lengthOfLIS(int[] nums, int prev, int i) {
        if(i==nums.length) return 0;
        int x = 0;
        if(prev==-1||nums[prev]<nums[i])
            x = 1+lengthOfLIS(nums,i,i+1);
        int y = lengthOfLIS(nums,prev,i+1);
        return Math.max(x,y);
    }
    
    // Time O(n*n) sapce :O(n*n+n)
    public int lengthOfLIS2(int[] nums) {
        return lengthOfLIS(nums,-1,0, new Integer[nums.length][nums.length]);
    }

    public int lengthOfLIS(int[] nums, int prev, int i, Integer[][] dp) {
        if(i==nums.length) return 0;
        if(prev!=-1&&dp[prev][i]!=null) return dp[prev][i];
        int x = 0;
        if(prev==-1||nums[prev]<nums[i])
            x = 1+lengthOfLIS(nums,i,i+1,dp);
        int y = lengthOfLIS(nums,prev,i+1,dp);
        if(prev!=-1) dp[prev][i] = Math.max(x,y);
        return Math.max(x,y);
    }
    
    // Time O(n*n) sapce :O(n*n+n)
    public int lengthOfLIS3(int[] nums) {
        int[][] dp = new int[nums.length][nums.length]; 
        for(int[] i: dp) Arrays.fill(i,-1);
        return lengthOfLIS3(nums,-1,0, dp);
    }
    public int lengthOfLIS3(int[] nums,int prev,int i, int[][] dp) {
        if(i==nums.length) return 0;
        int x=0;
        if(prev!=-1&&dp[prev][i]!=-1) return dp[prev][i];
        if(prev==-1||nums[prev]<nums[i])
            x = 1+lengthOfLIS3(nums,i,i+1,dp);
        int y= lengthOfLIS3(nums,prev,i+1,dp);
        if(prev!=-1) dp[prev][i] = Math.max(x,y);
        return Math.max(x,y);
    }
    
    // TC = O(n^2)
    // SC = O(n^2)
    public int lengthOfLIS4(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        for(int i=nums.length-1;i>=0;i--){
            for(int prev=i-1;prev>=-1;prev--){
                int x=0;
                if(prev==-1||nums[prev]<nums[i]) 
                    x = 1+dp[i+1][i+1];
                int y = dp[i+1][prev+1];
                dp[i][prev+1] = Math.max(x,y);
            }
        }
        return dp[0][0];
    }
    /*     0123
        0  3000
        1  2200
        2  1110
        3  0000

    */
    
    //
    public int lengthOfLIS5(int[] nums) {
        int[] pre = new int[nums.length+1];
        for(int i=nums.length-1;i>=0;i--){
            int[] cur = new int[nums.length+1];
            for(int prev=i-1;prev>=-1;prev--){
                int x=0;
                if(prev==-1||nums[prev]<nums[i]) 
                    x = 1+pre[i+1];
                int y = pre[prev+1];
                cur[prev+1] = Math.max(x,y);
            }
            pre = cur;
        }
        return pre[0];
    }
    
    
    //
    public int lengthOfLIS6(int[] nums) {
        int[] dp = new int[nums.length];
        int ans=0;
        for(int i=0;i<nums.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i])
                    max= Math.max(max,dp[j]);
            }
            dp[i]=max+1;
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
    
}
