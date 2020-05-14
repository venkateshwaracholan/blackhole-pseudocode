/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree.bst;

/**
 *
 * @author vshanmugham
 */



/*

        5
      3   9
     2 4 6 11
     
     min-4, max-7
     
         5
        4 6
        
        
        5
      3   9
     2 4 6 11
     
     min-6, max-9
        
        9
       6

        5
      3   9
     2 4 6 11
     
     min-2, max-4
        
        3
       2 4
       
       
       5
     3   9
     
     min 9 max 9


       


*/


// https://leetcode.com/problems/trim-a-binary-search-tree/
// Trim binary search tree



import ds_algorithm.tree.TreeNode;

public class removeElementOutOfRangeBST {
  
//  Time: O(n) space: O(n)
//  approach: Top Down approach
//  assign root's left and right recursively
//  if root is < min move to right side recursively and find an inrange node to assign
//  if root is > max move to left side recursively to find an inrange node to assign
//  simple
  public TreeNode trimBST(TreeNode root, int L, int R) {
    if(root==null) return null;
    if(root.val<L) return trimBST(root.right,L,R);
    if(root.val>R) return trimBST(root.left,L,R);
    root.left = trimBST(root.left,L,R);
    root.right = trimBST(root.right,L,R);
    return root;
  }
  
//  Time: O(n) space:O(n)
//  bottom up approach, go to bottom most node first with recursive assigning
//  and if node is in range return itself
//  if node if bigger than max return left child
//  if node is smaller than min return right child
//  note nulls will be assigned to left and right if out of range and it propogates upwards
  public TreeNode trimBSTBottomUp(TreeNode root, int L, int R) {
        if(root == null) return null; 
        root.left = trimBST(root.left, L, R); 
        root.right = trimBST(root.right,L, R); 
        if(root.val < L) return root.right; 
        if(root.val > R) return root.left; 
        return root;
    }
  
//  
}
