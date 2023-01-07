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

// https://leetcode.com/problems/house-robber

public class HouseRobber {
    
    //
    public int rob(int[] nums) {
        int max = 0;
        for(int i=0;i<nums.length;i++)
            max= Math.max(max,rob(nums,i));
        return max;
    }

    public int rob(int[] nums, int i) {
        if(i>=nums.length) return 0;
        int max = nums[i];
        for(int x=i+2;x<nums.length;x++)
            max = Math.max(max,nums[i]+rob(nums,x));
        return max;
    }
    
    //
    //
    public int rob2(int[] nums) {
        int max = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        for(int i=0;i<nums.length;i++)
            max= Math.max(max,rob2(nums,i,dp));
        return max;
    }

    public int rob2(int[] nums, int i, int[] dp) {
        if(i>=nums.length) return 0;
        if(dp[i]!=-1) return dp[i];
        int max = nums[i];
        for(int x=i+2;x<nums.length;x++)
            max = Math.max(max,nums[i]+rob2(nums,x,dp));
        dp[i]=max;
        return max;
    }
    
    
    //
    public int rob3(int[] nums) {
        return rob(nums,0);
    }

    public int rob3(int[] nums, int i) {
        if(i>=nums.length) return 0;
        return Math.max(rob3(nums,i+1),nums[i]+rob3(nums,i+2));
    }
    
    //
    public int rob4(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return rob4(nums,0,dp);
    }

    public int rob4(int[] nums, int i, int[] dp) {
        if(i>=nums.length) return 0;
        if(dp[i]!=-1) return dp[i];
        dp[i] = Math.max(rob4(nums,i+1,dp),nums[i]+rob4(nums,i+2,dp));
        return dp[i];
    }
    
    
    
    //
    public int rob5(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++)
            dp[i] = Math.max(dp[i-1], nums[i]+dp[i-2]);
        return dp[nums.length-1];
    }
    
    // Time: O(n) space: O(n)
    // same core idea as above with different coding style
    // interpolating dp array to hold 2 more values
    public int rob6(int[] nums) {
        int dp[] = new int[nums.length+2];
        for(int i=0;i<nums.length;i++){
            dp[i+2] = Math.max(dp[i]+nums[i], dp[i+1]);
        }
        return dp[dp.length-1]; 
    }
  
  
    // Time: O(n) space: O(n)
    // Core idea: we are scaling problem for n using dp array
    // for i<2 we cant find prev and prevprev so check help us to make it 0;
    // to find current max, find max of prevprev + cur or prev, which will result in current max
    // assign that to max that can be robbed so far
    // return the last value in the dp array;
    // 1 3 1  =>  3>1+1
    public int rob7(int[] nums) {
        int prev=0, prevprev=0;
        for(int i=0;i<nums.length;i++){
            int temp = prev;
            prev = Math.max(prev, nums[i]+prevprev);
            prevprev=temp;
        }
        return prev;
    }
  
  
}
//    1 2 3 
//1 2
//a b