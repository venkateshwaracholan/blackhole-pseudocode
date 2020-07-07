/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */
/*

https://www.geeksforgeeks.org/return-a-pair-with-maximum-product-in-array-of-integers/
https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/

// [4,3,1,-8]

// 4,3
// -9-8

// 4,3
// -8,1

// [2,-2,3]
// 3,2
// -2,2

// [1,-2]
// 1,-2
// -2,1

*/




public class MaximumPoductOfTwoNumbers {
  public static void main(String args[]){
    int nega = Integer.MIN_VALUE,  
            negb = Integer.MIN_VALUE; 
    System.out.println(nega*negb);
  }
  
  public static int max_product(int a[]){
    if(a.length==0)
      return 0;
    else if (a.length==1)
      return a[0];
    else if (a.length==2)
      return a[0]*a[1];
    
    int fmax=0,smax=-1,fmin=0,smin=-1;
    for(int i=1;i<a.length;i++){
      if(a[i]>=a[fmax]){
        smax = fmax;
        fmax = i;
      }else if(smax==-1 || a[smax]==0 || a[i]>=a[smax]){
        smax = i;
      }
      if(a[i]<=a[fmin]){
        smin = fmin;
        fmin = i;
      }else if(smin==-1 || a[smin]==0 || a[i]<=a[smin]){
        smin = i;
      }
    }
    System.out.println(fmax+" "+smax);
    System.out.println(fmin+" "+smin);
    
    return Math.max(a[fmax]*a[smax], a[fmin]*a[smin]);
  }
}
