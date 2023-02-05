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
    
    //APPROACH 1 Ite + xor nums[i] and i => doing so will cancel i and nums[i] except missing number, x= nums.length coz i<nums.length how will it cancel 
     
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
    
    //APPROACH 2 find sum and subtract all numbers => sum = n*n+1/2
     
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
    
    
    //APPROACH 3 sorting and binaary search, after sorting one index wont have its value, nums[mid]>mid missing is in left side, else right side
      
    // 
    // comparing mid and nums[mid] gives the answer
    // be careful with the bounds
    public int missingNumber4(int[] nums) {
        Arrays.sort(nums);
        int l=0,r= nums.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]>mid) r=mid-1;
            else l=mid+1;
        }
        return l;
    }
    
    
    //APPROACH 4 cyclic sorting => skip if nums[i] ==n cant place, and skip if nums[i]==i already in correct pos, else place j in nums[j] by swapping, 
    //                      then ite and check whos not in place and return the,. else ret n;
    // 
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
    
    //APPROACH 5 neagtive indexing =>  x=n+1 means 0th pos as we are putting -(n+1) delib if val is zero coz zero cant be negated,  if nums[j]==0 put -(n+1) other wise just negate
    // finally  iterate and chekc if nums[i]>=0 return thats pos i, else return n finally
    
    // negative indexing
    // skipping if j==n coz out of bound
    // 3 checks for handling 0
    // 0 cannot be negated, so if nums[j] is 0 then lets sub n+1
    // if n becomes n+1 then it means its zero
    public int missingNumber6(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++){
            int x = Math.abs(nums[i]);
            if(x!=n){
                if(nums[x]==0) nums[x]=-(n+1);
                else if(x==n+1) nums[0]*=-1;
                else nums[x]*=-1;
            }
        }
        for(int i=0;i<n;i++)
            if(nums[i]>=0)
                return i;
        return n;
    }
    
}
