package ds_algorithm.hash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/majority-element/
import java.util.*;

public class MajorityElement {
  
    //  Time: O(n) sapce: O(n)
    //  aproach: freq hashing
    //  two variables, to store and and max freq to compare and assign
    //  freq > n/2 return that element, reteurn watever at end as solution exists 
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap();
        for(int i =0;i<nums.length;i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            if(map.get(nums[i])>nums.length/2) return nums[i];
        }
        return -1;
    }
  
    //  Time: O(n) sapce: O(1)
    //  aproach: Bayer Moores voting algorithm
    //  since more than n/2 element are going to be the elemnt of max freq
    //  if the next element is same icrement or decrement in different
    //  finally elements less than n/2 cancel with max el or other el and make max freq el ad the candidate when loop ends
  
    public int majorityElementBayerMoore(int[] nums) {
        int candidate = 0, count = 0;
        for(int num: nums){
            if(count==0) candidate=num;
            count+= (candidate==num) ? 1 :-1;
        }
        return candidate;
    }
    
    //  Time: O(nlogn) sapce: O(1ogn) quick sort primitives
    //  since majority elem exist, maj elem dominates in even cases like 1,2,2,2 and will surely 
    // exist in middle for odds 1,2,2  1,1,2  1,2,2,2,3
    // so after sort ans is in n/2
    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
  
}
