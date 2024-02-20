/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// tushar's explanation video
// https://www.youtube.com/watch?v=LPFhl65R7ww&t=1013s
  
public class MedianOfTwoSortedArrays {
  
    // Time: O(log(min(m,n))) space: O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) return findMedianSortedArrays(nums2,nums1);
        int x = nums1.length, y = nums2.length;
        int lo = 0, hi = x;
        int mid = (x+y)/2;
        while(lo<=hi){
            int partx = (hi+lo)/2;
            int party = mid - partx;
            int leftx = partx==0 ? Integer.MIN_VALUE: nums1[partx-1];
            int rightx = partx==x ? Integer.MAX_VALUE: nums1[partx];
            int lefty = party==0 ? Integer.MIN_VALUE: nums2[party-1];
            int righty = party==y ? Integer.MAX_VALUE: nums2[party];

            if(leftx<=righty && lefty<= rightx){
                if((x+y)%2==0){
                    return ((double)Math.max(leftx,lefty) + Math.min(rightx,righty))/2;
                }else{
                    return Math.min(rightx,righty);
                } 
            }else if(leftx>righty){
                hi= partx-1;
            }else{
                lo = partx+1;
            }
        }
        return 0;
    }
    
    
    
    // Time: O(m+n) space: O(1)
    public double findMedianSortedArraysLinear(int[] nums1, int[] nums2) {
        int idx1 = 0, idx2=0, med1=0, med2=0;
        int l1=nums1.length, l2=nums2.length;
        int mid = (l1+l2)/2;
        for(int i=0;i<=mid;i++){
            med1 = med2;
            if(idx1==l1){
                med2 = nums2[idx2];
                idx2++;
            }else if(idx2==l2){
                med2 = nums1[idx1];
                idx1++;
            }else if(nums1[idx1]<nums2[idx2]){
                med2 = nums1[idx1];
                idx1++;
            }else{
                med2 = nums2[idx2];
                idx2++;
            }
        }
        if((l1+l2)%2==0){
            return (double)(med1+med2)/2;
        }
        return med2;
    }
    
  //  L     R
  // 1 3   5 7  a1 
  // 2 4   6 8  a2

  public static void main(String args[]){
//    test(medianOfTwoSortedArrays(new int[]{1,2,3,4}, new int[]{5,6,7,8}),4.5); // 5,6,7   1,2,3,4
//    test(medianOfTwoSortedArrays(new int[]{1,3,5,7}, new int[]{2,4,6,8}),4.5); // 5,6,7   1,2,3,4
  }

  public static void test(double got, double exp){
    System.out.println(got == exp);
    if(got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
}
