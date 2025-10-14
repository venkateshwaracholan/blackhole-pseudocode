/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree.bst;

import ds_algorithm.binary_tree.TreeNode;

public class removeElementOutOfRangeBST {
  
//  Time: O(n) space: O(n)
//  approach: tail recursion approach
//  assign root's left and right recursively
//  if root is < min move to right side recursively and find an inrange node to assign
//  if root is > max move to left side recursively to find an inrange node to assign
//  simple
//  this is faster for this problem as it avoids traversing unwanted nodes which head recursion does.
  public TreeNode trimBSTTopDown(TreeNode root, int L, int R) {
    if(root==null) return null;
    if(root.val<L) return trimBSTTopDown(root.right,L,R);
    if(root.val>R) return trimBSTTopDown(root.left,L,R);
    root.left = trimBSTTopDown(root.left,L,R);
    root.right = trimBSTTopDown(root.right,L,R);
    return root;
  }
  
//  Time: O(n) space:O(n)
//  head recursion approach, go to bottom most node first with recursive assigning
//  and if node is in range return itself
//  if node if bigger than max return left child
//  if node is smaller than min return right child
//  note nulls will be assigned to left and right if out of range and it propogates upwards
  public TreeNode trimBSTBottomUp(TreeNode root, int L, int R) {
        if(root == null) return null; 
        root.left = trimBSTBottomUp(root.left, L, R); 
        root.right = trimBSTBottomUp(root.right,L, R); 
        if(root.val < L) return root.right; 
        if(root.val > R) return root.left; 
        return root;
    }
  
//  
}
