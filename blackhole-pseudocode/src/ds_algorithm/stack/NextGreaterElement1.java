/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.stack;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/next-greater-element-i/description/

public class NextGreaterElement1 {
    
    
    // Time O(mn) space: O(n)
    //approach:hashing and iteration
    // create number to index map for access
    // assign nums1[i]=-1 initially and check with variable nums2[ind] both point to same 
    // start j frm ind+1 and go up until you find first greater element, set ans to nums1 and break
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap();
        for(int i=0;i<nums2.length;i++)
            map.put(nums2[i],i);
        for(int i=0;i<nums1.length;i++){
            int ind = map.get(nums1[i]);
            nums1[i] = -1;
            for(int j = ind+1;j<nums2.length;j++){
                if(nums2[ind]<nums2[j]) {
                    nums1[i] = nums2[j];
                    break;
                }
            }
        }
        return nums1;
    }
    
    
    // Time O(mn) space: O(1)
    //approach:reverse iteration until finding value
    // iterate nums1 nd store val in x ans init nums[i] to -1
    // start j from reverse and go until x is found in nums2
    // if x, any value assign that to nums1[i] which will get us the fitst greatest elemnt
    public int[] nextGreaterElementReverse(int[] nums1, int[] nums2) {
        for(int i=0;i<nums1.length;i++){
            int x = nums1[i];
            nums1[i]=-1;
            for(int j=nums2.length-1;x!=nums2[j];j--){
                if(x<nums2[j]) nums1[i]=nums2[j];
            }
        }
        return nums1;
    }
    
    // Time O(m+n) space: O(n)
    // approach: stack to store decreasing sequence
    // pop and put in map if an greater element comes in
    // pop until stack has a value higher than incoming
    // get values fom map and put -1 if not in map
    public int[] nextGreaterElementStack(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap();
        Stack<Integer> s = new Stack();
        for(int num: nums2){
            while(!s.isEmpty() && s.peek()<num)
                map.put(s.pop(), num);
            s.add(num);
        }
        for(int i=0;i<nums1.length;i++)
            nums1[i] = map.getOrDefault(nums1[i],-1);
        return nums1;
    }
    
    
    // alt solution using stack from right to left
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap();
        Stack<Integer> s = new Stack();
        for(int i=nums2.length-1;i>=0;i--){
            while(!s.isEmpty() && s.peek()<nums2[i]) s.pop(); 
            map.put(nums2[i], s.isEmpty() ? -1 : s.peek());
            s.add(nums2[i]);
        }
        for(int i=0;i<nums1.length;i++)
            nums1[i] = map.getOrDefault(nums1[i],-1);
        return nums1;
    }
    
}
