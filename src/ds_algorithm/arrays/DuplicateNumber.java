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

// explanation video
// https://www.youtube.com/watch?v=-YiQZi3mLq0

// https://leetcode.com/problems/find-the-duplicate-number/
// the key to solving this is time O(n) space O(1) is floyds tortoise and hare used in linked list cycle detection
// first loop can only detect a cycle
// second loop is required to find the start of the loop
// Time: O(n) space: O(1)


// D + K + Ci = N - turtle
// D + K + Cj = 2N - hare
// Cj - Ci = N
// 2D + 2K + 2Ci = 2N - eqn 2
// D + K + 2Ci - Cj = 0
// D + K = Cj - 2Ci
// D = (Cj-2Ci) - K // meaning if we move D positions from the tortoise position, 
// we will fall short of K nodes adn there fore will end up in the start of loop.
// since D is unknown we start a pointer from start and move tortoise and new pointer 1 node each
// and if they become equal tehn we covered D distance.

public class DuplicateNumber {
  public int findDuplicate(int[] nums) {
      int fast,slow;
      fast = slow = nums[0];
      do{
          slow = nums[slow];
          fast = nums[nums[fast]];
      }while(slow!=fast);

      fast = nums[0];
      while(slow!=fast){
          fast = nums[fast];
          slow = nums[slow];
      }
      return fast;
  }
  
}
