/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import ds_algorithm.Test;
import java.util.*;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/sliding-window-maximum/

public class MaximumOfAWindowK {
  
  // dequeue approach
  // using deque because we need to remove from front as well.
  // res size = n-k+1
  // we store only index in deque so that we can check if elemnts in front are removed properly when window moves.
  // Core idea: ALways first element of deque is the largest element in deque. 
  // we store element in deque if next element is samller than the current 
  // so that if cur is removed through window movement next will be next bigger.
  // Time: O(2n) space: O(K)
  public static int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> dq = new ArrayDeque();
    int n = nums.length;
    if(n==0)
      return new int[0];
    int res[] = new int[n-k+1];
    for(int i=0;i<n;i++){
      if(!dq.isEmpty() && dq.peekFirst() < i-k+1){
          dq.removeFirst();
      }
      while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]){
          dq.removeLast();
      }
      dq.add(i);
      if(i>=k-1){
          res[i-k+1] = nums[dq.peekFirst()];

      }
    }
    return res;
  }
  
  public static void main(String[] args){
    Test.test(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3), new int[]{3,3,5,5,6,7});
    Test.test(maxSlidingWindow(new int[]{1,3},1), new int[]{1,3});
    Test.test(maxSlidingWindow(new int[]{1,-1,1},2), new int[]{1,1});
    Test.test(maxSlidingWindow(new int[]{1},1), new int[]{1});
    Test.test(maxSlidingWindow(new int[]{},1), new int[]{});
  }
}
