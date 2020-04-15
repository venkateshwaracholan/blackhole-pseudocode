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


// input = [0,1,2,1,0,2,1,0,2,1,0,2]
// output = [0,0,0,0,1,1,1,1,2,2,2,2]

// ~2N
// ~N
// Best Complexity = 

import java.io.*;

//allowed = [0, 2, 1]
//
//
//2**n-1  
//
//2 => 2 pointers 
//3 => 4 pointers 
//4 => 8 pointers

public class SortArraysZeroesOnesTwos {
  
  
  // 4 pointer approach
  // core idea:
  
  // TODO: have to optimize this
  // i think i got it, i need to swap i to l if i encounter a 2
  //  why am i moving it to k and then to l,
  public static void sort_ones_zeroes_twos(int nums[]){
    if(nums.length==0)
      return;
    int i=0,j=0,k=nums.length-1,l= nums.length-1;
    while(i<=k){
      if(nums[i] > nums[k]){
        swap(nums,i,k);// i++;k--;
      }
      if(nums[j]>nums[i]){
        swap(nums,i,j);
      }
      if(nums[k]>nums[l]){
        swap(nums,k,l);
      }
      if(nums[l]==2)
        l--;
      if(nums[j]==0)
        j++;
      
      if(nums[k]>nums[i]){
        i++;
        k--;
      }else if(nums[k]==nums[i])
        k--;
    }
  }
  
  public static void swap(int nums[], int i, int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
  // 1,2,2,1,2,1,0,2,0,0,2,1,1,1,2,0,2
  // 0
  // 2
  // 2
  
  // Time: O(n) space: O(1)
  // coreidea: use count of all digits
  // 0's index zerocount
  // 1's index is zerocount+onecount
  // 2's index is zerocount+onecount+twocount;
  public static void sort_ones_zeroes_twos2(int arr[]){
    int zerocount= 0 ; int onecount = 0 ; int twocount = 0;
		for(int i=0 ; i < arr.length ;i++)
		{
			switch(arr[i])
			{
			 case 0 :zerocount = zerocount+1;break;
			 case 1 : onecount =onecount+1;break;
			 case 2 : twocount=twocount+1;break;
			}
			if(zerocount > 0)
			arr[zerocount-1]= 0;
			if(onecount > 0)
			arr[zerocount + onecount -1]= 1;
			if(twocount > 0)
			arr[zerocount + onecount +  twocount-1]= 2;
		}
  }
  
  public static void main (String[] args) {
		int arr[] = new int[]{1,2,2,1,2,1,0,2,0,0,2,1,1,1,2,0,2};
    sort_ones_zeroes_twos2(arr);
    for(int i=0;i<arr.length;i++){
      System.out.print(arr[i]+",");
    }
	}
}


// 0,0,1,1,1,1,1,1,1,1,2,2,2,2
//     i               
//     k
//                   l
//     j

//  0 1 1 0 1 0 1 1
//  i             j
 
//  0 0 i++
//  0 1 i++,j--
//  1 0 swap(i,j), i++, j--
//  1 1 j--
 
 

