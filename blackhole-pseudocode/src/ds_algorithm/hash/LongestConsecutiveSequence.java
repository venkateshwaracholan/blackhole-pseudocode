/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.hash;

/**
 *
 * @author venka
 */

import java.util.*;

// https://leetcode.com/problems/longest-consecutive-sequence/description/

public class LongestConsecutiveSequence {
   
    /*
    * Approach 1 (Brute Force):
    * - For each number, keep checking if next consecutive number exists in the array.
    * - Use a linear search (contains) to check presence of each next number.
    * - Count the length of each sequence and track the maximum.
    *
    * Time:  O(n^3) → outer loop O(n) × inner while O(n) × linear contains O(n).
    * Space: O(1) → no extra data structures used.
    */
    public int longestConsecutive(int[] nums) {
        int max = 0;
        for(int n:nums){
            int c=1, cur = n+1;
            while(contains(nums, cur++)){
                c++;
            }
            max = Math.max(max, c);
        }
        return max;
    }
    public boolean contains(int[] nums, int x){
        for(int n: nums){
            if(x==n){
                return true;
            }
        }
        return false;
    }
    
    /*
    * Approach 2 (Brute Force Optimized with HashSet):
    * - Add all numbers to a HashSet for O(1) contains checks.
    * - For each number, extend the sequence while the set contains the next number.
    * - Count the length of each sequence and track the maximum.
    * - Still iterates over all numbers, but contains check is faster than linear search.
    *
    * Time:  O(n^2) → outer loop O(n) × while loop (can be up to O(n) in worst case).
    * Space: O(n) → HashSet stores all numbers.
    */

    public int longestConsecutive2(int[] nums) {
        int max = 0;
        var set = new HashSet<Integer>();
        for(int n:nums){
            set.add(n);
        }
        for(int n:nums){
            int c=1, cur = n+1;
            while(set.contains(cur++)){
                c++;
            }
            max = Math.max(max, c);
        }
        return max;
    }
    
    /*
    * Approach 3 (HashSet optimization):
    * - Add all numbers to a set for O(1) contains check.
    * - Traverse the set (not array) to avoid duplicate work from repeated nums.
    * - For each number, only start if (n-1) not in set → ensures sequence start.
    * - Extend sequence forward while set contains next, track length, update max.
    * - Each number participates in at most one sequence walk.
    *
    * Time:  O(n) → insert all + at most one full pass per number.
    * Space: O(n) → HashSet stores all unique numbers.
    */

    public int longestConsecutiveHash(int[] nums) {
        int max = 0;
        Set<Integer> set = new HashSet();
        for(int n: nums) set.add(n);
        for(int n:set){
            if(set.contains(n-1)) continue;
            int c = 1, cur = n+1;
            while(set.contains(cur++)) c++;
            max = Math.max(max, c);
        }
        return max;
    }
    
    //APPROACH 4 sort and count numbers, skip if same
    // Time O(nlogn) space O(logn)
    // sorting solution
    // skip check if 2 numbers are same 0,1,1,2
    // if num eq prev sc++ else reset c to 1
    public int longestConsecutiveSort(int[] nums) {
        int max = 1, c=1;
        if(nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]==nums[i]){
                continue;
            }
            if(nums[i]==nums[i-1]+1){
                c++;
                max = Math.max(c, max);
            }
            else{
                c=1;
            }
        }
        return max;
    }
}
