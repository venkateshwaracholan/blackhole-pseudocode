package ds_algorithm.arrays_2d;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/rotate-image/

// similar problems
// clockwise 90, transpose and mirror left to right
// anticlockwise 90, transpose and mirror top to bottom
// 180, mirror left to right and mirror top to bottom

// also think for non square matrix problem
//

/*
[
  [1,2,3],
  [4,5,6],
  [x,x,x]
],

[
  [3,6,x],
  [2,5,x],
  [1,4,x]
],

*/

public class RotateImage {
  
    //APPROACH 1 Transpose + mirror left right => transpose we can skip nodes below i,j diagonal inc diag(coz diag swapping is useless) 
    //                                            then -> i,j <-> j,i
    //              for mirror rev, i 0 to n, j 0 to n/2, swap left to right, i,j <-> i,n-1-j
    
    public void rotateAlt(int[][] matrix) {
        int r = matrix.length, c = r==0 ? 0 : matrix[0].length;
        for(int i=0;i<r;i++){
            for(int j=i+1;j<c;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][c-1-j];
                matrix[i][c-1-j] = temp;
            }
        }
    }
    
    //APPROACH 1.2 Transpose + mirror left right => transpose we can skip nodes below i,j diagonal inc diag(coz diag swapping is useless) 
    //                                            then -> i,j <-> j,i
    //              for mirror rev, i 0 to n, instead of j , use l,r to swap while l<r, ont swap even for equals, i,l <-> i,r
    
    
    //  time: O(mn) space:O(1)
    //  Core Idea: Transpose and mirror(reverse every row)
    //  for finding transpose in place, swap elements, but 
    //  the bounds should not run for all elements then transpose would be reversed to normal
    //  so i 0 to m, j i+1 to n so that swap happen only for half diagonal elements to produce transpose
    //  then we mirror left to right(o vice versa) to generte lockwise rotation
  
    public void rotate(int[][] matrix) {
        for(int i=0;i<matrix.length;i++)
            for(int j=i+1; j<matrix[0].length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        for(int i=0;i<matrix.length;i++){
            int l = 0, r = matrix[i].length-1;
            while(l<r){
                int temp = matrix[i][l];
                matrix[i][l++] = matrix[i][r];
                matrix[i][r--] = temp;
            }
        }    
    }
  
 /*
  
  [1,2,3],
  [4,5,6],
  [7,8,9]

  [7,4,1],
  [8,5,2],
  [9,6,3]
 */
    //APPROACH 2 circle swaps + bounds i 0,n/2, j i,n-1-i  => for 1st row col 0,n-1, for row 2 col 1,n-2, odd 3x3 odd=>[1,2], 4x4 even => [5,1,9,x]
    //                                                                                                                                    [x,4,x,x]
    
    //  Time: O(mn) space: O(1)
    //  this solution might look like a tricky one, but with faith understanding of the core idea it isnt that hard
    //  core idea: assign elements in circle
    //  bounds: i: 0 to i<n/2 so middle element is diag is not processed
    //          j: i to n-1-i, so that inner circles dont interfere with outer
    //
    // 5 , 1, 9,11
    // 2 , 4, 8,10
    // 13, 3, 6, 7
    // 15,14,12,16
    // let take i!=j
    // i=0,j=1,n=4;
    // 1 => matrix[i][j]
    // 10 => matrix[j][n-1-i]
    // 12 => matrix[n-1-i][n-1-j]
    // 13 => matrix[n-1-j][i]
    // once we find the bound and values its easy
    // bounds is a important we run only 5,1,9,4 
    // thats why j i to n-1-i(5,1,9) and i o to n/2 thats y2 rows executed
    // 
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n/2;i++){
            for(int j=i;j<n-1-i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}
