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
/*
sq

n is even. n=4
1 1 1 1          0
0 1 1 0    //mid 1 
0 1 1 0          2
1 1 1 1          3 

n is odd n=5 mid = 2
1 1 1 1 1
0 1 1 1 0
0 0 1 0 0   //mid
0 1 1 1 0
1 1 1 1 1


0 1 2 3 4
0 1 2 1 0

0 1 2 3
0 1 1 0

non sq
m<n
             max(3,4) = 4
1 1 1 1 
0 1 1 0    //mid
0 1 1 0

non sq
m>n
         max(4,3) = 4
1 1 1
0 1 1     mid
0 1 1
1 1 1


mid = (n-1)/2


O(m*n)

Print elements in Q1 and Q3
hourglass pattern

11111  for both even and odd
01110
00100 
01110
11111

if non square, make it square and print but should not print virtual row or column 
added to make it square
*/


public class PrintElementsQ1Q3HourGlass {
  public static void printQ1Q3(int a[][],int m, int n){
    int x = Math.max(m,n);
    int k=0;int mid = (x-1)/2;
    for(int i=0;i<x;i++){
      for(int j=k;i<x-k;j++){
        if(i<m && j<n)
          System.out.print(a[i][j] +" ");
      }
      System.out.println();
      if(x%2==0 && i==mid)
        continue;
      if(i<mid)
        k++;
      else
        k--;
    }
  }
}
