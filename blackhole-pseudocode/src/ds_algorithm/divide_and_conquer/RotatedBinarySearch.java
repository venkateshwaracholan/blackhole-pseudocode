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

// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class RotatedBinarySearch {
  
  
//  Time: O(log n) single pass
//  code idea: binary search
//  compare mid with left most element, if its more then left -mid is a proper sorted array 
//  check if target is in range and choose accordingly
//  if mid < left, then mid - right is a sorted array, 
//  check if target is in mid - right rage and choose accordingly
//  always remeber while comparing left and mid can be equal also as mid tends to left side
  public int search(int[] nums, int target) {
    int l = 0, r = nums.length-1;
    while(l<=r){
      int mid = l + (r-l)/2;
//      System.out.println(l+" : "+mid+" : "+r);
      if(nums[mid]==target) return mid;
      else if(nums[mid]>=nums[l])
        if(target>=nums[l] && target < nums[mid]) r = mid-1;
        else l = mid+1;
      else
        if(target > nums[mid] && target <= nums[r]) l = mid+1;
        else r = mid-1;
    } 
    return -1;   
  }
  
  
  
//  TimeO: (2logn) two pass with several conditionals
//  first we need to find the rotation index, write a func for that
//  always compare mid with left to get that idea
//  handle 0 and 1 element cases separately
//  once we knwo rot we now have 2 sorted arrays
//  if rot==0 tehn search in whole array
//  if rot has the value return rot;
//  them comare target with left(and not the) element to choose the side
//  if target is greater than left, left to rot-1
//  else rot to right
  
//  key intricasies
//  1. l<=r becoz we use mid+1 and mid-1
//  2. if we retun mid+1, we have smallerst value in rot, write code accordingly
//  3. the id tends to move towards left becoz of l + (r-l)/2;
//  so, target>= left left - mid and not target>left 
  public int find_rot_index(int nums[], int l, int r){
        if (nums[l] < nums[r])
            return 0;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid]>nums[mid+1])
                return mid+1;
            else{
                if(nums[mid]>=nums[l])
                    l=mid+1;
                else r = mid-1;
            }
        }
        return 0;    
    }
    public int binSearch(int nums[], int l, int r, int t){
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid]==t)
                return mid;
            else if(t>nums[mid])
                l = mid+1;
            else
                r = mid-1;
        }
        return -1;
    }
    
    public int searchAlt(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        if(nums.length == 0)
            return -1;
        else if ( nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int rot = find_rot_index(nums, l,r);
        //System.out.println(rot);
        if(rot==0)
            return binSearch(nums, l, r,target);
        else if(nums[rot]==target)
            return rot;
        else if(target>=nums[l])
            return binSearch(nums, l, rot-1,target);
        else
            return binSearch(nums, rot, r,target);
    }
}
