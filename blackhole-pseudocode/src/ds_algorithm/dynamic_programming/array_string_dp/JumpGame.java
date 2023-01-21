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
    
    
    
    //APPROACH 1 DFS + i =>   i>=nums.length-1 reaches last index, for(j=i+1,<=i+nums[]i) starting from next to i, we can jump upto i+num[i], if rec(j) ret true Afloop ret false
    
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
        for(int j=i+nums[i];j>=i+1;j--)
          if(recursion(nums,j)) return true;
        return false;      
    }
    
    //APPROACH 1.2 DFS + i +  Boolean[len] dp=>   i>=nums.length-1 reaches last index, for(j=i+1,<=i+nums[]i) 
    //                      starting from next to i, we can jump upto i+num[i], if rec(j) ret true Afloop ret false, store dp for false cases and return if not null
    // Memo for ablove recursion
    // Time: O(n**2) space O(n) 
    //  approach: top down, recursion, memoization, dp
    public boolean canJump2(int[] nums) {
        return canJump(nums,0, new Boolean[nums.length]);
    }
    public boolean canJump(int[] nums, int i, Boolean[] dp) {
        if(i>=nums.length-1) return true;
        if(dp[i]!=null) return dp[i];
        for(int j=i+nums[i];j>=i+1;j--)
            if(canJump(nums,j,dp)) return true;
        return dp[i] = false;
    }
    
  
    //APPROACH 2 n*n Ite + new boolean[n] ==> dp[0] =true, for(i=0,n) far = min(n-1,i+nums[i]) avoiding jump over boundary, for(j=far,>=i+1) making dp[j] true wherever we can jump
    //                                                                   finally retur dp[n-1]
    
    // Time: O(n**2) space O(n) 
    // approach: bottom up iteration, dp tabulation
    // we traverse from reverse, and assign last pos dp as ans
    // [2,3,1,1,4]
    //  every node iterates nodes on its right so n**2
    //  if any of such nodes leads to anser assign dp[i] to 1
    //  finally check if first element leads to ans
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0]=true;
        for(int i=0;i<nums.length;i++){
            int far = Math.min(i+nums[i], n-1);
            for(int j=far;j>=i+1;j--){
                if(dp[i])
                    dp[j] = true;
            }
        }
        return dp[n-1];
    }
    //APPROACH 2.2 n*n Ite reverse + new boolean[n] ==> dp[n-1] =true, for(i=n-2,<=0) far = min(n-1,i+nums[i]) avoiding jump over boundary, for(j=i+1, <=far) 
    //                                                      making dp[i] true if we can jump to last and break finally retur dp[0]
    
    public boolean canJump4(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1]=true;
        for(int i=n-2;i>=0;i--){
            int far = Math.min(i+nums[i], n-1);
            for(int j=i+1;j<=far;j++){
                if(dp[j]){
                    dp[i] = true;break;
                }
            }
        }
        return dp[0];
    }

    
    //APPROACH 3 BFS(0) +  boolean[n] => i==n-1 ret true, find far=min(i+nums[i],n-1)  for(j=far,>=i+1) not visited, mark vis and add j in queue
    
    //  Iterative reperesentation of the top down recursion
    //  never try this, this is very risky.
    //  recursion and return value, the state of the stack memory everything helps a lot is several problems
    public boolean canJumpBFS(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList();
        q.add(0);
        while(!q.isEmpty()){
            int i = q.poll();
            if(i==n-1) return true;
            int far = Math.min(i+nums[i],n-1);
            for(int j=far;j>=i+1;j--)
                if(!vis[j]){
                    vis[j]=true;
                    q.add(j);
                }
        }
        return false;
    }
  
  
    //APPROACH 4 Greedy, ans=n-1
     
    //  Time: O(n) space: O(1)
    //  appoach: greedy, reverse traversal
    //  start from n-2 to 0,
    //  if i + its jump value greater than equals ans_pos
    //  update ans_pos to i, as jumping from i will also result in reaching last index
    //  if ans_pos becomes start, it has an answer, so ans_pos == 0
    public boolean canJumpGreedy(int[] nums) {
        int ans = nums.length-1;
        for(int i=nums.length-2;i>=0;i--)
            if(i+nums[i]>=ans)
                ans = i;
        return ans==0;
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
  
    
    
    //unwanted
    //  same as above, 
    //  with a small optimisation that if we go to farthers first we have a fair chance of finding solution sooner.
    //  memo for reverse iteration in rec
    //  Time: O(n**2) space O(n) 
    //  approach: top down recursion, dp memoization
    //  jumping farthest first doesnt mean that its bottom up,  only we are stopping porgram if we find solution fast
    //  jumping is still capped by i+nums[i], so top down only
    public boolean canJumpFartherstMemo(int[] nums) {
        return recursionFartherstMemo(nums, 0, new Boolean[nums.length]);
    }
    public boolean recursionFartherstMemo(int nums[], int i, Boolean dp[]){
        if(i>=nums.length-1) return true;
        int far = Math.min(i+nums[i], nums.length-1);
        for(int j = far;j>=i+1;j--){
            if(dp[j]!=null) return false;
            if(recursionFartherstMemo(nums,j,dp)) return true;
        }
        return dp[i] = false;      
    }
    
    
    
    
}
