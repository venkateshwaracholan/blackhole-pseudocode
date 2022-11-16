/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.min_max_path_to_reach_target;

/**
 *
 * @author vshanmugham
 */
public class Knapsack01 {
  
  
//  Time: O(2**n) space: O(n)
//  Core idea: top down, tree recursion, tail recursion
//  reduce capacity and break into smaller sub problems
//  if current weight is greater tha capacit skip it
//  else choose max of either pick the item or not pick the item
//  so while returnning, if we pick the item, add its value and deduct weight to pass through recursion
//  
  public static int zeroOneKnapsack(int c, int wt[], int val[]){
    return recursion(0, c, wt, val);
  }
  
  public static int recursion(int i, int c, int wt[], int val[]){
    if(i == wt.length) return 0;
    if(c <= 0) return 0;
    if(wt[i]>c){
      return recursion(i+1, c, wt, val);
    }else{
      return Math.max(val[i]+recursion(i+1, c - wt[i], wt, val), recursion(i+1, c, wt, val));
    }
  }
  
  
/*
  
Worst-case Time complexity: O(n*n) Worst-case Space complexity: O(n*n) 
explanation: there are 3 cases 
  w<n: for w< n, w is the bottleneck so, time and space becomes O(w*n) 
  w==n: O(w*n) = O(n*n) obvious 
  w>n time complexity becomes O(n*n) as n*number of possible subtractions(which is also bottlenecked by n) 
  to understand better think of w>>n like w = 1000000000, 
  this wouldn't affect the problem's run time complexity and space complexity(for hashmaps memoization) 
  
  Iterative bottom up is always O(w*n) but not top-down which at worst case in O(n*n) 
  
  if we are using 2d array for space in top-down, space complexity becomes O(w*n) and out of which only n*n values are filled.
  
  */
  
// lab - a b c d
// wt  - 1 1 1 1
// val - 1 1 1 1
// c = 4
// Index to remining capacity tree recursion representation i,c
/*
                         0,4
                1,3                 1,4
          2,2       2,3         2,3       2,4
      3,1   3,2   3,2  3,3   3,2   3,3  3,3  3,4
   
*/
     
// lab - a b c d
// wt  - 2 2 4 5
// val - 2 4 6 9
// c = 8
// ans = 13
  
//  Time: O(n**2) space: O(n**2) look at above explanation
//  Core idea: top down, tree recursion, tail recursion, memoization
//  i,c - index, remaining capacity will be repeated in sub problems
//  and since its already found, we can reuse them
  public static int zeroOneKnapsackMemo(int c, int wt[], int val[]){
    return recursion(0, c, wt, val, new Integer[wt.length][c+1]);
  }
  
  public static int recursion(int i, int c, int wt[], int val[], Integer dp[][]){
    if(i == wt.length) return 0;
    if(c <= 0) return 0;
    if(dp[i][c]!=null) return dp[i][c];
    if(wt[i]>c){
      return dp[i][c] = recursion(i+1, c, wt, val);
    }else{
      return dp[i][c] = Math.max(val[i]+recursion(i+1, c - wt[i], wt, val), recursion(i+1, c, wt, val));
    }
  }
  
  
  
  
//  Time: O(n*c) space: O(n*c)
  
/*
      1 2 3 4 5 6  7  8
  2 2 0 0 0 0 0 0  0  0
  2 4 0 2 2 2 2 2  2  2
  4 6 0 4 4 6 6 6  6  6
  5 9 0 4 4 6 6 10 10 12
      0 4 4 6 9 10 13 13
  
*/
  public static int zeroOneKnapsackBottomUp(int c, int wt[], int val[]){
    int dp[][] = new int[wt.length+1][c+1];
    for(int i=0;i<wt.length;i++){
      for(int j=1;j<=c;j++){
        if(wt[i]<=j){
          dp[i+1][j] = Math.max(val[i]+dp[i][j-wt[i]], dp[i][j]);
        }else{
          dp[i+1][j] = dp[i][j];
        }
      }
    }
    return dp[wt.length][c];
  }
  
   
  
  
  public static void main(String args[]){
    int wt[] = new int[]{2,2,4,5};
    int val[] = new int[]{2,4,6,9};
    //System.out.println(zeroOneKnapsackMemo(8,wt,val));
    System.out.println(zeroOneKnapsackBottomUp(8,wt,val));
  }
  
  
  

  
  
}
       


