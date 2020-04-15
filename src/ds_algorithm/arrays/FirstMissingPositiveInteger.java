/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import ds_algorithm.Test;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/first-missing-positive/

public class FirstMissingPositiveInteger {
  
  // with space
  // using arra as a hashmap with storing nums[i] in nums[i]-1 index 
  // in map if value in range [1,n]
  // Time: O(n) space O(n)
  public static int firstMissingPositiveSpace(int[] nums) {
    int n = nums.length;
    int map[] = new int[n];
    for(int i=0;i<n;i++){
      if(nums[i]>0 && nums[i] <= n){
        map[nums[i]-1] = nums[i];
      }
    }
    int i=0;
    for(;i<n;i++){
      if(i+1!=map[i])
        return i+1;
    }
    return i==n ? n+1 : 1;
  }
  
  // without auxiliary space
  // tring to use the same array for the map
  // idea is to move number to the proper index nums[i] to nums[i]-1 index 
  // but while moving we have to inturn move that is present at nums[i]-1 index to its proper place
  // so loop cur, copy value at nums[nums[i]-1] to next, assign to index and set next to cur 
  // until cur is already in the right place
  // Time: O(n) space O(1)
  public static int firstMissingPositiveChain(int[] nums) {
    int n = nums.length;
    for(int i=0;i<n;i++){
      int cur = nums[i];
      while(cur>0 && cur <= n && nums[cur-1]!=cur){
        int next = nums[cur-1];
        nums[cur-1] = cur;
        cur = next;
      }
    }
    for(int i=0;i<n;i++){
      if(i+1!=nums[i])
        return i+1;
    }
    return n+1;
  }
  
  public static int firstMissingPositiveNegative(int[] nums) {
    int n = nums.length;
    for(int i=0;i<n;i++){
      if(nums[i]<1 || nums[i]>n){
        nums[i] = n+1;
      }
    }
    for(int i=0;i<n;i++){
      int val = Math.abs(nums[i]);
      if( val!=n+1 && nums[val-1]>0){
        nums[val-1] = nums[val-1]*-1;
      }
    }
    for(int i=0;i<n;i++){
      if(nums[i]>0)
        return i+1;
    }
    return n+1;
  }
  // 3,4,-1,1
  
  public static void main(String[] args){
    Test.show = false;
    Test.test(firstMissingPositiveSpace(new int[]{3,4,-1,1}),2);
    Test.test(firstMissingPositiveSpace(new int[]{1,2,0}),3);
    Test.test(firstMissingPositiveSpace(new int[]{7,8,9,11,12}),1);
    Test.test(firstMissingPositiveSpace(new int[]{1}),2);
    Test.test(firstMissingPositiveSpace(new int[]{}),1);
    Test.test(firstMissingPositiveSpace(new int[]{-1}),1);
    
    Test.test(firstMissingPositiveChain(new int[]{3,4,-1,1}),2);
    Test.test(firstMissingPositiveChain(new int[]{1,2,0}),3);
    Test.test(firstMissingPositiveChain(new int[]{7,8,9,11,12}),1);
    Test.test(firstMissingPositiveChain(new int[]{1}),2);
    Test.test(firstMissingPositiveChain(new int[]{}),1);
    Test.test(firstMissingPositiveChain(new int[]{-1}),1);
    
    Test.test(firstMissingPositiveNegative(new int[]{3,4,-1,1}),2);
    Test.test(firstMissingPositiveNegative(new int[]{1,2,0}),3);
    Test.test(firstMissingPositiveNegative(new int[]{7,8,9,11,12}),1);
    Test.test(firstMissingPositiveNegative(new int[]{1}),2);
    Test.test(firstMissingPositiveNegative(new int[]{}),1);
    Test.test(firstMissingPositiveNegative(new int[]{-1}),1);
    
  }
}
