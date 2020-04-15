package ds_algorithm.arrays;

import com.google.gson.Gson;
import ds_algorithm.Test;
import ds_algorithm.utils.ArrayUtils;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/remove-element/

public class RemoveElement {
  
  // Time: O(n) space: O(1) 
  // copying val to right
  public static int removeElement(int[] nums, int val){
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
  }
  
  
  // Time: O(n) space: O(1) 
  // copying number to left
  public static int removeElement2(int[] nums, int val){
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
  }
  
  public static int[] newArr(int[] nums, int index){
    int newArr[] = new int[index];
    for(int i=0;i<index;i++){
      newArr[i] = nums[i];
    }
    return newArr;
  }
  
  public static void main(String[] args){
    
    int a[] = new int[]{1,1,1,1,1};
    a = new int[]{1,1,1,1,1};
    Test.test(newArr(a, removeElement(a, 1)), new int[]{});
    
    a = new int[]{0,1,2,2,3,0,4,2};
    Test.test(newArr(a, removeElement(a, 2)), new int[]{0,1,3,0,4});
    
    a = new int[]{3,2,2,3};
    Test.test(newArr(a, removeElement(a, 3)), new int[]{2,2});
    
    a = new int[]{};
    Test.test(newArr(a, removeElement(a, 2)), new int[]{});
    
    a = new int[]{1,1,1,1,1};
    Test.test(newArr(a, removeElement(a, 2)), new int[]{1,1,1,1,1});
    
    a = new int[]{1};
    Test.test(newArr(a, removeElement(a, 1)), new int[]{});
    
    
    a = new int[]{1,1,1,1,1};
    Test.test(newArr(a, removeElement2(a, 1)), new int[]{});
    
    a = new int[]{0,1,2,2,3,0,4,2};
    Test.test(newArr(a, removeElement2(a, 2)), new int[]{0,1,3,0,4});
    
    a = new int[]{3,2,2,3};
    Test.test(newArr(a, removeElement2(a, 3)), new int[]{2,2});
    
    a = new int[]{};
    Test.test(newArr(a, removeElement2(a, 2)), new int[]{});
    
    a = new int[]{1,1,1,1,1};
    Test.test(newArr(a, removeElement2(a, 2)), new int[]{1,1,1,1,1});
    
    a = new int[]{1};
    Test.test(newArr(a, removeElement2(a, 1)), new int[]{});
    
    
  }
  
}
