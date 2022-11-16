/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_2d;

/**
 *
 * @author vshanmugham
 */

/*

Question # 1 Revisit
How to attempt?
Question :

Q1. Given a number N followed a sequence of characters P representing a path, construct a matrix of size NxN and traverse the path P in that matrix and return the value at the end of the path. Fill the matrix with incremental numbers starting with 1 at the center and moving in a clockwise spiral as shown in the figure 1. Each character in path P is one of U,D,L,R representing 4 directions L for Left, R for Right, U for Up and D for Down. If the path takes you out of the matrix, return -1.

 
INPUTS: Your function will take 2 inputs.
ARGUMENT_1 will be an INTEGER (representing N)
ARGUMENT_2 will be a STRING (representing P the path)
 
EXPECTED OUTPUT: Your function shall return an INTEGER V. (which will be the Value in the position after traversing the path P)
 
How to build the N * N matrix : 

Starting with 1 in the center, and next number (2) shall be set above 1, and then the next number (3) to the right and then the next number (4) below (3) etc to form a spiral number matrix. (SPIRAL IS UP,RIGHT,DOWN,LEFT. (CLOCK-WISE MOVES). There can be MULTIPLE LEFTS or RIGHTS or UPs or DOWNs to fill the series)
 
For ex: IF N = 3, then the 3 X 3 matrix will be as follows :
Figure 1 
 
For 4X4 Matrix :

 
For 5 X 5 Matrix :

 
Example 1:
Input:5 LURU
Output:11
Explanation: 
 
 
Example 2 :   If the given COMMAND string goes beyond the matrix return -1
Input:5 LLL
Output:-1

*/


public class SpiralFromCenter {
  public static void main(String args[]){
    System.out.print(spiral_from_center(5,"LLU"));
    
    print_spiral_from_center(5);
  }
  
  // 1,2 = 2
  public static void print_spiral_from_center(int n){
    System.out.println();
    for (int i = 0; i < n; i++) { 
      for (int j = 0; j < n; j++) { 
        // x stores the layer in which (i, j)th 
        // element lies 
        int x; 
        // Finds minimum of four inputs 
        x = Math.min(Math.min(i, j),  
          Math.min(n - 1 - i, n - 1 - j)); 

        // For upper right half 
        if (i >= j) 
          System.out.print((n - 2 * x) * (n - 2 * x) -  
                            (i - x) - (j - x) + "\t"); 
 
        // for lower left half 
        else
          System.out.print((n - 2 * x - 2) * (n - 2 * x - 2) + 
                            (i - x) + (j - x) + "\t"); 
      } 
      System.out.println(); 
    } 
  }
  
  public static int spiral_from_center(int input1, String input2){
    int n = input1, m = input1;
		if(input1==0)
			return -1;
		int i = m/2, j= n%2==0 ? (n/2) - 1 : (n/2);
		char str[] = input2.toCharArray();
		for(int v=0;v<str.length;v++){
			int lt = str[v];
			switch(lt){
				case 'L': j--;break;
				case 'U': i--;break;
				case 'R': j++;break;
				case 'D': i++;break;
			}
		}
    int x = Math.min(Math.min(i, j),  
          Math.min(n - 1 - i, n - 1 - j)); 
    if(i>=0 && i<m && j>=0 && j<n){
      if (i >= j) 
        return (n - 2 * x) * (n - 2 * x) -  
                          (i - x) - (j - x); 
      else
        return (n - 2 * x - 2) * (n - 2 * x - 2) + 
                          (i - x) + (j - x);
    }
    return -1;
  }
  
  public static int spiral_from_center2(int input1, String input2){
    int n = input1, m = input1;
		if(input1==0)
			return -1;
		int i = m/2, j= n%2==0 ? (n/2) - 1 : (n/2);
//		System.out.println(i+" "+j);
		int k=1,l=1;
		int ele = 1;
		int mat[][] = new int[n][n];
    mat[i][j] = ele++;i--;
		while(ele<=m*n){
			for(int x = 0;x<k && ele<=m*n;x++){
				mat[i][j] = ele++;j++;
			}l++;
      
			for(int x = 0;x<l && ele<=m*n;x++){
			mat[i][j] = ele++; i++;
			}k++;
			
			for(int x = 0;x<k && ele<=m*n;x++){
			mat[i][j] = ele++; j--;
			}l++;
					
			for(int x = 0;x<l && ele<=m*n;x++){
			mat[i][j] = ele++; i--;
			}
			k++;
      
		}

		i = m/2; j= n%2==0 ? (n/2) - 1 : (n/2);
		char str[] = input2.toCharArray();
		for(int v=0;v<str.length;v++){
			int lt = str[v];
			switch(lt){
				case 'L': j--;break;
				case 'U': i--;break;
				case 'R': j++;break;
				case 'D': i++;break;
			}
		}

    for(int a=0;a<m;a++){
		 	for(int b=0;b<n;b++){
		 		System.out.print(mat[a][b] + " ");
		 	}
		 	System.out.println();
    }
		if(i>=0 && i<m && j>=0 && j<n)
			return mat[i][j];
		return -1;
    
  }
}
