/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays;

/**
 *
 * @author venka
 */


//  https://leetcode.com/problems/is-subsequence/description/


public class IsSubSequence {
    
    // Time O(n) space O(1)
    //approach: two pointers
    // checks i<t.length()&&j<s.length() ensures if both are empty handled
    // increment j if char found
    // see if j ended
    public boolean isSubsequence(String s, String t) {
        int j=0;
        for(int i=0;i<t.length()&&j<s.length();i++){
            if(s.charAt(j)==t.charAt(i)) j++;
        }
        return j==s.length();
    }
}
