/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.greedy;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/can-place-flowers/description/

public class CanPlaceFlowers {
    
    // Time O(n) spcae O(1)
    // approach: geeedy, check  current, left and right =0 and plant a flower, decrement n
    // handle edge case i==0 && i==n-1 with ors
    // check if n is already 0 and return true;
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i]==0 && (i==0||flowerbed[i-1]==0) && (i==flowerbed.length-1||flowerbed[i+1]==0)) {
                flowerbed[i]=1;
                if(n==0) return true;
                n--;
            }
        }
        return n==0;
    }
}
