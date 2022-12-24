/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.strings;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/maximum-number-of-balloons/description/

public class MaximumNumberOfBalloons {
    
    
    // Time O(n) space: O(1)
    // approach: letter freq map with cmap
    // the getting min of occ of balloon char in cmap
    //handle for l and o as they appear twice so cmap val/2
    public int maxNumberOfBalloons(String text) {
        int [] cmap = new int[26]; 
        int min = Integer.MAX_VALUE;
        String s= "balloon";
        for(int i=0;i<text.length();i++)
            cmap[text.charAt(i)-'a']+=1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='l' || s.charAt(i)=='o') 
                min = Math.min(min, cmap[s.charAt(i)-'a']/2);
            else 
                min = Math.min(min, cmap[s.charAt(i)-'a']);
        }
        return min;
    }
    
    
    // Time O(n) space: O(1)
    //approach: storing fre is res[5] 5 chars
    //using switch, for l and o ha to appear twice, so .5 normally, but i scaled everything to 2
    // to accomdate in int and divided by 2 at the end
    public int maxNumberOfBalloonsAlt(String text) {
        int [] res = new int[5]; 
        for(int i=0;i<text.length();i++){
            switch(text.charAt(i)){
                case 'b': res[0]+=2;break;
                case 'a': res[1]+=2;break;
                case 'l': res[2]+=1;break;
                case 'o': res[3]+=1;break;
                case 'n': res[4]+=2;break;
            }
        }
        Arrays.sort(res);
        return res[0]/2;
    }
    
    
    // same as above
    // maping char frequency to int[]
    //characters 'l' and 'o' have to appear twice, so weighing every other "baloon" char as twice and 'l' and 'o' as once. 
    //ideally 'l','o'=>0.5 'b','a','n'=>1 
    //scaling twice to fit into int[] res
    //divide min of res by 2 while returning
    public int maxNumberOfBalloons(String text) {
        int [] res = new int[5]; 
        int min = Integer.MAX_VALUE;
        for(int i=0;i<text.length();i++){
            switch(text.charAt(i)){
                case 'b': res[0]+=2;break;
                case 'a': res[1]+=2;break;
                case 'l': res[2]+=1;break;
                case 'o': res[3]+=1;break;
                case 'n': res[4]+=2;break;
            }
        }
        for(int n: res) min = Math.min(min, n);
        return min/2;
    }
}
