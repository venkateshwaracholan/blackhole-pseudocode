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

// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
import java.util.*;

public class ConstructBTreeFromInoderPostorder {
  
//  Time: O(n) space: O(n)
//  core Idea, this is exactly same as constuct bst from preorder 
//  what i am doing is using idex as the ordeing from inorder
//  post order is a mirror image of preorder
//  so iterating in reverse and assigning right first and then left;
  public static TreeNode constructRec(int postorder[], int idx[], int low, int high, Map<Integer, Integer> map){
      if(idx[0]<0) return null;
      int val = postorder[idx[0]];
      int index = map.get(val);
      if(index<low || index>high) return null;
      TreeNode root = new TreeNode(val);
      idx[0]--;
      root.right = constructRec(postorder, idx, index+1, high, map);
      root.left = constructRec(postorder, idx, low, index-1, map);
      return root;
  }

  public static TreeNode buildTree(int[] inorder, int[] postorder) {
      if(postorder.length==0) return null;
      Map<Integer, Integer> map = new HashMap();
      for(int i=0;i<inorder.length;i++) map.put(inorder[i],i);
      return constructRec(postorder,new int[]{postorder.length-1}, 0,postorder.length-1, map);
  }
  
  public static void main(String args[]){
    buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
  }
}
