/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.design_data_structure;

/**
 *
 * @author vshanmugham
 */

/* #google

Given a real-time source of data, ignore all duplicates which were received within 10 seconds.

Example:
foo @ 0
bar @ 2
baz @ 5
foo @ 8
bar @ 13
foo @ 100

Prints: foo bar baz bar foo

print_occ(string s, int x)

No te; the timestamp passes is an increasing sequence 
*/

import java.util.*;


//  Time: O(n) space: O(n) for n messages 
//  core idea: heap + map
//  

public class IgnoreMessageWithinKSeconds {
  int k;
  int maxCleanup;
  Map<String, Integer> messageTimeMap;
  Queue<String> minHeap;
  IgnoreMessageWithinKSeconds(int k, int c){
    this.k = k;
    this.maxCleanup = c;
    this.messageTimeMap = new HashMap();
    minHeap = new PriorityQueue<>((a,b)-> messageTimeMap.get(a)-messageTimeMap.get(b));
  }
  
//  Time: O(1) space: O(1) 
//  if map doesnot contain or diff between time and time on map >= k
//  print the message
//  put message and time in map
//  Additionally,we use heap to possibley remove stale messages
//  so put message in heap
//  in the beggining of loop we try to remove entries that have time less than time - k
//  of the diff in times is greater than k, we need to remove them
//  This craetes a bottle neck when tere are too many entrie to remove created by timestamps didnt change much
//  so we have a max cap to clean up
//  Alternatively this cleanup can be done in myltiple threads with concurent versions of the heap without synchronization, but if there are collitions it would lock
  
  
  public void receiveMessage(String message, int time){
    int cleanup = maxCleanup;
    while(!minHeap.isEmpty() && (time-messageTimeMap.get(minHeap.peek())>k) && cleanup>0){
        messageTimeMap.remove(minHeap.poll());
        cleanup--;
    }
    if(!messageTimeMap.containsKey(message) || (time-messageTimeMap.get(message)>=k))
        System.out.println(message);
    messageTimeMap.put(message, time);
    minHeap.add(message);
  } 
  
  public static void main(String args[]){
    IgnoreMessageWithinKSeconds i = new IgnoreMessageWithinKSeconds(10, 5);
    i.receiveMessage("foo",0);
    i.receiveMessage("bar",2);
    i.receiveMessage("baz",5);
    i.receiveMessage("foo",8);
    i.receiveMessage("bar",13);
    i.receiveMessage("foo",100);
  }
}
