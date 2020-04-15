/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */
public class FirstAndSecondMaximum {
  public static void main(String args[]){
    first_second_maximum(new int[]{});
    first_second_maximum(new int[]{1});
    first_second_maximum(new int[]{1,2});
  }
  
  // 5, 10, 15
  public static void first_second_maximum(int nums[]){
    int a = Integer.MIN_VALUE, b=Integer.MIN_VALUE;
    
    for(int i=0;i<nums.length;i++){
      if(nums[i]>a){
        b = a;
        a = nums[i];
      }else if(nums[i]>b){
        b = nums[i];
      }
    }
    
    
  }
}
