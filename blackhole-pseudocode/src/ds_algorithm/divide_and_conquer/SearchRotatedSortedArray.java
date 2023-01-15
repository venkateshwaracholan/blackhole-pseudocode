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

public class SearchRotatedSortedArray {
  
  
    //  Time: O(log n) single pass
    //  code idea: binary search
    //  compare mid with left most element, if its more then left->mid is a proper sorted array 
    //  check if target is in range and choose accordingly
    //  if mid < left, then mid - right is a sorted array, 
    //  check if target is in mid - right rage and choose accordingly
    //  always remeber while comparing left and mid can be equal also as mid tends to left side
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(target==nums[mid]) return mid;
            if(nums[l]<=nums[mid])
                if(target>=nums[l]&&target<nums[mid]) r = mid-1;
                else l = mid+1;
            else
                if(target>nums[mid] && target<=nums[r]) l = mid+1;
                else r = mid-1;
        }
        return -1;
    }
  
    // [4,5,6,7,0,1,2] 
    // [6,7,0,1,2,4,5] 

    //  TimeO: (2logn) two pass with several conditionals
    //  first we need to find the rotation index, write a func for that
    //  always compare mid with left to get that idea
    //  once we knw rot we now have 2 sorted arrays
    //  if k==0  search in whole array
    //  if k has the value return k;
    //  them comare target with left(and not the) element to choose the side
    //  if target is greater than left, left to k-1
    //  else k to right

    //  key intricasies
    //  1. l<=r becoz we use mid+1 and mid-1
    //  2. if we retun mid+1, we have smallerst value in k, write code accordingly
    //  3. the mid tends to move towards left becoz of l + (r-l)/2;
    //  so, target>= left left - mid
    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        int k = rot(nums, l, r);
        if(k==0) return binarySearch(nums, l ,r, target);
        else if(target>=nums[l]) return binarySearch(nums, l, k-1, target);
        else return binarySearch(nums, k, r, target);
    }

    public int rot(int[] nums, int l , int r){
        if(nums[l]<=nums[r]) return 0;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[mid+1]) return mid+1;
            if(nums[l]<=nums[mid]) l = mid+1;
            else r = mid-1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int l, int r, int target){
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]==target) return mid;
            if(target<nums[mid]) r = mid-1;
            else l= mid+1;
        }
        return -1;
    }
}
