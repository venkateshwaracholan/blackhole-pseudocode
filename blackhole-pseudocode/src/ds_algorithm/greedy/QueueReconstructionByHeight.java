/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.greedy;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/queue-reconstruction-by-height/

import java.util.*;

public class QueueReconstructionByHeight {
  
//  Time: O(n**2) space: O(n)
//  core idea: greedy
//  sort by desc order of x[0] and if equal sort based on x[1]
//  and insert the element in an arraylist in x[1]th index so that rest gets swapped to next positions
//  Greedy idea explained
//  Assume we start with the following array:
/*
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

In the beginning part of the code where we sort, the array is sorted first by largest height, but if the heights are equal, they are sorted by the number of people in front of them that need to be taller from least to greatest. At this point, we now have:

[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]

We are inserting larger numbers first since we are traversing from beginning to end of this newly sorted array. For people that are shorter than taller people, the shorter person's position is irrelevant for the taller people. Taller people only care about how many people before them are taller. For those with the same height, the position of the people with higher second numbers is irrelevant since they will be after the previous people with the same height anyways. We are inserting these numbers in an order that won't effect anybody inserted after.

When we call "list.add(ppl[1], ppl)", we are adding that person in the position of the linked list where we have ppl[1] taller or equal height people in front.

[[7,0]]
[[7,0], [7,1]]
[[7,0], [6,1], [7,1]]
[[5,0], [7,0], [6,1], [7,1]]
[[5,0], [7,0], [5,2], [6,1], [7,1]]
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

*/
  public int[][] reconstructQueue(int[][] people) {
      Arrays.sort(people, (a,b)-> b[0]==a[0] ? a[1]-b[1]:b[0]-a[0]);
      ArrayList<int[]> ans = new ArrayList();
      for(int x[]: people) ans.add(x[1],x);
      return ans.toArray(new int[people.length][2]);
  }
  
  
/*
  why we have to sort people height from tall to short rather than short to tall？
  
  wudnt work
  
  Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

If we sort from short to tall -> [4,4],[5,0],[5,2],[6,1],[7,0],[7,1]
First element in the list is [4,4]
When inserting the second element [5,0] - we can not determine it's position, it can either be before or after the first element :-
# Before [4,4] -> '''[4,0], [4,1], [4,2]''', [5,0], [4,4]
# After [4,4] -> '''[3,0], [3,1],[3,2][3,3]''', [4,4], [5,0]
'''[4,0], [4,1], [4,2]''' and '''[3,0], [3,1],[3,2][3,3]''' are not a part of the given array, this is just an example to show that the solution can not be built if we sort from short to tall.

If we sort from tall to short -> [7,0],[7,1], [6,1], [5,0],[5,2], [4,4]
First element in the list is [7,0]
When inserting [7,1] the only valid position is after [7,0] because [7,1], [7,0] is invalid

We can only built the list iteratively if we sort from tall to short because the "second element is the number of people in front of this person who have a height greater than or equal to h"
  
  
  */
  
//  same as above comparator solution
  public int[][] reconstructQueueComparator(int[][] people) {
      Arrays.sort(people, new Comparator<int[]>(){
          public int compare(int a[], int b[]){
              return a[0]==b[0] ? a[1]-b[1] : b[0] - a[0]; 
          }
      });
      ArrayList<int[]> ans = new ArrayList();
      for(int x[]: people) ans.add(x[1],x);
      return ans.toArray(new int[people.length][2]);
  }
}
