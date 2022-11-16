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
Q1. You are given a str, find out no. of palindromic substrings into it.
EX: ABAA o/p: 6

0123456789
aaabcbcaaa
 ik
O(n**2)



public static int count_plaindromic_substrings(String s){
	if(s==null){
  	return 0;
  }
	char arr[] = s.toCharArray();
  int n = arr.length;
  int ans = 0;
  for(int i=0;i<n;i++){
  	int k = 1;
    ans++;
    while((i+k)<n && (i-k)>=0){
    	if(arr[i+k]==arr[i-k])
      	ans++;
    	k++;
    }
  }
	return ans;
}


    
    
    
    
    
    
    
    
    
    
    


*/
public class MaximumNumberOfPalindromicSubstrings {
  
}
