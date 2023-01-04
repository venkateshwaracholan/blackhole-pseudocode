/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.two_pointers;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/3sum/description/

public class ThreeSum {
    
    // Time O(n^3logk+nlogn) SC: O(K) - k is the number of unique triplets
    // brute force
    // even brute force requires set as we need only uniq triplets
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0)
                        set.add(Arrays.asList(nums[i],nums[j],nums[k]));
                }
            }
        }
        return new ArrayList(set);
    }
    
    // TC: O(n * logn) + O(n^2 * logk) => O(n^2 * logk)
    // SC: O(K) - k is the number of unique triplets
    // Optimizing to the brute force (using HashSet) - TLE:
    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> ans = new HashSet();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            Set<Integer> set = new HashSet();
            for(int j=i+1;j<nums.length;j++){
                int target = -1*(nums[i]+nums[j]);
                if(set.contains(target))
                    ans.add(Arrays.asList(nums[i],nums[j],target));
                set.add(nums[j]);
            }
        }
        return new ArrayList(ans);
    }
    
    // TC: O(n ^ 2), SC: O(k) - k is the number of unique triplets
    // Auxiliary space: O(1)
    // approach: two pointer with skips for equal values, skipping at 3 places, i ,lo, hi
    // to move lo and hi if next/prev value is same,we refer with whats in triplet to avoid temp variables
    // if totl sum<0 move lo to right else move hi to left
    // loop stopped with len -2 coz u cant make a tripletwith 2 nums
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i==0 || nums[i]!=nums[i-1]){
                int lo = i+1,hi = nums.length-1;
                while(lo<hi){
                    if((nums[i]+nums[lo]+nums[hi])==0){
                        List<Integer> triplet = Arrays.asList(nums[i],nums[lo],nums[hi]);
                        ans.add(triplet);
                        while(lo<hi && nums[lo]==triplet.get(1))lo++;
                        while(lo<hi && nums[hi]==triplet.get(2))hi--;
                    }
                    else if(nums[i]+nums[lo]+nums[hi]<0)lo++;
                    else hi--;
                }
            }
        }
        return new ArrayList(ans);
    }
}
