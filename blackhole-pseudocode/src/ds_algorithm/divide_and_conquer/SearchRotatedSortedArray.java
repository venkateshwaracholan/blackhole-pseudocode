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
  

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Single-pass Modified Binary Search: Finds the sorted half and skips it if the target isn’t there, set l=0,r=n-1, while l≤r, mid= l+(r-l)/2; if nums[mid]==target return mid; if nums[l]≤nums[mid] (left sorted) → if target lies in [nums[l],nums[mid]) then r=mid-1 else l=mid+1(skip left sorted); else (right sorted) → if target lies in (nums[mid],nums[r]] then l=mid+1 else r=mid-1(skip right sorted); repeat until found or l>r, return -1 → O(log n), O(1).
    * Two-step Binary Search find rotation pivot, then binary search in correct sorted half: set l=0,r=n-1, if nums[l]≤nums[r] return binarySearch(l,r); else find rotation index rot: while l<r mid=l+(r-l)/2 → if nums[mid]>nums[r] l=mid+1 else r=mid; return l; then binarySearch in correct half: if target lies in [nums[rot],nums[r]] search [rot,r] else search [l,rot-1]; binarySearch: standard O(log n) search → O(log n), O(1).
    */


    /*
    * ONE LINER => Single-pass Modified Binary Search: Finds the sorted half and skips it if the target isn’t there, set l=0,r=n-1, while l≤r, mid= l+(r-l)/2; if nums[mid]==target return mid; if nums[l]≤nums[mid] (left sorted) → if target lies in [nums[l],nums[mid]) then r=mid-1 else l=mid+1(skip left sorted); else (right sorted) → if target lies in (nums[mid],nums[r]] then l=mid+1 else r=mid-1(skip right sorted); repeat until found or l>r, return -1 → O(log n), O(1).
    *
    * ONE LINER => 
    *
    * Approach: Single-pass Modified Binary Search
    * - Use l and r pointers to bound search space.
    * - While l <= r:
    *     - mid = l + (r-l)/2.
    *     - If nums[mid] == target → return mid.
    *     - If left half [l..mid] is sorted (nums[l] <= nums[mid]):
    *         - If target in [nums[l], nums[mid]) → search left half → r = mid-1.
    *         - Else → search right half → l = mid+1.
    *     - Else (right half [mid+1..r] is sorted):
    *         - If target in (nums[mid], nums[r]] → search right half → l = mid+1.
    *         - Else → search left half → r = mid-1.
    * - Return -1 if target not found.
    *
    * Time Complexity: O(log n) — single binary search pass.
    * Space Complexity: O(1) — constant extra space.
    *
    * Rationale:
    * - At each step, we identify one sorted half of the current range by comparing nums[l] and nums[mid].
    * - If target lies within that sorted half, we continue searching inside it; otherwise, we skip it entirely.
    * - This ensures we always discard half of the search space at every step, maintaining O(log n) complexity.
    * - This works because a rotated sorted array still contains sorted subarrays, and the target can only be in one of them.
    *
    * Example:
    *   nums = [4,5,6,7,0,1,2], target = 0 → returns index 4.
    */

    public int search(int[] nums, int target) {
        int l=0, r = nums.length-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(target==nums[mid]){
                return mid;
            }
            if(nums[l]<=nums[mid]){
                if(target>=nums[l] && target<nums[mid]){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            }
            else{
                if(target>nums[mid] && target<=nums[r]){
                    l = mid+1;
                }else{
                    r=mid-1;
                }
            }
        }
        return -1;
    }
  
    
    /*
    * ONE LINER => Two-step Binary Search find rotation pivot, then binary search in correct sorted half: set l=0,r=n-1, if nums[l]≤nums[r] return binarySearch(l,r); else find rotation index rot: while l<r mid=l+(r-l)/2 → if nums[mid]>nums[r] l=mid+1 else r=mid; return l; then binarySearch in correct half: if target lies in [nums[rot],nums[r]] search [rot,r] else search [l,rot-1]; binarySearch: standard O(log n) search → O(log n), O(1).
    *
    * Approach: Two-step Binary Search
    * 1. Find rotation index using modified binary search (findRot):
    *    - Use l and r pointers to detect pivot point where array rotation occurs.
    *    - While l < r:
    *        - mid = l + (r-l)/2.
    *        - If nums[mid] > nums[r], pivot is to the right → l = mid+1.
    *        - Else pivot is at mid or to the left → r = mid.
    *    - Return l as the rotation index.
    *
    * 2. Search target in correct sorted segment:
    *    - If target ≤ nums[r], search right segment [rot, r].
    *    - Else, search left segment [l, rot-1].
    *    - Use standard binary search in that segment.
    *
    * binarySearch:
    *  - Classic binary search in a given range [l, r].
    *  - Return index if found; else return -1.
    *
    * Time Complexity: O(log n) for findRot + O(log n) for binary search → O(log n) overall.
    * Space Complexity: O(1) — only constant extra space used.
    *
    * Rationale:
    *  - Splitting the problem into two binary searches maintains logarithmic complexity.
    *  - Efficiently leverages sorted property of rotated array segments.
    *
    * Example:
    *   nums = [4,5,6,7,0,1,2], target = 0 → returns index 4.
    */
    public int searchFindRot(int[] nums, int target) {
        int l=0, r = nums.length-1;
        if(nums[l]<=nums[r]){
            return binarySearch(nums, target, l ,r);
        }
        int rot = findRot(nums);
        if(target>=nums[rot] && target<=nums[r]){
            return binarySearch(nums, target, rot, r);
        }else{
            return binarySearch(nums, target, l, rot-1);
        }

    }

    public int binarySearch(int[] nums, int target, int l, int r){
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }else if(target<nums[mid]){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }

    public int findRot(int[] nums){
        int l=0, r = nums.length-1;
        while(l<r){
            int mid = l+ (r-l)/2;
            if(nums[mid]>nums[r]){
                l=mid+1;
            }
            else{
                r=mid;
            }
        }
        return l;
    }
}
