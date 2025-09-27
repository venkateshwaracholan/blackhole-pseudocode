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
  
  
    /*
    * Approach: Brute Force Multiplication
    * - For each index i:
    *     - Initialize ans[i] = 1.
    *     - Loop through every index j:
    *         - If j != i, multiply nums[j] into ans[i].
    * - Return ans after all iterations.
    *
    * Time Complexity: O(n^2) — outer loop runs n times, inner loop n times.
    * Space Complexity: O(1) extra (output array not counted).
    *
    * Rationale: Straightforward approach that directly computes the product
    * excluding self by recomputing products each time.
    * Correct but inefficient for large input; serves as a baseline for optimization.
    */
    public int[] productExceptSelfBrute(int[] nums) {
        int ans[] = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i] = 1;
            for(int j=0;j<nums.length;j++){
                if(i!=j){
                    ans[i]*=nums[j];
                }
            }
        }
        return ans;
    }
  
    /*
    * Approach: Prefix and Suffix Product (O(n), O(1) extra space)
    * - Build result array in two passes without division.
    * - Left pass:
    *     - Track running product l from the left.
    *     - ans[i] stores product of all elements before i.
    * - Right pass:
    *     - Track running product r from the right.
    *     - Multiply ans[i] with product of all elements after i.
    * - Final ans[i] = product of all nums except nums[i].
    *
    * Time Complexity: O(n) — two linear passes.
    * Space Complexity: O(1) extra — only scalars l and r, output array excluded.
    *
    * Rationale: Efficiently computes product except self in-place using prefix/suffix products,
    * avoiding O(n^2) brute force and disallowed division.
    */
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int l=1,r=1;
        ans[0]=1;
        for(int i=1;i<nums.length;i++){
            ans[i] = nums[i-1]*l;
            l = l*nums[i-1];
        }
        for(int i=nums.length-1;i>0;i--){
            ans[i-1] = ans[i-1]*nums[i]*r;
            r = r*nums[i];
        }
        return ans;
    }
    /*
    For educational purpose:
    * Difference from previous optimization:
    * - Previous version explicitly tracked both left (l) and right (r) accumulators.
    * - Here, left accumulator is skipped:
    *     - ans[] itself is used to store prefix products during the left-to-right pass
    *       (ans[i] = ans[i-1] * nums[i-1]).
    * - Right accumulator r is still required for the right-to-left pass,
    *   but initialized directly with nums[last], so each update multiplies only 2 values
    *   (ans[i-1] * r) instead of 3.
    *
    * Benefit: Slightly simpler and more efficient — avoids an extra variable for l
    * and reduces one multiplication per iteration in the right pass. 
    *  1, 2,3,4
    *  1, 1,2,6
    * 24,12,8,6
    */
    
    public int[] productExceptSelf2(int[] nums) {
        int[] ans = new int[nums.length];
        int r=1;
        ans[0]=1;
        for(int i=1;i<nums.length;i++){
            ans[i] = nums[i-1]*ans[i-1];
        }
        r = nums[nums.length-1];
        for(int i=nums.length-1;i>0;i--){
            ans[i-1] = ans[i-1]*r;
            r = r*nums[i-1];
        }
        return ans;
    }
  
  
    /*
    * Approach: Division with Zero Handling
    * - Compute:
    *     - p  = total product of all elements (will be 0 if any zero exists).
    *     - pwz = product of all non-zero elements (product without zeros).
    *     - zc = count of zeros in nums.
    * - If more than one zero exists:
    *     - Every product except self will include at least one zero → ans[] all zeros.
    * - Otherwise:
    *     - For each index i:
    *         - If nums[i] == 0 → ans[i] = pwz (only position that can be non-zero).
    *         - Else → ans[i] = p / nums[i] (safe since no division by zero).
    *
    * Time Complexity: O(n) — single scan to compute products, one more to fill result.
    * Space Complexity: O(1) extra (ans[] excluded).
    *
    * Rationale: Division-based shortcut with explicit zero handling.
    * Simpler than prefix/suffix approach, but only valid if division is allowed.
    */
    public int[] productExceptSelf3(int[] nums) {
        int[] ans = new int[nums.length];
        int p=1,pwz=1,zc=0;
        for(int n:nums){
            if(n==0){
                zc++;
            }
            else {
                pwz=pwz*n;
            }
            p=p*n;
        }
        if(zc>1){
            return ans;
        }
        for(int i=0;i<nums.length;i++){
            ans[i] = nums[i]==0 ? pwz: p/nums[i];
        }
        return ans;
    }

    /*
    * Approach: Division with Zero Handling (skip total product)
    * - Instead of computing full product p, track only:
    *     - pwz = product of all non-zero elements.
    *     - zc  = count of zeros in nums.
    * - If more than one zero → every product will include zero → ans[] all zeros.
    * - If exactly one zero → only the position with zero gets pwz, others are 0.
    * - If no zero → ans[i] = pwz / nums[i].
    *
    * Time Complexity: O(n) — one pass for counts/products, one pass to build ans.
    * Space Complexity: O(1) extra (output excluded).
    *
    * Difference from previous division approach:
    * - Avoids computing full product p (which is always 0 when zc > 0).
    * - Slightly simpler and avoids unnecessary multiplications.
    *
    * Rationale: Cleaner division-based solution with explicit zero handling,
    * though still invalid in contexts where division is disallowed.
    */
    public static int[] productExceptSelf4(int[] nums) {
        int[] ans = new int[nums.length];
        int pwz=1,zc=0;
        for(int n:nums){
            if(n==0){
                zc++;
            }
            else {
                pwz=pwz*n;
            }
        }
        if(zc>1){
            return ans;
        }
        for(int i=0;i<nums.length;i++){
            if(zc==1){
                ans[i] = nums[i]==0 ? pwz: 0;
            }
            else{
                ans[i] = pwz/nums[i];
            }
        }
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
