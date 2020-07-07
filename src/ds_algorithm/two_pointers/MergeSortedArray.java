/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.two_pointers;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/merge-sorted-array/

// Time: O(n) space: O(1)
// core idea: two pointers, filling from reverse order a there is free space


public class MergeSortedArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
      int i = m-1,j = n-1, l = m+n-1;
      while(i>=0 && j>=0)
          nums1[l--] = nums1[i]>nums2[j] ? nums1[i--] : nums2[j--];     
      while(j>=0){
          nums1[l--] = nums2[j--];
      }
  }
  
//  same as avove convering the bounds in a single loop
//  if n<0 || meaning only m is left alwasy  choose from nums1 always
//  if n >0 and m<0 thenalwasy choose nums2
//  otherwise compare and choose 
  
  public void mergeAlt(int[] nums1, int m, int[] nums2, int n) {
      int i = m-1,j = n-1, l = m+n-1;
      while(l>=0)
          nums1[l--] =  j<0 || (i>=0 && nums1[i]>nums2[j]) ? nums1[i--] : nums2[j--];         
  }
  
//  same as above but in a 2 liner
  public void mergeAltOneLiner(int[] nums1, int m, int[] nums2, int n) {
      for(int l=m--+n---1;l>=0;l--)
          nums1[l] =  n<0 || (m>=0 && nums1[m]>nums2[n]) ? nums1[m--] : nums2[n--];         
  }
}
