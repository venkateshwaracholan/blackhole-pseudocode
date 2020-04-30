/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
import java.util.*;

public class ConstructBTreeFromInorderPreorder {
/*

* 0. preorder[0] is the root of tree
* 1. Iterate 0->len - each element of preorder 
* 2. Find the idx of that element in inorder
* Elements that are left to the idx are left sub-tree and right are right-subtree to that element
* 3. If you now divide the remaining elements in pre-order array into two, 
* the elements immediate to identified idx are part of left sub-tree and at the end it is right tree elements
* 4. recursively add left and right from preorder

*/ 
  

// Time: O(n) space O(n)
//Core Idea: recursion and use inorder's index as a map for order
// skip placing if its idex is not in the range
// use idx[] to keep track of the cur el index in rec
// readjust range in every recursion just like the preorder bst problem
  public static TreeNode constructRec(int[] preorder, Map<Integer,Integer> map, int idx[], int low, int high){
    if(low>high) return null;
    if(low==high) return new TreeNode(preorder[idx[0]++]);
    TreeNode root = new TreeNode(preorder[idx[0]]);
    int center = map.get(preorder[idx[0]++]);
    root.left = constructRec(preorder, map, idx, low, center-1);
    root.right = constructRec(preorder, map, idx, center+1, high);
    return root;
  }
  
  public static TreeNode buildTree(int preorder[], int inorder[]){
    if(preorder.length == 0 || preorder.length!=inorder.length) return null;
    Map<Integer, Integer> map = new HashMap();
    for(int i=0;i<inorder.length;i++) map.put(inorder[i], i);
    return constructRec(preorder, map, new int[]{0},0,preorder.length-1);
  }
  
  
// this, on the other hand, is the exact code we wrote for construct bst from preorder, 
//  and instead of the val itself I use the index to decide the order  
  public static TreeNode recursionBstWay(int preorder[], Map<Integer, Integer> map, int lower, int upper, int idx[]){
      if(idx[0]==preorder.length) return null;
      int val = preorder[idx[0]];
      int index = map.get(val);
      if(index < lower || index>upper) return null;  
      TreeNode n = new TreeNode(val);
      idx[0]++;
      n.left = recursionBstWay(preorder, map, lower, index-1, idx);
      n.right = recursionBstWay(preorder, map,index+1, upper, idx);
      return n;
  }

  public static TreeNode buildTreeBstWay(int[] preorder,int inorder[]){
    if(preorder.length == 0 || preorder.length!=inorder.length) return null;
    Map<Integer, Integer> map = new HashMap();
    for(int i=0;i<inorder.length;i++) map.put(inorder[i], i);
    return recursionBstWay(preorder, map, 0, preorder.length-1, new int[]{0});
  }
  
  
  

  public static void insertInBSTNonReturn(TreeNode root, int v, Map<Integer, Integer> map){
//    if(root==null) return; // this line was never useful
    if(map.get(v)>map.get(root.val)) {
      if(root.right==null) root.right = new TreeNode(v);
      else insertInBSTNonReturn(root.right,v, map);
    }
    else {
      if(root.left==null) root.left = new TreeNode(v);
      else insertInBSTNonReturn(root.left,v, map);
    }
  }
  
  public static TreeNode bstFromPreorderRecursiveNonReturn(int[] preorder,int  inorder[]) {
    if(preorder.length==0) return null;
    TreeNode root = new TreeNode(preorder[0]);
    Map<Integer, Integer> map = new HashMap();
    for(int i=0;i<inorder.length;i++) map.put(inorder[i], i);
    for(int i=1;i<preorder.length;i++)
      insertInBSTNonReturn(root,preorder[i], map);
    return root;
  }
  
  
  
  public static void main(String args[]){
    buildTreeBstWay(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
  }
}
