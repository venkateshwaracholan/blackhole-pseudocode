/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.cycle_detection;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

public class HappyNumbers {
  public static int getNext(int n){
    int s = 0;
    while(n>0){
        s+= (n%10)*(n%10);
        n/=10;
    }
    return s;
  }

//  Time: O(log(n)) space: O(log(n))
//  log(n) is of base 10 and indicated the number of digits in the number n
//  the solution has a cycle or reaches 1 and does not grow as individual sum gets back to 3 digits or lesser eventually.
//  we use a hashset to find cycle
  public static boolean isHappy(int n) {
    Set<Integer> set= new HashSet();
    while(n!=1 && !set.contains(n)){
        set.add(n);
        n = getNext(n);
    }
    return n==1;
  }
  
  //  we use the floyds cycle detection algo to find cycle
  //  Time: O(log(n)) space: O(1)
  public static boolean isHappyFloyds(int n) {
    int slow = n;
    int fast = getNext(n);
    while(fast!=1 && slow!=fast){
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }
    return fast==1;
  }
  
  
  // Math magic solution becoz of nature of the numbers and their pattern
  //  Time: O(log(n)) space: O(1) 
  public boolean isHappyMagic(int n) {
    while(n!=1 && n!=7){
        if(n%10 == n) return false;
        n = getNext(n);
    }
    return true;
  }
  
}
