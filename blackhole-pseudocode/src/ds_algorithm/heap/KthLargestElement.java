/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

public class KthLargestElement {
  
//  Time: O(n+k) Space: O(n)
//  core idea: add everything to max heap and then poll k times
  public int findKthLargest(int[] nums, int k) {
    Queue<Integer> q = new PriorityQueue<>((a,b) -> (b-a));
    for(int n: nums)
        q.add(n);
    while(!q.isEmpty() && k>1){
        q.poll();
        k--;
    }
    return q.poll();
  }
  
//  Time:O(n) space: O(k)
//  core idea, use min heap and firt element is the kth largest element
//  push only to k elements
//  if elements reach k, check if its greater than current and then remove first and add back;
  public int findKthLargestSpaceEfficient(int[] nums, int k) {
    Queue<Integer> q = new PriorityQueue<>((a,b) -> (a-b));
    for(int i=0;i<nums.length;i++){
      if(q.size()<k){
        q.add(nums[i]);
      }else if(nums[i]>q.peek()){
        q.poll();
        q.add(nums[i]);
      }
    }
    return q.poll();
  }
  
//  Time:O(n) space: O(k+1)
//  core idea, use min heap and firt element is the kth largest element
//  push elements to heap
//  if elements become more than k, remove the first one.
//  so we use K+1 space
  public int findKthLargestBest(int[] nums, int k) {
    Queue<Integer> q = new PriorityQueue<>((a,b) -> (a-b));
    for(int i=0;i<nums.length;i++){
        q.add(nums[i]);
        if(q.size()>k)
            q.poll();
    }
    return q.poll();
  }
  
  
  public int findKthLargestQuickSelect(int[] nums, int k) {
      return quickSelect(nums, k, 0, nums.length - 1);
  }

  int quickSelect(int[] nums, int k, int start, int end) {
      if (start == end) return nums[start];
      int left = start;
      int right = end;
      int pivot = nums[start + (end - start) / 2];
      while (left < right) {
          while (left <= right && nums[left] > pivot) left++;
          while (left <= right && nums[right] < pivot) right--;
          if (left <= right) {
              int temp = nums[left];
              nums[left++] = nums[right];
              nums[right--] = temp;                
          }
      }

      if (start + k - 1 <= right) {
          return quickSelect(nums, k, start, right);
      }

      if (start + k - 1 >= left) {
          return quickSelect(nums, k - (left - start),  left, end);
      }

      return nums[right + 1];
  }
}
