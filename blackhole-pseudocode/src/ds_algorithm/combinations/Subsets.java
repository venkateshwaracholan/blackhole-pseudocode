/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author venkateshwarans
 */

// https://leetcode.com/problems/subsets  

public class Subsets {
  public static boolean show = true;
  
  
//  Time: O(n*(2**n))  space: O(n* (2**n))where n is the length of nums
//  core Idea: recursion, backtrack, subset Pattern
//  we need 2 arraylist, one for ans and one for temp
//  call the recursion with 0
//  iterate from i to n
//  add number to list
//  call recursion
//  remove from list
//  in recursion, take a copy and put in ans(to avoid reference problem)
  
/*
  recursion tree
                   []
       [1]         [2]           [3]
   [1,2] [1,3]    [2,3]   
[1,2,3]  
  
*/
  public static List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> ans = new ArrayList();
      backtrack(0, nums, new ArrayList(), ans);
      return ans;
  }

  public static void backtrack(int k, int nums[], List<Integer> temp, List<List<Integer>> ans){
      ans.add(new ArrayList(temp));
      for(int i=k;i<nums.length;i++){
          temp.add(nums[i]);
          backtrack(i+1, nums, temp, ans);
          temp.remove(temp.size()-1);
      }
  }
  
//  same as above
  public List<List<Integer>> subsetsReturn(int[] nums) {
      return backtrackReturn(0, nums, new ArrayList(), new ArrayList());
  }

  public List<List<Integer>> backtrackReturn(int i, int nums[], List<Integer> temp, List<List<Integer>> ans){
      ans.add(new ArrayList(temp));
      for(;i<nums.length;i++){
          temp.add(nums[i]);
          backtrack(i+1, nums, temp, ans);
          temp.remove(temp.size()-1);
      }
      return ans;
  }

// same as above, using linked list  
  
  public static List<List<Integer>> subsetsAlt(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsets(nums, 0, res, new LinkedList<Integer>());
    return res;
  }
    
  public static void subsets(int[] nums, int i, List<List<Integer>> res, LinkedList<Integer> list)      {
    if (i >= nums.length) {
        //res.add(new ArrayList<Integer>(list));
        System.out.println(list);
        return;
    }
    // Current number is not part of subset
    subsets(nums, i+1, res, list);
    list.addLast(nums[i]);
    // Current number is part of subset and added to list
    subsets(nums, i+1, res, list);
    // Backtrack and remove current number to try other solutions with/without this number
    list.removeLast();
  }
  
  
//  Time: O(n*(2**n))  space: O(n* (2**n))where n is the length of nums
//  core idea: iterative, build over solution
//  for every i, the inner loops runs size of the ans times
//  copies,adds new number, and adds it back to ans
//  so ans, doubles for every i, and thus 2 loops can run over 2**n combinations

  public List<List<Integer>> subsetsIterativeBuildOverSolution(int[] nums) {
      List<List<Integer>> ans = new ArrayList();
      ans.add(new ArrayList());
      for(int i=0;i<nums.length;i++){
          int n = ans.size();
          for(int j=0;j<n;j++){
              List<Integer> temp = new ArrayList(ans.get(j));
              temp.add(nums[i]);
              ans.add(temp);
          }
      }
      return ans;
  } 
  
  
  
//  Time: O(n*(2**n))  space: O(n* (2**n))where n is the length of nums
//  core idea: iterative, bit manipulation
//  iteration bounds
//  outer loop 0 to 2**n, 1<<nums.length => 2**n
//  innner loop 0 to n
//  we are filling values where bits have 1s with nums[j]'s value
/*
  000  []
  001  [3]
  .
  .
  110  [1,2]
  111  [1,2,3]
  
  for 5 in outer loop, inner loop behaves like
  101  101  101
  001  010  100
  1<<0 1<<1 1<<2
  [1,   2,    3]
*/  
  
  public static List<List<Integer>> subsetsIterativeBitManipulation(int[] nums) {
      List<List<Integer>> ans = new ArrayList();
      int x = 1<<nums.length;
      for(int i=0;i<x;i++){
          List<Integer> temp = new ArrayList();
          for(int j=0;j<nums.length;j++){
              if((i & 1<<j)!=0){
                  temp.add(nums[j]);
              }
          }
          ans.add(temp);
      }
      return ans;
  }
  
  
  public static void main(String[] args){
    subsets(new int[]{1,2,3,4});
//    test(printCombinations(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
//    test(printCombinations(new int[]{-1,-1,-1}), -1);
//    test(printCombinations(new int[]{-2,-1}), -1);
      subsetsIterativeBitManipulation(new int[]{1,2,3});
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
