/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

import java.util.PriorityQueue;

/**
 *
 * @author venkateshwarans
 */
/*
  a   b
  5  10

a-b   asc
  
-1 0 1

b-a
1 0 -1




12345

ab
no swap a-b is negative


Key thing to remember is if comparison returns positive value ( >0) swap happens. Otherwise not during sorting algorithm.

Example:

4(a) 2(b) 6

For ascending order:

a > b (4 > 2) return 1 (swap requires between a and b i.e place 2 4 6)

For descending order:

a > b ( 2 > 4 ) return -1 (swap not needed between a and b i.e place 4 2 6, because it is already in order).

This logic implemented under sorting algorithm, So, just think if a and b are already in order as you expected then return -1 otherwise return 1.


*/

// priority queue
// while creating from a collection, time complexity is O(n) uses siftdown idea on half the nodes
// https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
// sift down hast to be from n/2 node up to root(not from root to n/2)

// while adding an element, it uses insert in last and siftup
// fo if we iterate and add it O(nlogn) and pass a collection its O(n)

// docs
// https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html

//refer actual java impl code
//http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/util/PriorityQueue.java
public class PriorityQueueUsage {
  public static void main(String args[]){
    //System.out.println(4>>1);
    int arr[] = new int[]{1,2,3,4,5,6,7,8};
    PriorityQueue<Integer> q = new PriorityQueue<Integer>((a,b) -> {
      return b-a;
    });
    for(int i=0;i<arr.length;i++){
        q.add(arr[i]);
    }
    while(!q.isEmpty()){
      System.out.println(q.poll());
    }
  }
}
