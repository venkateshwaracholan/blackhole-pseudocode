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

// https://leetcode.com/problems/jump-game
import java.util.*;
public class JumpGame {

// tree recursion for the problem

//                 0
//            1             2  3  4  5 
//     2      3   4   5    ...  ...  ...
//  3  4  5  45   5
// 45  5
// 5
    //APPROACH
    //  Time: O(n!) space O(n)
    //  why n factorial is becasue, take the worst case example
    //  [ 5	4	3	2	1	0	0]
    //  for every iteration, n, n-1, n-2,n-3 etc which is n!
    //  approach : back tracking, recursion, top down, brute
    //  for every node we make all the possible jumps and if we find solution we stop.
    //  so time complexity becomes n! instead of n**n, in worst case, for every node there is one less node to jump to,as jumping behind is prohibited
  
    public boolean canJump(int[] nums) {
        return recursion(nums, 0);
    }
    public boolean recursion(int nums[], int i){
        if(i>=nums.length-1) return true;
        for(int j = i+1;j<=i+nums[i];j++)
          if(recursion(nums,j)) return true;
        return false;      
    }
    // Memo for ablove recursion
    // Time: O(n**2) space O(n) 
    //  approach: top down, recursion, memoization, dp
    public boolean canJumpMemo(int[] nums) {
        return recursionMemo(nums, 0, new int[nums.length]);
    }
    public boolean recursionMemo(int nums[], int i, int dp[]){
        if(i>=nums.length-1) return true;
        for(int j = i+1;j<=i+nums[i];j++){
          if(dp[j]==-1) continue;
          if(recursionMemo(nums,j,dp)) return true;
        }
        dp[i] = -1;
        return false;      
    }  
    //  same as above, 
    //  with a small optimisation that if we go to farthers first we have a fair chance of finding solution sooner.
    //  memo for reverse iteration in rec
    //  Time: O(n**2) space O(n) 
    //  approach: top down recursion, dp memoization
    //  jumping farthest first doesnt mean that its bottom up,  only we are stopping porgram if we find solution fast
    //  jumping is still capped by i+nums[i], so top down only
    public boolean canJumpFartherstMemo(int[] nums) {
        return recursionFartherstMemo(nums, 0, new int[nums.length]);
    }
    public boolean recursionFartherstMemo(int nums[], int i, int dp[]){
        if(i>=nums.length-1) return true;
        int far = Math.min(i+nums[i], nums.length-1);
        for(int j = far;j>=i+1;j--){
            if(dp[j]==-1) return false;
            if(recursionFartherstMemo(nums,j,dp)) return true;
        }
        dp[i] = -1;
        return false;      
    }
  

    //APPROACH
    // Time: O(n**2) space O(n) 
    // approach: bottom up iteration, dp tabulation
    // we traverse from reverse, and assign last pos dp as ans
    // [2,3,1,1,4]
    //  every node iterates nodes on its right so n**2
    //  if any of such nodes leads to anser assign dp[i] to 1
    //  finally check if first element leads to ans
  
    public boolean canJumpiterativeDpBottomUp(int[] nums) {
        int dp[] = new int[nums.length];
        dp[nums.length-1] = 1;
        for(int i = nums.length-2;i>=0;i--){
            int far = Math.min(nums.length-1, i+nums[i]);
            for(int j = i+1;j<=far;j++){
                if(dp[j]==1){
                    dp[i]=1;break;
                }
            }
        }
        return dp[0]==1;
    }  

    //APPROACH
    //  Iterative reperesentation of the top down recursion
    //  never try this, this is very risky.
    //  recursion and return value, the state of the stack memory everything helps a lot is several problems
    public boolean canJumpIteTopDown(int[] nums) {
        if (nums.length == 1) return true;
        int visited[] = new int[nums.length];
        Stack<Integer> s = new Stack();
        s.add(0);
        while(!s.isEmpty()){
            int i = s.pop();
            if(i>=nums.length-1) return true;
            int far = Math.min(nums.length-1, i+nums[i]);
            for(int j = far;j>i;j--){
                if(visited[j]==0){
                    visited[j]=1;
                    s.add(j);
                }
            }
        }
        return false;
    }
  
  
    //APPROACH
    //  Time: O(n) space: O(1)
    //  appoach: greedy, reverse traversal
    //  start from n-2 to 0,
    //  if i + its jump value greater than equals ans_pos
    //  update ans_pos to i, as jumping from i will also result in reaching last index
    //  if ans_pos becomes start, it has an answer, so ans_pos == 0
    public boolean canJumpGreedy(int[] nums) {
        int ans_pos = nums.length-1;
        for(int i=nums.length-2;i>=0;i--){
            if(i+nums[i]>=ans_pos)
                ans_pos = i;
        }
        return ans_pos==0;
    }
    // iterative forward greedy
    public boolean canJump2(int[] nums) {
        int reachable = 0;
        for(int i=0;i<nums.length;i++){
            if(i>reachable) return false;
            reachable = Math.max(reachable, i+nums[i]);
        }
        return true;
    }
  
  
    public static void main(String args[]){
        boolean x[] = new boolean[10];
        System.out.println(x[0]);
    }
  
}
