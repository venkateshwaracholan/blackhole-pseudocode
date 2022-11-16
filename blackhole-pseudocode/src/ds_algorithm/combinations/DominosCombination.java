/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.combinations;

/**
 *
 * @author vshanmugham
 */

/*
John found six dominoes. A domino is a rectangular tile with a line dividing its face into two square halves. Each half is marked with a number of spots. John wants to build a three-stage pyramid from these dominoes. The first level should consist of three dominoes, the second level of two dominoes and the highest of one domino. The levels are arranged in such a way that the peak of the pyramid is at the center: that is, each level is positioned over the center of the level below it. There is also an additional condition. The number of spots on each domino half should be the same as the number of spots on the half positioned beneath it. Note that this does not apply to neighboring dominoes on the same level.

Is it possible to build a pyramid from these dominoes, as described above? Dominoes can be freely rearranged. We also assume that dominoes can be rotated (that is, the piece [X, Y] can be treated as [X, Y] or [Y, X], where X denotes the number of spots in the first half-domino and Y denotes the number of spots in the second half-domino).

Write a function:

class Solution { public String solution(int[] A); }

that, given an array A consisting of twelve integers (the first and the second integer describe the first domino, the third and the fourth integer describe the second domino, etc.), returns the string "YES", if it is possible to build a correct pyramid from these dominoes or "NO" otherwise.

For example, given:

    A = [4, 3, 3, 4, 1, 2, 2, 3, 6, 5, 4, 5]
the function should return "YES". John found the following dominoes: [4, 3], [3, 4], [1, 2], [2, 3], [6, 5], [4, 5]. There are several ways to build the pyramid. For instance, John can rotate first and fifth domino and as a result gets: [3, 4], [3, 4], [1, 2], [2, 3], [5, 6], [4, 5]. Next, he can place the third, the first and the fifth domino at the first level (in this order), the fourth and the sixth domino at the second level (also in this order) and the second domino at the highest level. The result will be:
// 4, 3| 3, 4| 1, 2| 2, 3| 6, 5| 4, 5

          3 | 4
      2 | 3   4 | 5
  1 | 2   3 | 4   5 | 6 

Assume that:

array A contains twelve integers;
each element of array A is an integer within the range [1..12].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.



*/

import java.util.*;

class Domino{
  int id;
  int left, right;
  Domino(int id, int left, int right){
    this .id = id;
    this.left = left; 
    this.right = right;
  }
  
  public boolean equals(Object o){
    if(o==null || o.getClass()!=this.getClass()){
      return false;
    }
    Domino d = (Domino) o;
    return d.id == this.id;
  }
  
  public int hashCode(){
    return this.id;
  }
  
  public String toString(){
    return this.id+", "+this.left+" | "+this.right;
  }
}

public class DominosCombination {
  public static boolean validDominoPyramid(int A[]){
    Domino D[] = new Domino[A.length/2]; 
    for(int i=0;i<A.length;i+=2){
      D[i/2] = new Domino(i/2, A[i], A[i+1]);
    }
    Set<Domino> dominoSet = new HashSet(Arrays.asList(D));
    for(Domino d: D){
      dominoSet.remove(d);
      if(recursion(d, dominoSet)){
        return true;
      }
      dominoSet.add(d);
    }
    return false;
  } 
  public static boolean recursion(Domino d, Set<Domino> dominoSet){
    System.out.println(d);
    return false;
  }

//  public static boolean isDominoPyramidValid(int[] values)
//  {
//      int arrayLength = values.length;
//
//      int offset = 0;
//      int currentRow = 1; // Note: I'm using a 1-based value here as it helps the maths
//      boolean result = true;
//      while (result)
//      {
//          int currentRowLength = currentRow * 2;
//
//          // Avoid checking final row: there is no row below it
//          if (offset + currentRowLength >= arrayLength)
//          {
//              break;
//          }
//
//          result = checkValuesOnRowAgainstRowBelow(values, offset, currentRowLength);
//          offset += currentRowLength;
//          currentRow++;
//      }
//
//      return result;
//  }
//
//  private static boolean checkValuesOnRowAgainstRowBelow(int[] values, int startOfCurrentRow, int currentRowLength)
//  {
//      int startOfNextRow = startOfCurrentRow + currentRowLength;
//      int comparablePointOnNextRow = startOfNextRow + 1;
//      for (int i = 0; i < currentRowLength; i++)
//      {
//          if (values[startOfCurrentRow + i] != values[comparablePointOnNextRow + i])
//          {
//              return false;
//          }
//      }
//
//      return true;
//  }
//  
  
  public static void main(String args[]){
//    System.out.println(isDominoPyramidValid(new int[] { 3, 4, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6 }));
    System.out.println(validDominoPyramid(new int[] { 4, 3, 3, 4, 1, 2, 2, 3, 6, 5, 4, 5 }));
    
    
  }
}
