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
  
  // Time: O(nlog(n)) space: O(1)
  public static double medianOfTwoSortedArraysAlt(int a1[], int a2[]){
    
    int l1 = a1.length;
    int l2  = a2.length;
    if(l1>l2){
      return medianOfTwoSortedArrays(a2,a1);
    }
    int indMin = 0, indMax = l1;
		int half = (l1 + l2) / 2;
    while (indMin <= indMax) {
			int i = (indMax + indMin) / 2;
			int j = half - i;

			int A_left = i == 0 ? Integer.MIN_VALUE : a1[i - 1];
			int A_right = i == l1 ? Integer.MAX_VALUE : a1[i];
			int B_left = j == 0 ? Integer.MIN_VALUE : a2[j - 1];
			int B_right = j == l2 ? Integer.MAX_VALUE : a2[j];

			if (A_left > B_right) {
				indMax = i - 1;
			} else if (B_left > A_right) {
				indMin = i + 1;
			} else {
				int maxLeft = Math.max(A_left, B_left);
				int minRight = Math.min(A_right, B_right);
				if ((l1 + l2) % 2 == 1) return minRight;
				return (minRight + maxLeft) / 2.0;
			}
		}
    return 0.0;
    
  }
  
  //  L     R
  // 1 3   5 7  a1 
  // 2 4   6 8  a2
  
  // Time: O(log(min(m,n))) space: O(1)
  public static double medianOfTwoSortedArrays(int a1[], int a2[]){
    int l1 = a1.length;
    int l2 = a2.length;
    if(l1<l2){
      return medianOfTwoSortedArrays(a2,a1);
    }
    int imin = 0, imax = l1;
    while(imin<=imax){
      int i = (imin+imax)/2;
      int j = (l1+l2)/2 - i;
      if(i>imin && a1[i-1]>a2[j]){
        imax = i-1;
      }else if( i<imax && a2[j-1] > a1[i]){
        imin = i+1;
      }else{ //found
        int maxLeft = 0;
        if(i==0) maxLeft = a2[j-1];
        else if(j==0) maxLeft = a1[i-1];
        else maxLeft = Math.max(a1[j-1],a2[j-1]);
        if((l1+l2)%2==1) return maxLeft;
        
        int minRight = 0;
        if(i==l1) minRight = a2[j];
        else if(j==l2) minRight = a1[i];
        else minRight = Math.min(a1[j],a2[i]);
        return (maxLeft + minRight)/2.0;   
      }
    }
    return 0.0;
  }

  public static void main(String args[]){
    test(medianOfTwoSortedArrays(new int[]{1,2,3,4}, new int[]{5,6,7,8}),4.5); // 5,6,7   1,2,3,4
    test(medianOfTwoSortedArrays(new int[]{1,3,5,7}, new int[]{2,4,6,8}),4.5); // 5,6,7   1,2,3,4
  }

  public static void test(double got, double exp){
    System.out.println(got == exp);
    if(got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
}
