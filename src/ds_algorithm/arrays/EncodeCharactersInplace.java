/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */

/*
aaabbcccccddaaa
a3b2c4d2a1

the input array is double the size to accomodate all output, 
give the solution in place in the array

*/

public class EncodeCharactersInplace {
  public static void main (String[] args) {
		// System.out.println("Hello Java");
    // System.out.println(max_product(new int[]{0,0,1,-4}));
    char[] arr = new char[]{'a','b','c','d','e','0','0','0','0','0'};
    System.out.println(String.valueOf(encode(arr)));
	}
  
  public static char[] encode( char[] a ){
    int n = a.length;
    int c=0, j=n-1;
    for(int i=0;i<(n/2)-1;i++){
      if(a[i]==a[i+1]){
        c++;
      }else{
        a[j] = a[i];
        a[j-1] = (char)(c+1);
        System.out.println(a[j]+" "+a[j-1]);
        j-=2;
        c=0;
      }
    }
    // while(j>0){
    //   a[j] = '0';
    //   j--;
    // }
    // for(int i=0;i<n/2;i++){
    //   char temp = a[i];
    //   a[i] = a[n-1-i];
    //   a[n-1-i] = temp;
    // }
    return a;
    
  }
}
