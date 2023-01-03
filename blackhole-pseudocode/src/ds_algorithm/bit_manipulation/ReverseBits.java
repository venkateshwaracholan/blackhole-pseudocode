/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/reverse-bits/description/

public class ReverseBits {
    
    
    // right shift n and add it to left shift x
    // x<<1 shift x to left by 1, and add the last bit
    public int reverseBits(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            x = x<<1 | (n&1);
            n=n>>1;
        }
        return x;
    }
    
    // instead of modifying n in above, we are right shifting n -> i times
    public int reverseBits(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            x = x<<1 | (n>>i&1);
        }
        return x;
    }
    
    // left shift sep and add right bit
    public int reverseBits(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            x = x<<1;
            x+= (n&1);
            n=n>>1;
        }
        return x;
    }
    
    // placing lsb starting from last position of x
    public int reverseBits(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            int lsb = n>>i&1;
            x= x | lsb<<(31-i);
        }
        return x;
    }
    
    // same as above, instead of shifting lsb, we are mulplying 2**n-i
    public int reverseBits(int n) {
        long x = 0;
        for(int i=0;i<32;i++){
            x+=  Math.pow(2,31-i)*(n>>i&1);
        }
        return (int)x;
    }
    
    
    public int reverseBits(int n) {
        return Integer.reverse(n);  
    }
}
