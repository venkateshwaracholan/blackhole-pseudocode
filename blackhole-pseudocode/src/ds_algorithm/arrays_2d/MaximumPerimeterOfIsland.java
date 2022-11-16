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

Q2: given a 2-D matrix of nXm with 0,1's into it, 0 = water, 1 = land.
print out the max parametir that any island can have.

In: 11000	O/P: 11
		10100			 1	
		00100
   
   
01234
0  
   11000010 10100021110100
  1 
   
0  00000
1  01000
2  00000
    
1111 
//squan x nrix
pub - O(2*(n**2))lic static int max_island(int arr[][]){
	int n = arr.length;
  int 
  int max = 0;
  
  int a[][] = new int[n][n];
  for (int i = 0; i < n; i++) {
  	for (int j = 0; j < n; j++) {
    	a[i][j] = 0;
    }
  }
  
  int trav = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; //let us assume this as 4
  Queue<Integer> q = new LinkedList();
  for(int i=0;i<n;i++){
  	for(int j=0;j<n;j++){
    	int rc = i*n+j;
      if(a[i][j]==1)	
        q.add(rc);
      int count = 0;
      while(!q.isEmpty()){
      	int rcn = q.poll();
        
        int r = rcn/n, c = rcn%n;
        arr[r][c] = 0;
        a[r][c] = 1;
        int cx =0;
      	for(int k=0;k<trav.length;k++){
        	int ro = trav[k][0], co = trav[k][1];
          if(isBound(r-ro,c-co,n) && arr[r-ro][c-co]==1){
          	q.add((r-ro)*n+c-co);
            cx++;
          }
          if(isBound(r-ro,c-co,n) && ar[r-ro][c-co]==1)
          
        }
        if(cx!=4)
        	count++;
        
      }
      max = Math.max(count,max);
    }
  }
 	return max;
}

public static bolean isBound(int r, int c, int n){
	return r>=0 && r<n && c>=0 && c<n;
}
    
    


*/
public class MaximumPerimeterOfIsland {
  
  public static void main(String args[]){
    
//    System.out.println(islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    
  }
}
