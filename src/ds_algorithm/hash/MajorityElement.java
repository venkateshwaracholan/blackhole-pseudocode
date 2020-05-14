package ds_algorithm.hash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/majority-element/
import java.util.*;

public class MajorityElement {
  
//  Time: O(n) sapce: O(n)
//  aproach: freq hashing
//  two variables, to store and and max freq to compare and assign
  public int majorityElement(int[] nums) {
      Map<Integer, Integer> map = new HashMap();
      int max_c = 0, ans = nums[0];
      for(int n: nums){
          map.put(n, map.getOrDefault(n,0)+1);
          if(map.get(n)>max_c){
              max_c =map.get(n);
              ans= n;
          }    
      }
      return ans;
  }
  
//  Time: O(n) sapce: O(1)
//  aproach: voting algorithm
//  since more than n/2 element are going to be the elemnt of max freq
//  if the next element is same icrement or decrement in different
//  finally elements less than n/2 cancel with max el or other el and make max freq el ad the candidate when loop ends
  
  public int majorityElementBoyreMooreVoting(int[] nums) {
        int count = 0,candidate = 0;
        for(int n: nums){
            if(count==0) candidate = n;
            count += (n==candidate) ? 1: -1;
        }
        return candidate;
    }
  
}
