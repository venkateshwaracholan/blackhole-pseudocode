/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.decision_making;

import ds_algorithm.Test;
import ds_algorithm.binary_tree.BinaryTree;
import ds_algorithm.binary_tree.TreeNode;

import static ds_algorithm.binary_tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

import java.util.HashMap;
/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/house-robber-iii/

public class HouseRobber3 {
  
  public static int rob(TreeNode root) {
    int ans[] =  robHelper(root);
    return Math.max(ans[0],ans[1]);
  }
  
  // Time: O(n) space: O(n)
  // Core Idea : a and b are max so far in prevprev and prev respectively
  // post order traversal, dfs
  // if we are robbing current we can only add a from left and a from right which are prevprev
  // however if we dont rob current, we can take max of a,b from left and right side
  // we return an array of b,a so that b is immediate next and a is nextnext child.
  // we construct new a and b and return them to parent
  public static int[] robHelper(TreeNode root) {
    if(root==null)
        return new int[]{0,0};
    int left[] = robHelper(root.left);
    int right[] = robHelper(root.right);
    int b = root.val + left[1] +right[1];
    int a = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
    return new int[]{b,a};
  }
  
  public static void main(String args[]){
    BinaryTree tree = new BinaryTree();
    Test.test(rob(deSerializeBfs("3,2,3,null,3,null,1")), 7);
    Test.test(rob(deSerializeBfs("3,4,5,1,3,null,1")), 9);
    Test.test(rob(deSerializeBfs("4,1,null,2,null,3")), 7);
    
    Test.test(robAlt(deSerializeBfs("3,2,3,null,3,null,1")), 7);
    Test.test(robAlt(deSerializeBfs("3,4,5,1,3,null,1")), 9);
    Test.test(robAlt(deSerializeBfs("4,1,null,2,null,3")), 7);
    
  }
  
  
  // more readable memoization solution
  // Time: O(n) space: O(2n)
  // max(cur+a,prev) logic 
  
  public static int robAlt(TreeNode root) {

      HashMap<TreeNode, Integer> map = new HashMap<>();
      return robHelper(root, map);
  }
  public static int robHelper(TreeNode root, HashMap<TreeNode, Integer> map) {
      if (root == null) return 0;
      if (map.containsKey(root)) {
          return map.get(root);
      }
      int left = robHelper(root.left, map);
      int right = robHelper(root.right, map);

      int leftchildren = root.left != null ? robHelper(root.left.left, map) + robHelper(root.left.right, map) : 0;
      int rightchildren = root.right != null ? robHelper(root.right.left, map) + robHelper(root.right.right, map) : 0;
      int result = Math.max(left + right, root.val + leftchildren + rightchildren);
      map.put(root, result);
      return result;
  }
  
  
  
 
}
