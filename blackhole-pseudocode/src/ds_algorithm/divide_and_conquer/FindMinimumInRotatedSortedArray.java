/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/

public class FindMinimumInRotatedSortedArray {
    
    
    // Time O(logn) space:O(1)
    // left<right no rotation return nums at 0
    // while l<=r
    // if mid == mid+1 end with nums[mid+1]
    // if left<= mid(as mid tends left) rotation must be n right side
    // else left side
    public int findMin(int[] nums) {
        int l=0, r=nums.length-1;
        if(nums[l]<=nums[r]) return nums[0];
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[mid+1]) return nums[mid+1];
            if(nums[l]<=nums[mid]) l = mid+1;
            else r = mid-1; 
        }
        return nums[0];
    }
}
