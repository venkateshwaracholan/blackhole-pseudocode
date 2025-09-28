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
    
    /*
    * Approach: r-anchored Binary Search for Minimum in Rotated Sorted Array
    * - Early check: if nums[l] <= nums[r], array is sorted → return nums[0].
    * - Use two pointers l and r to bound search space.
    * - While l < r:
    *     - mid = l + (r-l)/2.
    *     - If nums[mid] > nums[r], min is in right half → l = mid+1.
    *     - Else, min is in left half (including mid) → r = mid.
    * - Return nums[l] as minimum.
    *
    * Why r-based check:
    * - Keeps invariant: min is always in [l, r].
    * - Works for all cases without extra sorted-case or mid+1 checks.
    *
    * Why not l-based without extra conditions:
    * - Anchoring on nums[l] assumes left half sorted and min lies in right half.
    * - This fails for some rotated arrays where l is already in the rotated segment.
    * - Requires additional sorted-case handling and mid+1 checks → more error prone.
    *   Example: fails for [4,5,6,7,0,1,2].
    *
    * Time: O(log n), Space: O(1).
    * Examples:
    *   Best Case: sorted [1,2,3,4] → O(1).
    *   Worst Case: rotated [4,5,6,7,0,1,2] → O(log n).
    *
    * if we use mid == mid+1 check l variant can also work
    */
    public int findMin(int[] nums) {
        int l=0, r=nums.length-1;
        if(nums[l]<=nums[r]){
            return nums[0];
        }
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[r]){
                l = mid+1;
            }
            else{
                r = mid;
            }
        }
        return nums[l];
    }


    /*
    * Approach: Unsafe l <= r Binary Search with mid+1 check
    * - Early check: if nums[l] <= nums[r], array is sorted → return nums[0].
    * - Use two pointers l and r to bound search space.
    * - While l <= r:
    *     - mid = l + (r-l)/2.
    *     - If nums[mid] > nums[mid+1], nums[mid+1] is min → return nums[mid+1].
    *     - If nums[mid] > nums[r], min is in right half → l = mid+1.
    *     - Else, min is in left half including mid → r = mid.
    * - Return nums[l] as minimum.
    *
    * Why unsafe:
    * - Uses l <= r, so loop includes the case where l == r.
    * - Requires explicit mid+1 check to avoid missing pivot detection.
    * - Risk of index errors if mid+1 is not handled carefully.
    *
    * Time Complexity: O(log n) — halves search space each iteration.
    * Space Complexity: O(1) — constant extra space.
    *
    * Example:
    *   Best Case: sorted [1,2,3,4] → O(1).
    *   Worst Case: rotated [4,5,6,7,0,1,2] → O(log n).
    */
    public int findMinUnSafe(int[] nums) {
        int l=0, r=nums.length-1;
        if(nums[l]<=nums[r]){
            return nums[0];
        }
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[mid+1]){
                return nums[mid+1];
            }
            if(nums[mid]>nums[r]){
                l = mid+1;
            }
            else{
                r = mid;
            }
        }
        return nums[l];
    }

    public int findMinUnSafeLVariant(int[] nums) {
        int l=0, r=nums.length-1;
        if(nums[l]<=nums[r]){
            return nums[0];
        }
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[mid+1]) return nums[mid+1];
            if(nums[l]<nums[mid]){
                l = mid+1;
            }
            else{
                r = mid;
            }
        }
        return nums[l];
    }




}
