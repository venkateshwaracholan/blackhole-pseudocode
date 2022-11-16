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

// https://leetcode.com/problems/maximum-sum-circular-subarray

public class MaximumSumCircularSubarray {
  
//  Time: O(n) space: O(1)
//  using kadane to find maxsub array and min subarray
//  if max<0 return that or ans is max(max, total-min)
//  the subarray in centra can be found using max,
//  the split subarray interval can be found by total - min subarray
  public int maxSubarraySumCircular(int[] A) {
    int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE, total=0, max_sofar=0, min_sofar=0;
    for(int i=0;i<A.length;i++){
        max_sofar = A[i] + Math.max(max_sofar,0);
        max = Math.max(max, max_sofar);

        min_sofar = A[i] + Math.min(min_sofar,0);
        min = Math.min(min, min_sofar);
        total+=A[i];
    }
    return max<0 ? max : Math.max(max, total-min);
  }
  
//  slight variant with starting values as A[0]
  public int maxSubarraySumCircularAlt(int[] A) {
    int max = A[0],min = A[0], total=A[0], max_sofar=A[0], min_sofar=A[0];
    for(int i=1;i<A.length;i++){
        max_sofar = A[i] + Math.max(max_sofar,0);
        max = Math.max(max, max_sofar);

        min_sofar = A[i] + Math.min(min_sofar,0);
        min = Math.min(min, min_sofar);
        total+=A[i];
    }
    return max<0 ? max : Math.max(max, total-min);
  }
  
  
//  sign variant uses -1 to compute min_subarray in negative using max function
//  the split subarray interval can be found by total + min subarray(as it is already negated)
  public int maxSubarraySumCircularSignVariant(int[] A) {
    int max = Integer.MIN_VALUE,min = Integer.MIN_VALUE, total=0, max_sofar=0, min_sofar=0;
    for(int i=0;i<A.length;i++){
        max_sofar = A[i] + Math.max(max_sofar,0);
        max = Math.max(max, max_sofar);

        min_sofar = A[i]*-1 + Math.max(min_sofar,0);
        min = Math.max(min, min_sofar);
        total+=A[i];
    }
    return max<0 ? max : Math.max(max, total + min);
  }
}
