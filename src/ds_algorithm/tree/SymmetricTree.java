/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import ds_algorithm.Test;
import static ds_algorithm.tree.DiameterOfBinaryTree.DiameterOfBinaryTree;
import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/symmetric-tree/

public class SymmetricTree {
  
  public static boolean isSymmetric(TreeNode root) {
      return isSymmetric(root, root);
  }
  
  // Time: O(n) space: O(n)
  // core idea: we need two trackers to check symmetry
  // passing, t1s left,t2s right and ice versa,
  // checking value before hand reduces unwanted work.
  public static boolean isSymmetric(TreeNode t1, TreeNode t2){
      if(t1==null && t2==null) return true;
      if(t1==null || t2==null) return false;
      return t1.val==t2.val && isSymmetric(t1.left,t2.right) && isSymmetric(t1.right,t2.left);
  }
  
  public static void main(String args[]){
    BinaryTree tree = new BinaryTree();
    Test.test(isSymmetric(deSerializeBfs("1,2,2,3,4,4,3")), true);
    Test.test(isSymmetric(deSerializeBfs("1,2,2,null,3,null,3")), false);
    
  }
  
}
