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
import java.util.stream.IntStream;

//https://leetcode.com/problems/contains-duplicate/description/


public class ContainsDuplicate {
    
    //APPROACH 1 brute 2 loops, j=i+1
    //TLE
    // Time O(n*n) space O(1)
    public boolean containsDuplicateBrute(int[] nums) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                     return true;
                }
            }
        }
        return false;
    }
    
    //APPROACH 2 hashset
    // Time O(n) space O(n)
    //approach: hashset, check if present an add into set
    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
    
    
    //APPROACH 3 sort
    // Time O(nlogn) space:O(logn)
    // approach: sorting and checking adjacent elements
    public boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++)
            if(nums[i-1]==nums[i]) return true;
        return false;
    }
    
    //APPROACH 4 intsteam distinct count
    // using java streams // guess its Time O(n) space O(n)
    public boolean containsDuplicateStream(int[] nums) {
        return nums.length != IntStream.of(nums).distinct().count();
    }
    
    
    
}
