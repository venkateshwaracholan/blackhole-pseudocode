/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.hash;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

// https://leetcode.com/problems/most-common-word/

public class MostCommonWords {
  
//  Time: O(N+K) space: O(N+K) n - lenth of para k - length of banned
//  splitting isnt a solution, we have to either romve all special characters before hand
//  or use intuitive tokenisation like this using stringbuilder
//  accumulate letters in words and if a pecial character occurs, process the word if its not nothing ""
//  use a frequenc map and sincle it is just the largest we can have a max and compare if new freq
//  exceeds max ans,max = new values
//  paragraph += "." is very important as we will not process until a symbol is encountered
  public String mostCommonWord(String paragraph, String[] banned) {
    paragraph += ".";
    Map<String, Integer> map = new HashMap();
    Set<String> banSet = new HashSet();
    int max = 0;
    String ans = "";
    for(String s: banned) banSet.add(s.toLowerCase());
    StringBuilder word = new StringBuilder();
    for(char c: paragraph.toCharArray()){
        if(Character.isLetter(c))
            word.append(Character.toLowerCase(c));
        else if(word.length()>0){
            String fword = word.toString();
            //System.out.println(fword);
            if(!banSet.contains(fword)){
                map.put(fword,map.getOrDefault(fword,0)+1);
                if(map.get(fword)>max){
                    max = map.get(fword);
                    ans = fword;
                }   
            }
            word = new StringBuilder();
        }
    }
    return ans;
  }
  
//  Time: O(N+K) space: O(N+K) n - lenth of para k - length of banned
//  splitting with regex
  public String mostCommonWordRegex(String paragraph, String[] banned) {
    String[] words = paragraph.toLowerCase().split("[ !?',;.]+");
    String mostFrequestWord = null;
    int frequency = 0;
    Map<String, Integer> map = new HashMap<>();
    List<String> bannedList = Arrays.asList(banned);
    for(String word: words){
        if(!bannedList.contains(word)){
            int currentValue = map.getOrDefault(word, 0) + 1;
            if(currentValue >= frequency){
                mostFrequestWord = word;
                frequency = currentValue;
            }
            map.put(word, currentValue);
        }
    }

    return mostFrequestWord;
  }
}
