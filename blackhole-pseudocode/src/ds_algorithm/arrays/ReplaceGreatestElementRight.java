/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays;

/**
 *
 * @author venka
 */
// https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

public class ReplaceGreatestElementRight {
    
    
    // Time O(n^2) space: O(1)
    // approach brute force with 2 loops
    public int[] replaceElementsBrute(int[] arr) {
        for(int i=0;i<arr.length-1;i++){
            int next = arr[i+1];
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]>next) next = arr[j]; 
            }
            arr[i]=next;
        }
        arr[arr.length-1]=-1;
        return arr;
    }
    
    
    // Time O(n) space O(1)
    // approach: reverse iteration, just accumulationg max value for each cell with startying max at -1
    // store in temp, replace cur with max, find new max
    public int[] replaceElements(int[] arr) {
        int max=-1;
        for(int i=arr.length-1;i>=0;i--){
            int temp = arr[i];
            arr[i]=max;
            max = Math.max(max,temp);
        }
        return arr;
    }
    
//[17,18,5,4,6,1]
//[18,6,6,6,1,-1]
//         i
//           j
}
