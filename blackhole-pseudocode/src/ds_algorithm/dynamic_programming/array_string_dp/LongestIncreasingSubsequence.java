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
   
    //APPROACH 1 DFS + prev(idx) + i => i==len ret 0, we either pick or not pick, first lets not pick, so passing prev
    //                        pick only if prev -1 or nums[i]>nums[prev] rteurn max of pick and not pick
    
    // 0 1 0 2 3 4
    // Time O(2**n) sapce :O(n)
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums,-1,0);
    }
    public int lengthOfLIS(int[] nums, int prev, int i) {
        if(i==nums.length) return 0;
        int notpick = lengthOfLIS(nums,prev,i+1);
        int pick =0;
        if(prev==-1||nums[i]>nums[prev])
            pick = 1+lengthOfLIS(nums,i,i+1);
        return Math.max(pick,notpick);
    }
    // Time O(n*n) sapce :O(n*n+n)
    public int lengthOfLIS2(int[] nums) {
        return lengthOfLIS(nums,-1,0, new Integer[nums.length+1][nums.length]);
    }
    public int lengthOfLIS(int[] nums, int prev, int i, Integer[][] dp) {
        if(i==nums.length) return 0;
        if(dp[prev+1][i]!=null) return dp[prev+1][i];
        int notpick = lengthOfLIS(nums,prev,i+1, dp);
        int pick =0;
        if(prev==-1||nums[i]>nums[prev])
            pick = 1+lengthOfLIS(nums,i,i+1,dp);
        return dp[prev+1][i]= Math.max(pick, notpick);
    }
    
    
    //APPROACH 2 Ite + DP int[n+1][n+1]  j==nums.length is acc max in last  => for(i=0,<=n) for(j=i+1,<=n) int x=0; formed so far is in dp[i][i] as we are traversing only from j=i+1
    //                   if(j==nums.length || nums[j]>nums[i]) j==nums.length means we are allowing for last char counting,  nums[j]>nums[i] valid inc seq
    //                                   else find y= dp[i][j] cur value and dp[i+1][j] = max(x,y)  ret dp[n][n]
    // TC = O(n^2)
    // SC = O(n^2)
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
    // use max variable if u want to avoid max accumulation in last col j<=n, j==nums.length ||, but return max+1 finally
    // implemented below search APPROACH 2 alt with max
    public int lengthOfLIS3(int[] nums) {
        int n=nums.length;
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                int x=0;
                if( j==nums.length || nums[j]>nums[i])
                    x=1+dp[i][i];
                int y = dp[i][j];
                dp[i+1][j] = Math.max(x,y);
            }
        }
        return dp[n][n];
    }
    
    //APPROACH 3 Ite + DP int[n] =>   for(i=0,n) dp[i]=1(for inc that num count) for(int j=0,i) if(nums[j]<nums[i])lets check if dp[i]<dp[j]+1 plus one 
    //                                                               coz nums[j]<nums[i] then update dp[i], ans = max(ans, dp[i])
    //1D 
    // since we cant use from prev we have to surely use max
    public int lengthOfLIS7(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int x=0,y=0;
                if( nums[j]>nums[i])
                    x=dp[i]+1;
                y = dp[j];
                dp[j] = Math.max(x,y);
                ans=Math.max(ans,dp[j]);
            }
        }
        return ans+1;
    }
    
    //APPROACH 4 Binary search + dp int[n] => dp[0] = nums[0] for(i=1,len) check last value in p with cur, if its greter place it next pos in dp else find index to place
    //                                 use buin search to fin index to place, t>dp[mid] place on right l=mid+1, else more r=mid-1, finaly return l
    
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
    public int bin(int[] dp, int len,int t){
        int l=0,r=len-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(t>dp[mid]) l=mid+1;
            else r=mid-1;
        }
        return l;
    }
    
    //APPROACH 5 easiset, same as above binary search, we are using ceiling method to get next equal or greater value, and we remove them
    //
    public int lengthOfLIS11 (int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i : nums) {
            Integer ceil = set.higher(i);
            if(null != ceil) {
                set.remove(ceil);
            }
            set.add(i);
        }
        return set.size();
    }
    
    
    
    
    
    //unwanted
    
    //APPROACH 2  alt with max
    public int lengthOfLISX (int[] nums) {
        int n=nums.length, max =0;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int x=0;
                if(nums[j]>nums[i])
                    x=1+dp[i][i];
                int y = dp[i][j];
                dp[i+1][j] = Math.max(x,y);
                max = Math.max(max, dp[i+1][j]);
            }
        }
        return max+1;
    }
    
    
    public int lengthOfLIS7asd(int[] nums) {
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
}
