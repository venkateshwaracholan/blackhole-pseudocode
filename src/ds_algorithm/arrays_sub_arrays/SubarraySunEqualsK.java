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

// https://leetcode.com/problems/subarray-sum-equals-k/
import java.util.*;

public class SubarraySunEqualsK {
  
//  Time: O(n) space: O(n)
//  use hashmap to store the sum until i and its frequency
//  if sum-k exists it means we can add that frequency to ans
//  remember sum or sum-k can only repeat in presence of a 0 or a negative number
//  otherwise it increases steadily
  public int subarraySum(int[] nums, int k) {
    int sum = 0,ans = 0;
    Map<Integer,Integer> map = new HashMap();
    map.put(0,1);
    for(int i=0;i<nums.length;i++){
        sum+=nums[i];
        if(map.containsKey(sum-k))
            ans+=map.get(sum-k);
        map.put(sum, map.getOrDefault(sum,0)+1);
    }
    return ans;
  }
}
