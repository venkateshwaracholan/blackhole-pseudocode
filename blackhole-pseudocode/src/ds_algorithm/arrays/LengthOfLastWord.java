/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays;

/**
 *
 * @author venka
 */

 // https://leetcode.com/problems/length-of-last-word/description/

public class LengthOfLastWord {
    
    //Time O(n) space: O(1)
    // approach: reverse iteration, flags to check if letters started
    public int lengthOfLastWord(String s) {
        int c=0; boolean letters= false;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' ') { 
                c++;
                letters = true;
            }else if(letters) return c;
        }
        return c;
    }
    
    // alt approach, istead of flag , we check if c > 0
    public int lengthOfLastWordAlt(String s) {
        int c=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' ') c++;
            else if(c>0) return c;
        }
        return c;
    }
    
    // approach: reverse iteration, skip spaces first and then start counting letters until space from back
    public int lengthOfLastWordAlt2(String s) {
        int i = s.length() - 1;
        int lastWordLength = 0;
        while (i >= 0 && s.charAt(i) == ' ') i--;
        for (;i >= 0 && s.charAt(i) != ' ';i--) 
            lastWordLength++;
        return lastWordLength;
    }
    
    // just having it for the methods learning
    public int lengthOfLastWordWorst(String str) {
        str.trim();
        String[] s= str.split(" ");
        int x = s[s.length-1].length();
        return x;
    }
}
