/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import static ds_algorithm.binary_tree.SerializeAndDeserializeBinaryTree.deSerializeDfs;

import java.util.*;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

public class PopulatingNextRightPointer2 {
  
  // Time: O(n) space: O(n)
  //this solution work irrespective of if tree is perfect or not.
  // normal bfs, two loops level order traversal
  // assign next to peek if something is present in the queue in that level
  public TreeNode connectBFS(TreeNode root) {
    if(root==null){
        return root;
    }
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    while(!q.isEmpty()){
        int siz = q.size();
        TreeNode n = q.peek();
        while(siz>0){
            n = q.poll();
            TreeNode next = siz>0 ? q.peek() : null;
            n.next = next;
            if(n.left!=null) q.add(n.left);
            if(n.right!=null) q.add(n.right);
            siz--;
        }
        n.next = null;
    }
    return root;
  }
  
  
  // Time: O(n) space: O(n)
  //  core idea is very simlar to bfs
  // we have to make sure all nodes in the level is linked as we travel inside
  // so mostly we are doing duplicate work again and again
  public static TreeNode connectDFS(TreeNode root) {
    if(root==null){
      return root;
    }
    //if(root.left!=null && root.left.val==9) System.out.println(root.right.val);

    link(root);
    TreeNode next = root.next;
    while(next!=null){
      
      link(next);
      next = next.next;
    }
    connectDFS(root.left);
    connectDFS(root.right);
    return root;
  }
    
  public static void link(TreeNode root){
    if(root.left!=null) root.left.next = root.right!=null? root.right :  getNext(root.next);
    if(root.right!=null) root.right.next = getNext(root.next);
  }
    
  public static TreeNode getNext(TreeNode root){
    while(root!=null){
        if(root.left!=null) return root.left;
        if(root.right!=null) return root.right;
        root = root.next;
    }
    return null;
  }
  
  public static void main(String[] args){
    BinaryTree tree = new BinaryTree();
    tree.root = deSerializeDfs("-9,-3,2,null,4,4,0,-6,null,-5");
    
    connectDFS(tree.root);
    
  }
  
}
