/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_sub_arrays;

/**
 *
 * @author vshanmugham
 */

/*

https://leetcode.com/problems/maximum-product-subarray/
*/

public class MaximumProductSubarray {

    // Time: O(n) space: O(1) 
    // Kadane
    // Core Idea: have a min and max
    // multiply that with number to get number, min and max
    // min = min(number, min , max)
    // max = max(number, min , max)
    // assign max in maximum if possible.
    // array with 1 value is an exception
  
    public int maxProduct(int[] nums) {
        int ans = nums[0],min=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            int nmin = min*nums[i];
            int nmax = max*nums[i];
            min = Math.min(nums[i],Math.min(nmin,nmax));
            max = Math.max(nums[i],Math.max(nmin,nmax));
            ans = Math.max(ans,max);
        }
        return ans;
    }
    
    //Approach 2: Just the slight modification of previous approach. As we know that on 
    //multiplying with negative number max will become min and min will become max, 
    //so why not as soon as we encounter negative element, we swap the max and min already.
    public int maxProduct2(int[] nums) {
        int ans = nums[0],min=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<0){
                int t=max;
                max=min;
                min=t;
            }
            min = Math.min(nums[i],nums[i]*min);
            max = Math.max(nums[i],nums[i]*max);
            ans = Math.max(ans,max);
        }
        return ans;
    }
    
    
    //
    public int maxProduct3(int[] nums) {
        int n = nums.length;
        int l=1,r=1;
        int ans=nums[0];
        for(int i=0;i<n;i++){
            l=l*nums[i];
            ans = Math.max(ans,l);
            if(l==0) l=1;
        }
        for(int i=n-1;i>=0;i--){
            r=r*nums[i];
            ans = Math.max(ans,r);
            if(r==0) r=1;
        }
        return ans;
    }
    
    //
    public int maxProduct4(int[] nums) {
        int n = nums.length;
        int l=1,r=1;
        int ans=nums[0];
        for(int i=0;i<n;i++){
            l=l*nums[i];
            r=r*nums[n-1-i];
            ans = Math.max(ans,Math.max(l,r));
            if(l==0) l=1;
            if(r==0) r=1;
        }
        return ans;
    }
  
  
  public static void main(String args[]){
//    System.out.println(maxProductKadane(new int[]{2,3,-2,4}));
//    System.out.println(maxProductKadane(new int[]{4,-2,-2,2,3,-10}));
  }
  
}
