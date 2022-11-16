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

// https://leetcode.com/problems/bitwise-and-of-numbers-range/

public class BitwiseAndOfNumberRange {
  
//  Time: O(k) k bits in n space O(1)
//  approach: bit manipulation
//  why it works - common prefix
//  if there is no common prefix ans is simple 0
//  so we shift bits to right until both are 1 or both are 0, otherwise 0;
//  and count the shift so that we can left shift that many times and return
  public int rangeBitwiseAnd(int m, int n) {
    int shift = 0;
    while(m<n){ // while(m!=n) should also work
        m = m>>1;
        n = n>>1;
        shift++;
    }
    return n<<shift;// n==0 ? 0: n<<shift; this should also work
  }
  
//  Time: O(k) k bits in n space O(1) but even lesser iterations
//  core Idea: n>m so we are resetting each bit from right 
//  using n and n-1  -   111 & 110 = 110, 110 & 101 = 100
//  do this repeately until n less than n and thn m&n should give us only common prefix bit
  public int rangeBitwiseAndesetRightBits(int m, int n) {
    while(m<n) n  = n&(n-1);
    return n&m;
  }
  
}
