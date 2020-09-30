/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_behaviours;

/**
 *
 * @author vshanmugham
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
import java.util.*;

public class Comparators {
  
  // there is no way to sort primitives like int array
  public static void main(String args[]){
    int arr[] = new int[]{7,2,6,3,6,8,6};
    Integer[] input = Arrays.stream(arr).boxed().toArray(Integer[]::new);
    Arrays.sort(input, Collections.reverseOrder());
    System.out.println(input[0]);
    
    String test[] = new String[]{"a","b"};
    Arrays.sort(test, (a,b)-> a.compareTo(b));
    for(String s: test){
      System.out.println(s);
    }

    PriorityQueue<String> minHeap = new PriorityQueue<>((a,b)-> a.compareTo(b));
      minHeap.add("a");
      minHeap.add("b");
      while(!minHeap.isEmpty()){
        System.out.println(minHeap.poll());
    }
    IntegerComparator com = new IntegerComparator();
    System.out.println(com.compare(2,1));
  }
  
  
  
}

class IntegerComparator implements Comparator<Integer>{ 
    public int compare(Integer a, Integer b){ 
        return a - b;
    } 
    
    
} 