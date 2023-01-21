/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays_2d;

/**
 *
 * @author venka
 */
import java.util.*;

//  https://leetcode.com/problems/set-matrix-zeroes/

public class SetMatrixZeroes {
    
    //APPROACH 1 Ite Twice + Boolean[m][n] => first row col ite, we are just marking 0 sopts in dp, in seconds row col ite, we are checkking if marked and if marked
    //                                        we are using 2 loops to mark zeroes the whole row and col
     
    // Time O(mn*(m+n)) space: O(mn)
    // code is simple to understand
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        Boolean[][] m2 = new Boolean[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(matrix[i][j]==0) m2[i][j] = true;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(m2[i][j] != null){
                    for(int k=0;k<m;k++) matrix[k][j] = 0;
                    for(int k=0;k<n;k++) matrix[i][k] = 0;
                }
            }
        }
    }
    
    
    //APPROACH 2 Ite Twice + 2 int[] row,col => first row col ite, we are just marking 0 sopts in row[i], col[j], in seconds row col ite, 
    //                                          we are checkking if wither row or col marked, and making matriz[i][j] = 0;
    //                              
    // Time: O(mn) space: O(m+n)
    // we are storing which row and which col has zeroes in 2 int[] maps
    //then trerate again and checking if row matches i or col matche j then mark zero
    // same as above using int[] instead of sets
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(matrix[i][j]==0)
                    row[i] = col[j]=1;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(row[i]==1||col[j]==1)
                    matrix[i][j]=0;
    }
    
    
    //APPROACH 3 Ite Twice + using first row and col as dp => for(i=0,m) for(j=1,n) (leave first col), aslo col0=1 if first col has zero,  we are just marking 0 spots in matrix[i][0], matrix[0][j], 
    //                  in seconds row col ite reverse ite for(m-1,>=0) for(n-1,>=1) (leave first col), we are checkking if matrix[i][0]==0||matrix[0][j]==0 then  matrix[i][j]=0;
    //    
    // Time: O(mn) space: O(1)
    // the trick here is use first row and first columns as sets
    // we are going to leave first column alone, and use sep variable to track if it has zero
    // col0 lets us knw if first col has 0, and m[0][0] lets us knw if we have zero in first row
    // so always leave out first column
    // update first column using separate if
    public void setZeroes4(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        boolean col0 = false;
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0) col0=true;
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=1;j--){
                if(matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
            if(col0) matrix[i][0]=0;
        }
    }
    
}
