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
   
    //APPROACH
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
    
    
    //APPROACH
    // TC = O(n^2)
    // SC = O(n^2)
            /*  
[0,1,0,3,2,3]    
4000000
3300000
3220000
2222000
2222000
1111010
0000000
    */
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
    /*
[0,1,0,3,2,3]  
0000000
0101111
0002222
0002222
0000223
0000033
0000004
    */
    //
    public int lengthOfLIS4_2(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        for(int i=0;i<=nums.length;i++){
            for(int next=i+1;next<=nums.length;next++){
                int x=0;
                if(next>=nums.length||nums[i]<nums[next]) 
                    x = 1+dp[i][i];
                int y = dp[i][next];
                dp[i+1][next] = Math.max(x,y);
            }
        }
        return dp[nums.length][nums.length];
    }
    
    //APPROACH
    //1D
    public int lengthOfLIS7(int[] nums) {
        int[] dp = new int[nums.length];
        int ans=0;
        for(int i=0;i<nums.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i] && dp[i]<dp[j]+1)
                    dp[i] = dp[j]+1;
            }
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
    
    //APPROACH
    public int lengthOfLIS8(int[] nums) {
        List<Integer> ans = new ArrayList();
        ans.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            if(nums[i]>ans.get(ans.size()-1))
                ans.add(nums[i]);
            else{
                int x =bin(ans,nums[i]);
                ans.set(x,nums[i]);
            }
        }
        return ans.size();
    }

    public int bin(List<Integer> list,int t){
        int l=0,r=list.size();
        while(l!=r){
            int mid = l+(r-l)/2;
            if(t>list.get(mid)) l=mid+1;
            else r=mid;
        }
        return l;
    }
    //
    public int lengthOfLIS9(int[] nums) {
        int[] dp =new int[nums.length];
        dp[0]=nums[0];
        int len=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>dp[len])
                dp[++len]=nums[i];
            else{
                int x =bin(dp,len,nums[i]);
                dp[x]=nums[i];
            }
        }
        return len+1;
    }
    public int bin2(int[] dp, int len,int t){
        int l=0,r=len;
        while(l!=r){
            int mid = l+(r-l)/2;
            if(t>dp[mid]) l=mid+1;
            else r=mid;
        }
        return l;
    }
    
    //APPROACH
    //
    public int lengthOfLIS11 (int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i : nums) {
            Integer ceil = set.ceiling(i);
            if(null != ceil) {
                set.remove(ceil);
            }
            set.add(i);
        }
        return set.size();
    }
    
}
