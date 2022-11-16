/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_sub_arrays;


import java.util.*;
/**
 *
 * @author vshanmugham
 */

// https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/

/*
 
Office Design
A company is repainting its office and would like to choose colors that work well together. They are presented with several various-priced color options presented in a specific order so that each color complements the surrounding colors. The company has a limited budget and would like to select the greatest number of consecutive colors that fit within this budget. Given the prices of the colors and the company's budget, what is the maximum number of colors that can be purchased for this repainting project?

 

Example

prices = [2, 3, 5, 1, 1, 2, 1]

money = 7

 

All subarrays that sum to less than or equal to 7:

Length 1 subarrays are [2], [3], [5], [1], [1], [2], [1]

Length 2 - [2, 3], [5, 1], [1, 1], [1, 2], [2, 1]

Length 3 - [5, 1, 1], [1, 1, 2], [1, 2, 1]

Length 4 - [1, 1, 2, 1]

 

The longest of these, or the maximum number of colors that can be purchased, is 4.

*/

public class LongestSubarraySumAtmostK {
  public static int getMaxColors(List<Integer> prices, int money) {
  // Write your code here
      int sum = 0, count = 0, maxCount = 0;
      Map<Integer, Integer> map = new HashMap();
      map.put(0,1);
      for(int i=0;i<prices.size();i++){
          if(sum+prices.get(i)<= money){
              sum+=prices.get(i);
              count++;
          }else if(sum!=0){
              sum = sum - prices.get(i-count) + prices.get(i); 
          }
          maxCount = Math.max(count, maxCount);
      }
      return maxCount;
  }
}
