/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.combinations;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/subsets-ii/

import java.util.*;

public class Subsets2 {
  
  
//  Time: O(n*(2**n) + nlogn)  space: O(n* (2**n))where n is the length of nums  
//  core idea: recursion, backtracking, subset Pattern
//  NOTE: Sorting is necessary so that equal values are adjacent to each other
//  so, sort nums first
//  2 arrayist one for temp and one for ans
//  in the for loop inside rec, int i=k instead of using k directy as in subsets1
//  so that we can avoid calling recursion only when i!=k
//  when i == k we need to allow once to include that combination as well. 1,2,2
//  rest are same as subset1
  
//  i==k case means its passed through recursion
//  1!=k or i>k means its called from iteration in loop inside recursion
  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
      return backtrack(0, nums, new LinkedList(), new ArrayList());
  }

  public static List<List<Integer>> backtrack(int k, int nums[], LinkedList<Integer> temp, List<List<Integer>> ans){
      ans.add(new ArrayList(temp));
      for(int i=k;i<nums.length;i++){
          if(i!=k && nums[i]==nums[i-1]){
              continue;
          }
          temp.add(nums[i]);
          backtrack(i+1, nums, temp, ans);
          temp.removeLast();
      }
      return ans;
  }
  
  public static void main(String args[]){
    
    
    subsetsWithDup(new int[]{1,2,2});
  }
}
