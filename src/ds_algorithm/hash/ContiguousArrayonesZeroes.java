/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.hash;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/contiguous-array/
import java.util.*;

public class ContiguousArrayonesZeroes {
//  Time O(n*n) Space:O(1) brute
//  consider all sub array and if equal push it to max if possible.
  public int findMaxLength(int[] nums) {
    int max = 0;
    for(int i=0;i<nums.length;i++){
      int c = 0;
      for(int j=i;j<nums.length;j++){
        if(nums[j]==1)
          c++;
        else
          c--;
        if(c==0)
          max = Math.max(max, j-i+1);
      }
    }
    return max;
  }
  
//  Time O(n) Space:O(n) 
//  core idea:  hashing
//  we use a count whihc increases on 1 and decreases on 0
//  when the count value is not in map we put it(whatever the value and its index)
//  when the same value comes again there are equal numbers on 0s and 1s between 2 points
//  so we subtract i and whats in map and assignto max if possible.
//  an array with size 2n+1 can also be used as a map, for this problem
//  map.put(0,-1) is very important as it handles the case where the whole array contains equal number of zeroes and ones.
  public int findMaxLengthHash(int[] nums) {
    Map<Integer, Integer> map = new HashMap();
    int max = 0, c = 0;
    map.put(0,-1);
    for(int i=0;i<nums.length;i++){
      c += nums[i] == 0 ? -1: 1;
      if(map.containsKey(c)){
        max = Math.max(max, i - map.get(c));
      }else{
        map.put(c,i);
      }
    }
    return max;
  }
  
  
  public static void main(String args[]){
    System.out.println(-6%5);
  }
}
