/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

import java.util.Arrays;

/**
 *
 * @author vshanmugham
 */

// https://www.careercup.com/question?id=5729761528119296

public class MatchTwoSticks {
  public static int[] solve(int n, int arr[]){
    Arrays.sort(arr);
        int i = arr.length/2;
        int j = arr.length-1;
        int count = 0;
        while(i>0){
            if(arr[i]<=arr[j]){
                count++;
                j--;
            }
            i--;
        }
        
        return new int[]{count,arr.length-(count*2)};
  } 
  
  public static void main(String args[]){
    int arr[] = solve(5, new int[]{5,4,3,2,1});
    System.out.println(arr[0]+" "+arr[1]);
  }
}
