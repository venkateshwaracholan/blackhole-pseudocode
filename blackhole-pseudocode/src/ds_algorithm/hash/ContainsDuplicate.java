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

//https://leetcode.com/problems/contains-duplicate/description/


public class ContainsDuplicate {
    
    
    // Time O(n) space O(n)
    //approach: hashset, check if present an add into set
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
    
    
    // Time O(nlogn) space:O(logn)
    // approach: sorting and checking adjacent elements
    public boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++)
            if(nums[i-1]==nums[i]) return true;
        return false;
    }
    
    
    // using java streams // guess its Time O(n) space O(n)
    public boolean containsDuplicateStream(int[] nums) {
        return nums.length != IntStream.of(nums).distinct().count();
    }
    
    
    
}
