/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/

public class FindAllNumbersDisappeared {
    
    //Time O(n) space: O(1)
    // approach: making value of the index to negative if not already negative
    // if numbers not negative numbers corresponding to those index are missing
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int j = Math.abs(nums[i])-1;
            if(nums[j] > 0) nums[j] *=-1;
        }
        List<Integer> ans = new ArrayList();
        for(int i=0;i<nums.length;i++)
            if(nums[i]>0) ans.add(i+1);
        return ans;
    }
    
    //Time O(n) space: O(1)
    // approach: adding n to index at value
    // while accessing index just doing a mod n to get original index
    // values that are untouched will be less than or equal to n
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++) nums[(nums[i]-1)%n]+=n;
        List<Integer> ans = new ArrayList();
        for(int i=0;i<nums.length;i++) 
            if(nums[i]<=n) ans .add(i+1);
        return ans;
    }
    
    // Time O(n) space: O(1)
    // approach: cycic sorting
    // swap to proper index conditinally
    // if num is already in right pos or num's pos already has proper value skip the swap
    // be careful with indices while swapping
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1 && nums[i]!=nums[nums[i]-1]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        List<Integer> ans = new ArrayList();
        for(int i=0;i<nums.length;i++) 
            if(nums[i]!=i+1) ans .add(i+1);
        return ans;
    }
    
}
