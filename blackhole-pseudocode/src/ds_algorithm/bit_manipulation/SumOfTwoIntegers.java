/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.bit_manipulation;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/sum-of-two-integers/description/

public class SumOfTwoIntegers {
    

    // T/S: O(1)/O(1)
    // sum = a xor b, carry = a&b
    // and then we have to left shift carry
    // xor adds 2 numbers and ans get us carry, not for a single bit, its for all the bits
    // 1001
    //+1101
    // 0100 ->sum
    // 1001 ->carry, left shift this ans assign to b until no more carry
    public int getSum(int a, int b) {
        int c = 0;
        while(b!=0){         
            c = a&b;
            a = a^b;
            b = c<<1;
        }
        return a;
    }
    // a 101   110   100   0000   1000 
    // b 011   010   100   1000   0000  
    
    
    //recursive of the above approach
    public int getSum2(int a, int b) {
        if(b==0) return a;
        int temp = a^b;
        b = (a&b)<<1;
        return getSum(temp,b);
    }
    
    public int getSum3(int a, int b) {
        return (b==0) ? a : getSum(a^b,(a&b)<<1);
    }
    
    
    
    
    
    // similarly for subtract method
    public int getSubtract(int a, int b) {
	while (b != 0) {
		int borrow = (~a) & b;
		a = a ^ b;
		b = borrow << 1;
	}
	
	return a;
    }
}


