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
import ds_algorithm.tree.TreeNode;

// https://leetcode.com/problems/delete-node-in-a-bst

public class DeleteNodeFromBst {
  
// Time: O(logn) space: O(H)
// Approach: recursion, post order assignment stye
// methods to find max predecessor and min successor
// propogare recursion in post order assignment style until we find the element
// if found and leaf return null so that assignment in recursion will take care
// if right node is present, find min successor from right, copy it to node.val
// and trigger delete recrusion from right node and min successor val and it will be a leaf surely
// same for left with max predecessor
  public int successor(TreeNode root) {
      root = root.right;
      while (root.left != null) root = root.left;
      return root.val;
  }
  public int predecessor(TreeNode root) {
      root = root.left;
      while (root.right != null) root = root.right;
      return root.val;
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;

    if (key > root.val) root.right = deleteNode(root.right, key);
    else if (key < root.val) root.left = deleteNode(root.left, key);
    else {
      if (root.left == null && root.right == null) root = null;
      else if (root.right != null) {
        root.val = successor(root);
        root.right = deleteNode(root.right, root.val);
      }   
      else {
        root.val = predecessor(root);
        root.left = deleteNode(root.left, root.val);
      }
    }
    return root;
  }
  
  
  public TreeNode minSuccessor(TreeNode node){
      while(node.left!=null) node = node.left;
      return node;
  }
  public TreeNode maxPredecessor(TreeNode node){
      while(node.right!=null){
        node = node.right;
      }
      return node;
  }

// Time: O(logn) space: O(1)  
//  approach: bst iteration and recursion combined
//  iteratively traverse until node found and there are 3 major cases
//  separate node and root as we need to return root and have other checks
//  leaf, one child, two child
//  leaf case
//  if node == root, root has to be romed and is set null
//  we also use a parent pointer to store the parent 
//  parent.left is node, assign parent's lefft pointer to null and vice versa
//  on child case
//  if node == root, child becomes the root
//  or parent.left is node, assign parent's left pointer to child and vice versa
//  two children case
//  lets find the max predecessor or min successor
//  copy to temp, start delete rec from node and pass max predecessor or min successor value to delete
//  and once deletion is one copy the value to node
//  this is important becoz we dont have assignment recursion and to know the parent we are starting
//  one level above from root and copying value before hadn causees troubles in the structure
  
//  alternatively we can also skip 2 child case by consifering the approach done in recursion
//  alternatively we can also try heap approach
  public TreeNode deleteNodeIterative(TreeNode root, int val){
    if(root==null) return null;
    TreeNode node = root;
    TreeNode parent = null;
    while(node != null){
      if(node.val == val){
        if(node.left==null && node.right == null){
          if(node==root){
            root=null;
          }else if(parent.left == node){
            parent.left = null;
          }else if(parent.right == node){
            parent.right = null;
          }
        }else if(node.left==null || node.right == null){
          TreeNode child  = node.left!=null ? node.left : node.right;
          if(node==root){
            root = child;
          }else if(parent.left==node)
            parent.left = child;
          else if(parent.right==node)
            parent.right = child;
        }else{
          // TreeNode pred = maxPredecessor(node.left);
          // deleteNode(node.left, pred.val);
          // node.val = pred.val;

         TreeNode succ = minSuccessor(node.right);  // TreeNode succ = node.right; will also work heap way of doing things
         deleteNode(node, succ.val);
         node.val = succ.val;
        }
        return root;
      }else{
        parent = node;
        if(val < node.val){
          node = node.left;
        }else{
          node = node.right;
        }
      }
    }
    return root;
  }
  
  


//  alternatively we can also skip 2 child case by considering the approach done in recursion
//  but using objects to store max predecessor or min successor will result in nightmare, use primitives like int
  public TreeNode deleteNodeIteAlt(TreeNode root, int val){
    if(root==null) return null;
    TreeNode node = root;
    TreeNode parent = null;
    while(node != null){
      if(node.val == val){
        if(node.left==null && node.right == null){
          if(node==root) root=null;
          else if(parent.left == node) parent.left = null;
          else if(parent.right == node) parent.right = null;
        }else if(node.left!=null){
          int pred = maxPredecessor(node.left).val;
          deleteNode(node, pred);
          node.val = pred;
        }else{
          int succ = minSuccessor(node.right).val;  // TreeNode succ = node.right; will also work heap way of doing things
          deleteNode(node, succ);
          node.val = succ;
        }
        return root;
      }else{
        parent = node;
        if(val < node.val) node = node.left;
        else node = node.right;
      }
    }
    return root;
  }
}
