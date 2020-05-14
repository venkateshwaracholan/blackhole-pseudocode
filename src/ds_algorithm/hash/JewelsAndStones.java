package ds_algorithm.hash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/jewels-and-stones
import java.util.*;

public class JewelsAndStones {
//   Time: O(m+n) space: O(m)
//  approach: hashing
  public int numJewelsInStones(String J, String S) {
      Set<Character> set = new HashSet();
      int ans = 0;
      for(int i=0;i<J.length();i++) set.add(J.charAt(i));
      for(int i=0;i<S.length();i++) if(set.contains(S.charAt(i))) ans++;
      return ans;
  }
  
//   Time: O(m+n) space: O(1)
//  approach: hashing, array as map
//  Note: 26+26 = 52, but why 58?
//  becoz tere are 6 special characters between caps and small
  public int numJewelsInStonesArrayAsMap(String J, String S) {
      int map[] = new int[58];
      int ans = 0;
      for(int i=0;i<J.length();i++) map[J.charAt(i)-'A']=1;
      for(int i=0;i<S.length();i++) if(map[S.charAt(i)-'A']==1) ans++;
      return ans;
  }
  
  
  public static void main(String args[]){
    System.out.println('z'-'A');
  }
}
