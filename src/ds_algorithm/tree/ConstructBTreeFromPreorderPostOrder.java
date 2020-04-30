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

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

import java.util.*;

public class ConstructBTreeFromPreorderPostOrder {
  
  
//  Time O(n) space O(n)
//  core Idea: same as construct from inorder, preorder, even simpler
//  post order is an mirror image
//  construct index from from post order
//  only append an element if its left side of the root in post order
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
      if(pre.length==0)return null;
      Map<Integer, Integer> map = new HashMap();
      for(int i=0;i<post.length;i++) map.put(post[i],i);
      return constructFromPrePost(pre, map, new int[]{0}, pre.length-1);
  }

  public TreeNode constructFromPrePost(int[] pre, Map<Integer, Integer> map, int idx[], int parentIdx) {
      if(idx[0]==pre.length)return null;
      int val = pre[idx[0]];
      int index = map.get(val);
      if(index>parentIdx) return null;
      TreeNode root = new TreeNode(val);
      idx[0]++;
      root.left = constructFromPrePost(pre, map, idx, index);
      root.right = constructFromPrePost(pre, map, idx, index);
      return root;
  }
  
// Time:O(n) space:O(n)
// Core idea: recursion and two pointers
// i for pre and j for post
// we can add to left until val euals post order
// so if i val not equals j val, continue left right recursion
// then increment j and return back
//  i keep on increasing and j keep on increasing, but node alone goes back in recursion
//  espcially when 4 and 5 were filled in 2 it naturally got back and incremented j to skip 2 in postorder
//  this works like a magical charm
  public static TreeNode constructFromPrePostMagicRec(int[] pre, int[] post){
      return constructFromPrePostMagicRec(pre,post, new int[]{0},new int[]{0});
  }                                      

  public static TreeNode constructFromPrePostMagicRec(int[] pre, int[] post, int i[], int j[]) {
      TreeNode node = new TreeNode(pre[i[0]++]);
      if (node.val != post[j[0]])
          node.left = constructFromPrePostMagicRec(pre, post, i, j);
      if (node.val != post[j[0]])
          node.right = constructFromPrePostMagicRec(pre, post, i, j);
      j[0]++;
      return node;
  }
 
/*
      1
    2   3  
   4 5 6 7
  
*/
 
//  Time: O(n) space: O(n)
//  Core Idea: iterative, dequeu, two pointers
//  i for pre and j for post
//  get root and add into dq and iterate from 1
//  if last node equals val at j, remove that from dq and move j forward
//  assign node to last node on dq possibly on left or on right;
//  add new node to dq
  public static TreeNode constructFromPrePostItertive(int[] pre, int[] post){
    Deque<TreeNode> dq= new ArrayDeque();
    dq.add(new TreeNode(pre[0]));
    for(int i=1, j=0;i<pre.length;i++){
        TreeNode x = new TreeNode(pre[i]);
        while(dq.getLast().val==post[j]){
            dq.pollLast();
            j++;
        }
        if(dq.getLast().left==null)
            dq.getLast().left = x;
        else
            dq.getLast().right = x;
        dq.add(x);
    }
    return dq.getFirst();
  }
  
  public static void main(String args[]){
    constructFromPrePostItertive(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1});
  }
}
