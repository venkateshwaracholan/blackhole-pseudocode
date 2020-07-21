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

import java.util.*;

public class InvertBinaryTree {
  
  
//  Time: O(n) space:O(n) D tends to n recursion
//  core idea: tail recursion, swap before calling tail recursion
  public TreeNode invertTree(TreeNode root) {
      if(root==null) return null;
      TreeNode left = root.left;
      root.left = root.right;
      root.right = left;
      invertTree(root.left);
      invertTree(root.right);
      return root;
  }
  
//  Time: O(n) space:O(n) D tends to n in linear tree
//  core idea: head recursion, recursion assigning, swap/assign after recursion
//  return root so that assigning is possible
//  take a copy of left, so that we still keep it after overwrite
//  pass right node an assign to left node
//  pass the temp left and assign to right
  public TreeNode invertTreeDfsBetter(TreeNode root) {
      if(root==null) return null;
      TreeNode left = root.left;
      root.left = invertTreeDfsBetter(root.right);
      root.right = invertTreeDfsBetter(left);
      return root;
  }

// same as above, using 2 temporary varables
  
  public static TreeNode invertTreeDfs(TreeNode root) {
    if(root==null){
      return null;
    }
    TreeNode right = invertTreeDfs(root.right);
    TreeNode left = invertTreeDfs(root.left);
    root.left = right;
    root.right = left;
    return root;
  }
  
  
// Time: O(n) space: O(n) max n/2 but still order of n
// we are swapping in bfs, as it will also work
// core idea: iterative bfs (bfs is not possible throug recursion)
  
  public TreeNode invertTreeBfs(TreeNode root) {
        if(root==null) return null;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode x = q.poll();
            TreeNode left = x.left;
            x.left = x.right;
            x.right = left;
            if(x.left!=null) q.add(x.left);
            if(x.right!=null) q.add(x.right);
        }
        return root;
    }
  
  public static void main(String args[]){
    //Test.test(breadthFirstSearch(invertTree(deSerializeBfs("4,2,7,1,3,6,9"))), new ArrayList(Arrays.asList(4,7,2,9,6,3,1)));
    
  }
}
