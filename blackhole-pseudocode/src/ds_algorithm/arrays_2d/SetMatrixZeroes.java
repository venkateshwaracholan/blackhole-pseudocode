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
    
    //APPROACH
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
    
    
    //APPROACH
    // Time: O(mn) space: O(m+n)
    // we are storing which row and whohc col has zeroes in 2 hashsets
    //then trerate again and checking if row matches i or col matche j then mark zero
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        Set<Integer> row = new HashSet();
        Set<Integer> col = new HashSet();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(row.contains(i)||col.contains(j)){
                    matrix[i][j]=0;
                }
            }
        }
    }
    // same as above using int[] instead of sets
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row[i]=1;
                    col[j]=1;
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(row[i]==1||col[j]==1){
                    matrix[i][j]=0;
                }
            }
        }
    }
    
    
    //APPROACH
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
