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
public class RansomeNote {
//  Time O(m+n) space O(1)
  public boolean canConstruct(String ransomNote, String magazine) {        
      int map[]=new int[26];
      for(char ch:magazine.toCharArray()) map[ch-'a']++;
      for(char ch:ransomNote.toCharArray()){
          if(map[ch-'a']==0) return false;
          map[ch-'a']--;
      }            
      return true;
  }
  
//  Time O(m+n) space O(n)
//  we need to have additional check like if that character is present in map and then only check ist freq
  public boolean canConstructHashMap(String ransomNote, String magazine) {        
      Map<Character,Integer> map=new HashMap();
      for(char ch:magazine.toCharArray()) map.put(ch, map.getOrDefault(ch,0)+1);
      for(char ch:ransomNote.toCharArray()){
          if(map.getOrDefault(ch,0)==0) return false;  //if(!map.containsKey(ch) || map.get(ch)==0) return false;
          map.put(ch,map.get(ch)-1);
      }            
      return true;
  }
}
