/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import ds_algorithm.Test;
import static ds_algorithm.tree.BinaryTree.breadthFirstSearch;
import static ds_algorithm.tree.PreOrderTraversal.preOrderTraversalRec;
import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/invert-binary-tree/

/*
     4
   /   \
  2     7
 / \   / \
1   3 6   9


     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/


public class InvertBinaryTree {
  
  
  public static TreeNode invertTree(TreeNode root) {
    if(root==null){
      return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
  }
  
  public static void main(String args[]){
    Test.test(breadthFirstSearch(invertTree(deSerializeBfs("4,2,7,1,3,6,9"))), new ArrayList(Arrays.asList(4,7,2,9,6,3,1)));
    
  }
}
