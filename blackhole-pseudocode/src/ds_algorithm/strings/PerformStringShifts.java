/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.strings;

/**
 *
 * @author vshanmugham
 */
// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3299
import java.util.*; 

public class PerformStringShifts {

//  Time O(n+k) Space O(k) n - operations k - length of string
  public String stringShift(String s, int[][] shift) {
    int shiftz = 0, len = s.length();
    for(int i=0;i<shift.length;i++)
      shiftz+= shift[i][0]==1 ? shift[i][1] : -shift[i][1];
    shiftz = (shiftz%len);
    if(shiftz<0)
       shiftz = len + shiftz; 
    StringBuilder sb = new StringBuilder();
    for(int i=len-shiftz;i<len;i++)
        sb.append(s.charAt(i));
    for(int i=0;i<len-shiftz;i++)
        sb.append(s.charAt(i));
    return sb.toString();
  }
  
  public String stringShift2(String s, int[][] shift) {
    int rotate = 0,len = s.length();
    for(int[] r:shift)
      rotate+= r[0]==1 ? r[1] : -r[1];
    rotate = rotate % len;
    if(rotate < 0) rotate+=len;
    return s.substring(len-rotate)+s.substring(0,len-rotate);
  }
  
//  Time O(n+k) Space O(k) n - operations k - length of string
// core idea, use deque, where operation to get, put and remove from wither side is O(1)
  public String stringShiftDequeu(String s, int[][] shift) {
    int shiftz = 0,len = s.length();
    for(int i=0;i<shift.length;i++)
      shiftz+= shift[i][0]==1 ? shift[i][1] : -shift[i][1];
    shiftz = (shiftz%len);
    if(shiftz<0)
       shiftz = len + shiftz; 
    Deque deque = new ArrayDeque();
    for(int i=0;i<len;i++)
      deque.add(s.charAt(i));
    StringBuilder sb = new StringBuilder();
    while(shiftz>0){
      deque.addFirst(deque.removeLast());
      shiftz--;
    }
    for(int i=0;i<len;i++)
        sb.append(deque.removeFirst());
    return sb.toString();
  }

}
