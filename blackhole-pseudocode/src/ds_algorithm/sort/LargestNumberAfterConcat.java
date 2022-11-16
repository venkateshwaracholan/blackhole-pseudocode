/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

/**
 *
 * @author vshanmugham
 */

// approach: comparator
// https://leetcode.com/problems/largest-number/

// use bubble sort to demonstrate this algorithm and then try with comparator.
// Maximum number merge
import java.util.*;

public class LargestNumberAfterConcat {
  
  
  public String largestNumber(int[] nums) {
      String arr[] = new String[nums.length];
      for(int i=0;i<nums.length;i++) arr[i] = String.valueOf(nums[i]);
      Arrays.sort(arr,new NumberComparator());
      if(arr[0].equals("0")) return "0";
      String ans ="";
      for(String a:arr) ans+=a;
      return ans;
  }

  class NumberComparator implements Comparator<String>{
      public int compare(String a, String b){
          String x = a+b, y = b+a;
          return y.compareTo(x);
      }
  }
}







