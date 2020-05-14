/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/number-complement

public class NumberComplement {
  
//  Time: O(1) sapce: O(1) max can go upto 32 bits for integer
//  finding the number of bits using logs 
//  finding mask with -1, lets say n = 3 1000 -1 = 0111 or 111 whihc acts acts as a mask to xor
  public int findComplementMask(int num) {
      int n =(int) (Math.log(num)/Math.log(2))+1;
      int mask = (1<<n)-1;
      return num ^ mask;
  }
  
  
//  Time: O(1) sapce: O(1) max can go upto 32 bits for integer
//  use x for traversal
//  flip bit from right starting with 1 and slowly moving the bit to left and flip
//  xor num with bit and move bit left
//  right shift x until it becomes zero
//  
  public int findComplement(int num) {
      int x = num, bit =1;
      while(x!=0){
          num = num^bit;
          bit = bit<<1;
          x = x>>1;
      }
      return num;
  }
}
