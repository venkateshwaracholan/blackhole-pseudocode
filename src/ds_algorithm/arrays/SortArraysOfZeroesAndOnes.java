/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import java.util.*;

/**
 *
 * @author vshanmugham
 */



//0,1,0,1,0,0,1
//
//i           j
//
//
//
//0 0 - i++
//1 1 - j--
//0 1 - i++,j--
//1 0 - swap,i++,j--



public class SortArraysOfZeroesAndOnes {
  public void sort_ones_zeroes(int nums[]){
    if(nums.length==0)
      return;
    int i=0,j=nums.length-1;
    while(i<j){
      if(nums[i]==1 && nums[j]==0){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      }
      int t = j;
      if(!(nums[i]==0 && nums[t]==0))
        t--; 
      if(!(nums[i]==1 && nums[t]==1))
        i++;
      j = t;
    }
  }
}
