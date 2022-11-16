/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.numbers;

/**
 *
 * @author vshanmugham
 */
/*
Question # 1 Revisit
How to attempt?
Question :

Bonus Question:
 
NUMBER CHAIN -
A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.  
 
For example, for a number 44:
44 → 32 → 13 → 10 → 1 → 1
(4  square +4square = 16+16 gives next number 32. Then 3 square + 2  square  = 9 + 4 gives 13 and so on until 1 or 89)  
 
Another Example:
85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89
 
EVERY starting number will eventually arrive at 1 or 89 (and then keep repeating to 1 or 89)
 
So given a number N, find out how many numbers starting from 1 to N as start numbers will arrive at 89 (and return as INTEGER)? Where N can be any Natural Number.
 
Example: If N = 5, Expected Output (count of 89’s) = 4
 
How:
Since N = 5, FOR 1 to 5:
1 → 1 (So not 89, so not counted)
2 → 4 → 16 → 37 → 58 → 89 (Encountered 89, so count of 89’s = 1)
3 → 9 → 81 → 65 → 61 → 37 → 58 → 89 (so count of 89’s = 2)
4 → 16 → 37 → 58 → 89 (so count of 89’s = 3)
5 → 25 → 29 → 85 → 89 (so count of 89’s = 4)

*/


public class NumberChainSquare {
  public static void main(String args[]){
    System.out.print(number_chain(5));
  }
  
  public static int number_chain(int input1){
  	int i=1;
		int c = 0;
		while(i<=input1){
			int n = i;
			while(n!=1 && n!=89){
				n = sum_digits(n);
			}
			if(n==89)
				c++;
      i++;
		}
    return c;
	}

	public static int sum_digits(int n){
		int s = 0;
		while(n>0){
			s+= (n%10)*(n%10);
			n=n/10;
		}
		return s;
	}
}
