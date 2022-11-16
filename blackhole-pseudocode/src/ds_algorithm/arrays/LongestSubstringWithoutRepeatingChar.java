/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */


/*

 abcdeefg => abcde
 aabcefdaag => abcefd
 
 Only smaller case character 'a' <= char[i] <= 'z'
 
 aabcefdaag
 0123456789
 i
 j
 
 abcdabcd
 
 abcdecgkl
 0123456789
 i
      j
 
 max_start = 1
 max_end = 6

 
b
c
d
e
a 5

abcdecgkl

longest_unique_subsequence(String str){

  int max_start = 0, max_end = 0;
  int map[] = new int[26];
  for(int i=0;i<26;i++){
     map[i] = 0;
  }
  int j = 0;
  for(int j=0;j<str.length();j++){
      char c = str.charAt(j);
      if(map[c-'97']==0){
          map[c-'97'] = 1;
          if((max_end-max_start)<(j-i)){
              max_start = i;
              max_end = j;
          }
      }else{
          i++;
          
          j--;
      }
  }
  
}



*/

public class LongestSubstringWithoutRepeatingChar {
  
}
