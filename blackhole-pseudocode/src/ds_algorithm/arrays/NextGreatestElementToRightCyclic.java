/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import java.util.*;

/**
 *
 * @author vshanmugham
 */

//https://leetcode.com/problems/next-greater-element-ii/


public class NextGreatestElementToRightCyclic {
  public int[] nextGreaterElements(int[] nums) {
      if(nums.length==0)
          return new int[0];
      int ans[] = new int[nums.length];
      Arrays.fill(ans, -1);
      Stack<Integer> s = new Stack();
      for(int i=0;i<2*nums.length;i++){  
          int j = i % nums.length;
          //ans[j] = -1;
          if(s.isEmpty()){
              s.push(j);
          }else{
              if(nums[j] >= nums[s.peek()] ){
                  while(!s.isEmpty() && nums[j] > nums[s.peek()]){
                      ans[s.pop()] = nums[j];
                  }
                  if(s.isEmpty()){
                      ans[j] = -1;
                  }
              }
              s.push(j);
          }

      }

      return ans;
  }

  public int[] nextGreaterElements2(int[] nums) {
      int[] res = new int[nums.length];
      Stack<Integer> stack = new Stack<>();
      for (int i = 2 * nums.length - 1; i >= 0; --i) {
          while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
              stack.pop();
          }
          res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
          stack.push(i % nums.length);
      }
      return res;
  }
}
