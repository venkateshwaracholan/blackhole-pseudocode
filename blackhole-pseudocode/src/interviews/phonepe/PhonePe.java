/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interviews.phonepe;

/**
 *
 * @author venka
 * 
 * 
 * 
 * 
 * 
 */

/*

/*

 we are given a boolean array ,  we need to find the max subarray with equal number 0 and 1's
 
 
 000011100110
 
 01010101
 
 00001
 
 
 
 String representation of the numeber 5678904321, we need to remove k digits from the number in such a way that final number is the smallest number possible. 
 
 23456, K =1  => 2345
 
 9876 => 876
 1239876 => 123876
 
 
 21987, k =1  2187, 1987
 
 21987, k =2  187


import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

// Main class should be named 'Solution' and should not be public.
class Solution {
    //  0001100110
    //  0123456789
    //  
    //       j
    //  i
    
    public static int[] maxSubarray(int[] in){
        if(in==null) return null;
        int max=0, start=0, end=0;
        Map<Integer,Integer> map = new HashMap<>();
        int sum  = 0;
        for(int i=0;i<in.length;i++){
            in[i] = in[i] == 0? -1 : 1;
        }
        
        for(int i=0;i<in.length;i++){
            sum+=in[i];
            if(sum==0){
                max = i+1;
                end = i;
            }
            if(map.containsKey(sum)){
                int x = map.get(sum);
                if(max< i-x){
                    max = i-x;
                    end = i;
                    start = x+1;
                }
            }else{
                map.put(sum,i);
            }
        }
        System.out.println(max);
        System.out.println(start);
        System.out.println(end);
        int[] ans = new int[max];
        for(int k=0,i=start; i<=end;k++){
            System.out.print(in[i]+",");
            ans[k] = in[i++];
            if(ans[k] == -1){
                ans[k] =0;
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args) {
        System.out.println("Hello, World");
        int[] a = maxSubarray(new int[]{0,0,0,1,1,0,0,1,1,0});
        int[] a = maxSubarray(new int[]{0,0,0,0,1});
        int[] a = maxSubarray(new int[]{01010101});
        int[] a = maxSubarray(new int[]{});
        System.out.println();
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+",");
        }
    }
}


*/
public class PhonePe {
    
}
