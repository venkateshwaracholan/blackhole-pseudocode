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

/*

https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3289/

Given an integer array arr, count element x such that x + 1 is also in arr.

If there're duplicates in arr, count them seperately.

Example 1:

Input: arr = [1,2,3]
Output: 2
Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
Example 2:

Input: arr = [1,1,3,3,5,5,7,7]
Output: 0
Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
Example 3:

Input: arr = [1,3,2,3,5,0]
Output: 3
Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
Example 4:

Input: arr = [1,1,2,2]
Output: 2
Explanation: Two 1s are counted cause 2 is in arr.
 

Constraints:

1 <= arr.length <= 1000
0 <= arr[i] <= 1000

*/

import java.util.*;

public class CountingElements {
  
//  Time O(2n) spac: O(n)
//  a lame way of solving this, lamest is n*n lets skip it
//  two pass and a set
  public int countElements(int[] arr) {
    Set<Integer> set = new HashSet();
    for(int a: arr)
        set.add(a);
    int c = 0;
    for(int a: arr)
        if(set.contains(a+1)) c++;
    return c;
  }
  
  
  // original intuitive solution from the community
  public static int countElementsSol(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    int result = 0;
    for(int i: arr) {
      if(map.get(i-1) != null && map.get(i-1) > 0  && map.get(i) == null) {
        result += map.get(i-1);
      }  
      if(map.get(i+1) != null)
        result++;
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    return result;
  }
  
// recreated variant of the above 
  
//  Time:O(n) space O(n)
//  Intutive one pass solution to the problem
//  Core idea: put only element and its number of occurance in the map
//  if number+1 is present in map(direct case) just add the count;
//  if number-1 is present in map then make sure make sure number itself is not present in map 
//  and only for first time first time add add its frequency to result,
//  if the same number-1 appears again it will match the direct case anyway and get counted
  public static int countElementsSol2(int[] arr) {
      Map<Integer,Integer> map = new HashMap();
      int c = 0;
      for(int n:arr){
          if(map.containsKey(n-1) && !map.containsKey(n))
              c+= map.get(n-1);
          if(map.containsKey(n+1))
             c++; 
          map.put(n,map.getOrDefault(n,0)+1);
      }
      return c;
  }
//  1
//  2
  
  public static void main(String args[]){
//   int x =  countElementsSol(new int[]{1,3,2,3,5,0});
   int x =  countElementsSol(new int[]{2,2,3,3,2,2,3});
   
   System.out.println(x);
  }
}
