/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import com.google.gson.Gson;

/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/product-of-array-except-self/submissions/


// [2, 3, 4, 5]

public class ProductOfArraysExceptSelf {
  static boolean show = true;
  
  
    // Approach Brute
    public int[] productExceptSelfBrute(int[] nums) {
        int ans[] = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i] = 1;
            for(int j=0;j<nums.length;j++){
                if(i!=j)
                    ans[i]*=nums[j];
            }
        }
        return ans;
    }
  
    //APPROACH 1 left right iteration and acc of product
    // to every node accumulate of product of values upto its previous index from left to right.
    // do the same from right to left to get the result.
    // left acc is without involving ans, coz itz like init of ans, ans[0] is set starting from 1, starting from 1 hints usage of nums[i-1]
    // right acc is with ans[i] so tat we get to final ans, right acc start from number before last, hits usage of nums[i+1]
    // Time: O(n) space: O(n) but result space is not counted as per leetcode.
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0]=1;
        int l=1,r = 1;
        for(int i=1;i<nums.length;i++){
            ans[i] = nums[i-1]*l;
            l=l*nums[i-1];
        }
        for(int i=nums.length-2;i>=0;i--){
            ans[i] = ans[i]*nums[i+1]*r;
            r=r*nums[i+1];
        }
        return ans;
    }
    // to every node accumulate of product of values upto its previous index from left to right.
    // do the same from right to left to get the result.
    // optimizing further to assign r initially,so right acc is lead rather than lag, so +1 is removed from index
    //  1, 2,3,4
    //  1, 1,2,6
    // 24,12,8,6
    public int[] productExceptSelf2(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0]=1;
        for(int i=1;i<nums.length;i++)
            ans[i] = nums[i-1]*ans[i-1];
        int r = nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--){
            ans[i] = ans[i]*r;
            r=r*nums[i];
        }
        return ans;
    }
  
  
  //  1 ,2, 6,18 
  //  18,6, 3, #
  
  //  18 6  3  3
  
    //APPROACH 2 division, zero count, product and product without zero
  
    // calculate overall product and then divide with self.
    // this problem has 2 edge cases
    // 1. with 1 zero which can be solved with prodWithoutZero.
    // 2. two more zeroes - in this case output is always a zero array.
    // Time: O(n) space: O(1) using division.
    public int[] productExceptSelf3(int[] nums) {
        int p = 1, pwz = 1,zc=0, x=0;
        for(int n:nums){
            if(n==0) zc++;
            else pwz*=n;
            p*=n;
        }
        if(zc>1)return new int[nums.length];
        for(int n:nums) 
            nums[x++] = n==0 ? pwz : p/n;
        return nums;
    }
  
    public static int[] productExceptSelf4(int[] nums) {
        int pwz = 1,zc=0, x=0, ans[] = new int[nums.length];
        for(int n:nums) 
            if(n==0) zc++;
            else pwz*=n;
        if(zc>1)return ans;
        for(int n:nums) 
            if(zc==1) ans[x++] = n==0 ? pwz : 0;
            else ans[x++] = pwz/n;
        return ans;
    }
  
  

  
  public static void main(String[] args){
   test(productExceptSelf4(new int[]{2, 3, 4, 5}), new int[]{2,2,6,24});
   test(productExceptSelf4(new int[]{}), new int[]{});
   test(productExceptSelf4(new int[]{1}), new int[]{1});
   test(productExceptSelf4(new int[]{1,2}), new int[]{1,1});
  }
  
  public static void test(int[] got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    if(show || !gotStr.equals(expStr)){
      System.out.println("got     : "+gson.toJson(gotStr));
      System.out.println("expected: "+gson.toJson(expStr));
    }
  }
}
