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
    // even brute force requires set ans sort as we need only uniq triplets
    // TLE
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
    // Optimizing to the brute force (using HashSet):
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
    // if totl sum<0 move lo to right else move hi to left
    // and if sum is zero store tiplet in var ans add to ans
    // we can use value in triplet to skip same values for l and r refering to triplet 
    // -6,1,1,1,1,5,5,5,5
    // -6,1,5  we have to skip all 1s and all 5s so we need to compare l and r from triplet, and move until new value is found, also array is sorted
    // loop stopped with len -2 coz u cant make a tripletwith 2 nums
    public List<List<Integer>> threeSum3(int[] nums) {
        Set<List<Integer>> ans = new HashSet();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i==0||nums[i]!=nums[i-1]){
                int l=i+1,r=nums.length-1;
                while(l<r){
                    int sum  = nums[i]+nums[l]+nums[r];
                    if(sum<0) l++;
                    else if (sum>0) r--;
                    else {
                        List<Integer> trip = Arrays.asList(nums[i],nums[l++],nums[r--]);
                        ans.add(trip);
                        while(l<r&&nums[l]==trip.get(1)) l++;
                        while(l<r&&nums[r]==trip.get(2)) r--;
                    }
                }
            }
        }
        return new ArrayList(ans);
    }
    
    // approach: two pointer with skips for equal values, skipping at 3 places, i ,lo, hi 
    // if totl sum<0 move lo to right else move hi to left
    // and if sum is zero add to and and move both l anr r as we dont want that combo again
    // but both next values can be same but set takes care of it
    // loop stopped with len -2 coz u cant make a tripletwith 2 nums
    // -6,1,1,1,1,5,5,5,5
    // -6,1,5, we just do l++ and r--, so -6,1,5 comes again and again and set takes care
    public List<List<Integer>> threeSum4(int[] nums) {
        Set<List<Integer>> ans = new HashSet();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i==0||nums[i]!=nums[i-1]){
                int l=i+1,r=nums.length-1;
                while(l<r){
                    int sum  = nums[i]+nums[l]+nums[r];
                    if(sum<0) l++;
                    else if (sum>0) r--;
                    else ans.add(Arrays.asList(nums[i],nums[l++],nums[r--]));
                }
            }
        }
        return new ArrayList(ans);
    }
}
