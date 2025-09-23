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
   
    //APPROACH 1 brute, check until next number contains in list and add count
    // Time O(n^3) space: O(1)
    // approach: brute force
    // for everny number, check until next number is present in nums
    // for checking implement a contains method
    public int longestConsecutive(int[] nums) {
        int max = 0;
        for(int n:nums){
            int c = 1, cur = n+1;
            while(contains(nums,cur++)) c++;
            max = Math.max(max, c);
        }
        return max;
    }
    public boolean contains(int nums[], int n){
        for(int i:nums)
            if(n==i) return true;
        return false;
    }
    
    //APPROACH 2 brute optimised,use set for contains check until next number contains in list and add count, 
    // optimising above with set
    // Time O(n^2) space: O(n)
    public int longestConsecutive2(int[] nums) {
        int max = 0;
        Set<Integer> set = new HashSet();
        for(int n: nums) set.add(n);
        for(int n:nums){
            int c = 1, next = n+1;
            while(set.contains(next++)) c++;
            max = Math.max(max,c);
        }
        return max;
    }
    
    //APPROACH 3 brute optimised, skip if set contains n-1,use set for contains check until next number contains in list and add count, 
    // Time O(n) space: O(n)
    // approach: hashing
    // first add all numbers in set
    // then for everynumber check if n-1 is present and if so skip those, we will track from from the smaller number to next as sequence
    // while set contains next elem inncrease seq count, compare to max and assign
    // while can only run max of n times if all  numbers are in sequence
    // traversing set is optimal coz of duplicates in starting number of seq
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
        int max = 1,c=1;
        if(nums.length==0)return 0;
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]!=nums[i])
                if(nums[i-1]+1==nums[i]) c++;
                else c=1;
            max = Math.max(c,max);
        }
        return max;
    }
}
