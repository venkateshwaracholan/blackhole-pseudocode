/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming;

/**
 *
 * @author vshanmugham
 */

// recursions and its types
// https://www.geeksforgeeks.org/types-of-recursions/#:~:text=Recursion%20are%20mainly%20of%20two,one%20is%20called%20indirect%20recursion.

// imporatant ones:
// head recursion  - mistaken with bottom up usually, 

// tail recursion - mistaken with top dowm usually

// tree recursion  - dp
// when a normal problem of order n is traversed in 2**n or n**n, it is tree recursion
// not to be mistaken with recurson in a tree where n is number of nodes
// a recrusion over a tree can be called as tree recursion of depth d because n -> 2**d 



// tabulation - bottom up, 
// memoization top down
// https://www.geeksforgeeks.org/tabulation-vs-memoization/



// top down, also called as backtracking, memoization
// starting with the larger problem and create smaller sub problems to solve and backtrack up to the larger
// not all sub problems need to be solved, san be several can be skipped
// recursion is the know way to solve this, dont try iteration using stack and fail, never try that


// bottom up, tabulation
// solving smaller sub problems first and then scaling up to the larger ones
// all sub problems will be solves, even the unnecessary ones
// its us to us to decide if we want to solve the bottom up using iteation or recursion
// iteration is the obvious way to go, why risk it with recursion


// articles with examples:
// https://cs.stackexchange.com/questions/2644/is-there-a-difference-between-top-down-and-bottom-up-dynamic-programming

//https://stackoverflow.com/questions/13731612/dynamic-programming-top-down-versus-bottom-up-comparison

// https://stackoverflow.com/questions/19084172/dynamic-programming-top-down-vs-bottom-up

// https://stackoverflow.com/questions/6164629/what-is-the-difference-between-bottom-up-and-top-down


public class RecursionTopDownBottomUpWar {
  
}
