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
    
    //APPROACH 1  Ite until 32 => x=0, leftshift x and add last bit (n&1)  using  or |, then right shift n=n>>1
    
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
    //APPROACH 1.2  Ite until 32 => x=0, leftshift x separately and add last bit (n&1) using + on next line, then right shift n=n>>1
    
    // left shift sep and add right bit
    public int reverseBits3(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            x = x<<1;
            x+= (n&1);
            n=n>>1;
        }
        return x;
    }
    //APPROACH 1.2  Ite until 32 => x=0, leftshift x separately and add last bit (n&1) using + on next line, instead of n=n>>1 use (n>>i)&1
    // instead of modifying n in above, we are right shifting n -> i times
    public int reverseBits2(int n) {
        int x=0;
        for(int i=0;i<32;i++)
            x=x<<1 | (n>>i)&1;
        return x;
    }
    
    
    
    
    //APPROACH 2 => placing bit in the right pos i x without shifting x itself get t = (n>>i)&1 bit  x= | t<<(31-i)
    // placing lsb starting from last position of x
    public int reverseBits4(int n) {
        int x = 0;
        for(int i=0;i<32;i++){
            int t = n>>i&1;
            x= x | t<<(31-i);
        }
        return x;
    }
    

    
    //APPROACH 3 using java
    public int reverseBits6(int n) {
        return Integer.reverse(n);  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //unwanted
    //APPROACH 2.2 =>  get t = (n>>i)&1 bit  x+= 2**n-1*(t) 
    // same as above, instead of shifting lsb, we are mulplying 2**n-i
    public int reverseBits5(int n) {
        long x = 0;
        for(int i=0;i<32;i++){
            x+=  Math.pow(2,31-i)*(n>>i&1);
        }
        return (int)x;
    }
    
    
}
