/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/single-element-in-a-sorted-array/
// ASKED BY ARAVIND RAVINDRAN IN FW

public class SingleElementInSortedArray {
  
  
//  Time O(logn) sapce O(1)
//  approach: binary search, make answer fall on l index
//  so bounds will be l<r and not l<=r, if l<=r we have to return from inside the loop

//  special case: based on mid is even and odd we need to change its position and move accordingly
//  moving mid left for odd mid pos, if equal move l to mid+2 else r = mid 
//  simulate and see in example
  public int singleNonDuplicate(int[] nums) {
      int l = 0,r = nums.length-1;
      while(l<r){
          int mid = l + (r-l)/2;
          if(mid%2==1) mid--;
          if(nums[mid]==nums[mid+1]) l = mid+2;
          else r= mid;
      }
      return nums[l];
  }
  
// 1,1,2,3,3,4,4,8,8
// 0,1,2,3,4,5,6,7,8
// 1,1,3,3,4,4,8,8,9
//     m  
  
  
//  Time O(logn) sapce O(1)
//  approach: binary search, make answer fall on l index
//  so bounds will be l<r and not l<=r, if l<=r we have to return from inside the loop

//  special case: based on mid is even and odd we need to change its position and move accordingly
//  moving mid left for even mid pos, if equal move r to mid-1 else l = mid +1 
//  simulate and see in example
  public int singleNonDuplicateIntuitive(int[] nums) {
      int l = 0,r = nums.length-1;
      while(l<r){
          int mid = l + (r-l)/2;
          if(mid%2==0) mid--;
          if(nums[mid]==nums[mid+1]) r= mid-1;
          else  l = mid+1;
      }
      return nums[l];
  }
}
