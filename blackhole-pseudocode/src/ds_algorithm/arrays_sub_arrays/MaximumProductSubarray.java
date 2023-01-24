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

    //APPROACH 1 Ite + max, localmax, localmin, tempmax, tempmin => max=lmin=lmax = nums[0] for(i=1,len) tmin = nums[i]*min, tmax = nums[i]*max
    //                      lmin = min(nums[i],tmin,tmax) (coz anythng can change with mul neg ints) lmax = max(nums[i],tmin,tmax) fnally acc max=max(max,lmax)
    
    
    // Time: O(n) space: O(1) 
    // Kadane
    // Core Idea: have a min and max
    // multiply that with number to get number, min and max
    // min = min(number, min , max)
    // max = max(number, min , max)
    // assign max in maximum if possible.
    // array with 1 value is an exception
  
    public int maxProduct(int[] nums) {
        int max=nums[0], lmin=nums[0],lmax=nums[0];
        for(int i=1;i<nums.length;i++){
            int tmin = nums[i]*lmin;
            int tmax = nums[i]*lmax;
            lmin = Math.min(nums[i], Math.min(tmin, tmax));
            lmax = Math.max(nums[i], Math.max(tmin, tmax));
            max = Math.max(max,lmax);
        }
        return max;
    }
    //APPROACH 1.2 Ite + max, localmax, localmin + swap for negative => max=lmin=lmax = nums[0] for(i=1,len) if num[i] is neg swap lmin and lmax, as max and min are going to swap too
    //                      lmin = min(nums[i],nums[i]*lmin) lmax = max(nums[i],nums[i]*lmax) fnally acc max=max(max,lmax)
    
    //multiplying with negative number max will become min and min will become max, 
    //so why not as soon as we encounter negative element, we swap the max and min already.
    public int maxProduct2(int[] nums) {
        int max=nums[0], lmin=nums[0],lmax=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<0){
                int t=lmin;
                lmin = lmax;
                lmax = t;
            }
            lmin = Math.min(nums[i], nums[i]*lmin);
            lmax = Math.max(nums[i], nums[i]*lmax);
            max = Math.max(max,lmax);
        }
        return max;
    }
    
    //APPROACH 2 Left Ite right Ite + l=r=1, max=nums[0] => for(i=0,n) mul and acc l*=nums[i] if l becomes 0, l=1 (resetting product incase of zero) 
    //                      do the sam for right from reverse assign max in both l and r loop
    
   
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
    //APPROACH 2.2 left right Ite in single loop + l=r=1, max=nums[0]  => for(i=0,n) mul and acc l*=nums[i] if l becomes 0, l=1 (resetting product incase of zero) 
    //                                               r=r*=nums[n-1-i] as we are acc from right
    
   
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
