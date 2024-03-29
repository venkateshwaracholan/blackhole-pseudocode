/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/number-of-1-bits/description/

public class NumberOf1Bits {
    
    //APPROACH 1 Ite 32 bits + c => for(i=0,32) c+=n&1 comparing last digit and increments only if last dig is 1, then moving n right by 1  n=n>>1
    
    // Time O(n) space: O(1) n number of bits
    // right shift operator removes last bit
    // checking 1&n will give us if last bit is set or not
    public int hammingWeight(int n) {
        int c = 0;
        for(int i=0;i<32;i++){
            c+=n&1;
            n=n>>1;
        }
        return c;
    }
    //APPROACH 1.1 Ite n!=0 + c => while(n!=0) c+=n&1 comparing last digit and increments only if last dig is 1, then moving n right by 1  n=n>>>1 
    //                              with unsigned right shift as there willl be negatives
   
    // O(n) n- num of set bits
    // n&1 will give us if last bit is set
    // we have to right shift, but this time no bounds like 32
    // so, negatives can also come in like -3 stored in 2's comp  1111...1101 (1's comp+1)-> 111...1100+1 ->111...1101 
    public static int hammingWeight3(int n) {
        int c=0;
        while(n!=0){
            c+=n&1 ;
            n=n>>>1;
        }
        return c;
    }
    
    
    //APPROACH 2  Ite until 0 + c => while(n!=0) n&n-1 will reset last set bit, so counting those bits
    
    
    // O(n) n- num of set bits
    // n&(n-1)  will reset the last set bit, try out examples
    // 1100 - 1011 -> 1000 // last bit gone 1(1)00
    public int hammingWeight2(int n) {
        int c = 0;
        while(n!=0){
            n = n&(n-1);
            c++;
        }
        return c;
    }
    //APPROACH 2.2  Ite until 0 + c => while(n!=0) n&n-1 will reset last set bit, so counting those bits
    // same as above recursive
    public int hammingWeight6(int n) {
        return n == 0 ? 0 : 1 + hammingWeight(n & (n - 1));
    }
    
    
    //APPROACH 3  Ite traditional counting of bits
    
    public int hammingWeight5(int n) {
        String num = Integer.toBinaryString(n); //build in function in java to convert Integer to Binary String 
        int count=0;
        for(char ch:num.toCharArray()){
            if(ch=='1')
                count++; //just counting the number of 1's
        }
        return count;
    }
    
    public int hammingWeight4(int n) {
        return Integer.bitCount(n);
    }
    
    
    
    public static void main(String[] args){
        System.out.println(hammingWeight3(11));
    }
    
    
    
}
