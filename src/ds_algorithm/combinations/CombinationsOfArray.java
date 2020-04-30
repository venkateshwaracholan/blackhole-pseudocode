/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author venkateshwarans
 */
public class CombinationsOfArray {
  public static boolean show = true;
  
//  public static void printCombinations(int arr[]){
//    int ans[] = new int[arr.length];
//    ans[0] = arr[0];
//    combRec(ans ,arr, 0);
//  }
  
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsets(nums, 0, res, new LinkedList<Integer>());
    return res;
  }
    
  public static void subsets(int[] nums, int i, List<List<Integer>> res, LinkedList<Integer> list)      {
    if (i >= nums.length) {
        //res.add(new ArrayList<Integer>(list));
        System.out.println(list);
        return;
    }
    // Current number is not part of subset
    subsets(nums, i+1, res, list);
    list.addLast(nums[i]);
    // Current number is part of subset and added to list
    subsets(nums, i+1, res, list);

    // Backtrack and remove current number to try other solutions with/without this number
    list.removeLast();
  }
  
  public static void main(String[] args){
    subsets(new int[]{1,2,3,4});
//    test(printCombinations(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
//    test(printCombinations(new int[]{-1,-1,-1}), -1);
//    test(printCombinations(new int[]{-2,-1}), -1);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
