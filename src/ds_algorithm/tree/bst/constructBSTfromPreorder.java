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
import java.util.*;

// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal

public class constructBSTfromPreorder {
//  Time O(n) space: O(N)
//  Core idea: range
//  pass range to recursion and insert only if range is satisfied
//  assign from recursion return is the only way
//  assigning and calling recursion will cause a lot of trouble
  public TreeNode recursion(int preorder[], int lower, int upper, int idx[]){
      if(idx[0]==preorder.length) return null;
      int val = preorder[idx[0]];
      if(val < lower || val>upper) return null;  
      TreeNode n = new TreeNode(val);
      idx[0]++;
      n.left = recursion(preorder, lower, val, idx);
      n.right = recursion(preorder,val, upper, idx);
      return n;
  }

  public TreeNode bstFromPreorder(int[] preorder){
      return recursion(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, new int[]{0});
  }
  
//  Time O(n) space: O(N)
//  core idea: Iteration and recursion
//  root is created first nd the iterated from seconds element
//  recursion return a node if null
//  that is assigned to left or right accordingly
//  NOTE: everytime it starts the recution from root, and for non null, it just reassigns
//  and for null alone it creates new node and assigns and starts from actaul root again
  public TreeNode bstFromPreorderRecursiveReturn(int[] preorder) {
    if(preorder.length==0) return null;
    TreeNode root = new TreeNode(preorder[0]);
    for(int i=1;i<preorder.length;i++)
        insertInBSTreturn(root,preorder[i]);
    return root;
  }

  public TreeNode insertInBSTreturn(TreeNode root, int v){
    if(root==null) return new TreeNode(v);
    if(v>root.val) root.right = insertInBSTreturn(root.right,v);
    else root.left = insertInBSTreturn(root.left,v);
    return root;
  }
  
  
//  Time O(n) space: O(N)
//  core idea: Iteration and recursion
//  root is created first and the iterated from seconds element
//  recursion does not return any value
//  assigned if left or right is null or continue with traversal
//  NOTE: everytime it starts the recution from root, and for non null, it doesn not reassigns, only traverses
//  and for null alone it creates new node and assigns and starts from actaul root again
  public TreeNode bstFromPreorderRecursiveNonReturn(int[] preorder) {
    if(preorder.length==0) return null;
    TreeNode root = new TreeNode(preorder[0]);
    for(int i=1;i<preorder.length;i++)
      insertInBSTNonReturn(root,preorder[i]);
    return root;
  }

  public void insertInBSTNonReturn(TreeNode root, int v){
//    if(root==null) return; // this line was never useful
    if(v>root.val) {
      if(root.right==null) root.right = new TreeNode(v);
      else insertInBSTNonReturn(root.right,v);
    }
    else {
      if(root.left==null) root.left = new TreeNode(v);
      else insertInBSTNonReturn(root.left,v);
    }
  }
  
//  Time O(n) space: O(N)
//  core idea: iteration and stack
//  since it is preorder left is filled first and the right, so this algo works
//  add root node to stack and start looping from second
//  init temp as null inside loop
//  we add all nodes in the stack with root at the bottom
//  pop nodes until ne number is just greater than the node and not its parent or until empty
//  assign the popped node to temp
//  if temp is null, assign new node to stack's top nodes left
//  if temp is not null which means i new el is greater and so add it to temp's right
//  thats all
  public TreeNode bstFromPreorderIterative(int[] preorder) {
    if(preorder.length==0) return null;
    TreeNode root = new TreeNode(preorder[0]);
    Stack<TreeNode> s = new Stack();
    s.add(root);
    for(int i=1;i<preorder.length;i++){
        TreeNode temp = null;
        while(!s.isEmpty() && preorder[i]>s.peek().val)
            temp = s.pop();
        TreeNode n = new TreeNode(preorder[i]);
        if(temp==null) s.peek().left = n;
        else temp.right = n;
        s.add(n);
    }
    return root;
  }
  
  
  
  public static void main(String args[]){
    //bstFromPreorderTest(new int[]{8,5,1,7,10,12});
  }
    
}
