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

public class SubarraySumEqualsK {
  
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
  
  
  
  static int lenOfLongSubarr(int[] arr, int n, int k) 
      { 
             // HashMap to store (sum, index) tuples 
             HashMap<Integer, Integer> map = new HashMap<>(); 
             int sum = 0, maxLen = 0; 
  
             // traverse the given array 
             for (int i = 0; i < n; i++) { 
                  
                  // accumulate sum 
                  sum += arr[i]; 
                  
                  // when subarray starts from index '0' 
                  if (sum == k) 
                      maxLen = i + 1; 
  
                  // make an entry for 'sum' if it is 
                  // not present in 'map' 
                  if (!map.containsKey(sum)) { 
                      map.put(sum, i); 
                  } 
  
                  // check if 'sum-k' is present in 'map' 
                  // or not 
                  if (map.containsKey(sum - k)) { 
                        
                      // update maxLength 
                      if (maxLen < (i - map.get(sum - k))) 
                          maxLen = i - map.get(sum - k); 
                  } 
             } 
               
             return maxLen;              
      }
  
  
  public static int atMostSum(int arr[], int n, 
                                        int k) 
    { 
        int sum = 0; 
        int cnt = 0, maxcnt = 0; 
      
        for (int i = 0; i < n; i++) { 
              
            // If adding current element doesn't 
            // cross limit add it to current window 
            if ((sum + arr[i]) <= k) { 
                sum += arr[i];  
                cnt++; 
            }  
      
            // Else, remove first element of current 
            // window. 
            else if(sum!=0) 
           { 
            sum = sum - arr[i - cnt] + arr[i]; 
           } 
      
            // keep track of max length. 
            maxcnt = Math.max(cnt, maxcnt);  
        } 
        return maxcnt; 
    }
}



