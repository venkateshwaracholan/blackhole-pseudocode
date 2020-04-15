/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_2d;

import ds_algorithm.Test;

/**
 *
 * @author vshanmugham
 */

// https://www.geeksforgeeks.org/the-celebrity-problem/

public class CelebrityFinding {
  
  
  // recursion
  public static int findCelebrityRec(int a[][]){
    return findCelebrityRec(a,0,1);
  }
  
  public static int findCelebrityRec(int a[][], int i, int j){
    return 0;
  }
  
  // iteratve
  // Time: O(n) space: O(1)
  // core idea, a celebrity and his adjacent are not enough to prove that they are celebrity
  // so, we asume 0 as c and then if nc is a potenti celebrity move that to c
  // and proceed to confirm the behaviour with everyone else
  public static int findCelebrityite(int a[][]){
    int c, nc;
    for(c=0,nc=1;nc<a.length;){
      if(a[c][nc]==1 && a[nc][c]==0)
        c = nc;
      else
        nc++;
    }
    return c;
  }
  
  // 0 1 2 3 4 5
  // c
  //     n         
             
  
  // stack
  
  
  
  // the input itself is a matrix representation of a directed acyclic graph
  // we can use it to solve it in O(n)
  
  // loops once agin to confrim if the returned result is a celebrity
  public static void main(String args[]){
//    int input[][] = { { 1, 1, 1, 1 }, 
//                      { 1, 1, 1, 1 }, 
//                      { 0, 0, 1, 0 },  
//                      { 1, 1, 1, 1 } };
    int input[][] = { { 0, 0, 1, 0 }, 
                      { 1, 0, 1, 0 }, 
                      { 0, 0, 0, 0 },  
                      { 1, 0, 1, 0 } }; 
    Test.test(findCelebrityite(input),2);
  }
  
  
  
}
