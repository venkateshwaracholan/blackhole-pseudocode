/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/missing-number/description/

// 10 answers https://leetcode.com/problems/missing-number/solutions/2082901/10-approaches-java-o-n-2-to-o-logn-self-explanatory-variables/

public class MissingNumber {
    

    // Time O(n) space: O(1)
    // nums are in range [0,n] ans anything xor itself is 0
    // so we are xoring 0 to n to x, thus starting value x = n;
    // then we xor all numbers in nums
    // so all numbers except missing number cancels itself
    public int missingNumber(int[] nums) {
        int x= nums.length;
        for(int i=0;i<nums.length;i++){
            x=x^i;
            x=x^nums[i];
        }
        return x;
    }
    
    // Time O(n) space: O(1)
    // finding total sumof n numbers, n*(n+1)/2
    // iterting and subtracting all available umbers to get missing number
    public int missingNumber2(int[] nums) {
        int x = nums.length*(nums.length+1)/2; //sum of n numbers
        for(int i=0;i<nums.length;i++)
            x-= nums[i];
        return x;
    }
    
    public int missingNumber3(int[] nums) {
        return nums.length * (nums.length + 1) / 2 - Arrays.stream(nums).sum();
    }
    
    
    // sorting and binaary search
    // comparing mid and nums[mid] gives the answer
    // be careful with the bounds
    public int missingNumber4(int[] nums) {
        Arrays.sort(nums);
        int l=0,r= nums.length;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>mid) r=mid;
            else l=mid+1;
        }
        return l;
    }
    
    // cyclic sorting
    //moving all numbers nums[i] to its position i
    // then iterating and finding who is not in their place
    public int missingNumber5(int[] nums) {
        int i=0,n=nums.length;
        while(i<n){
            if(nums[i]!=n && nums[i]!=i){
                int j = nums[i];
                nums[i] = nums[j];
                nums[j] = j;
            }else i++;
        }
        for(i=0;i<n;i++)
            if(i!=nums[i])
                return i;
        return n;
    }
    
    
    //
    // negative indexing
    // skipping if j==n coz out of bound
    // 3 checks for handling 0
    // 0 cannot be negated, so if nums[j] is 0 then lets sub n+1
    // if n becomes n+1 then it means its zero
    public int missingNumber6(int[] nums) {
        int n = nums.length;
        for (int i = 0 ; i < n ; i++) {
            int j = Math.abs(nums[i]);
            if (j != n) {
                if (j == n + 1) nums[0] = -nums[0];
                else if (nums[j] == 0) nums[j] = nums[j] - (n + 1);
                else nums[j] = -nums[j];
            }
        }
        
        for (int i = 0 ; i < n ; i++)
            if (nums[i] == 0 || nums[i] > 0)
                return i;
        return n;
    }
    
}
