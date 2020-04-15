/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeDfs;
import java.util.*;

/**
 *
 * @author vshanmugham
 */
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

// pointing right pointer for perfect binar tree, meaning all nored have 2 children or none.

public class PopulatingNextRightPointer1 {
  
  
  // Time: O(n) space: O(1) recursive
  // core idea - if root's left is not null, directly assign next to right as it will be present,
  // for right, we need to check if root has next and then assign its left if present.
  public static TreeNode connectDFS(TreeNode root) {
    if(root==null){
      return root;
    }
    if(root.left!=null) root.left.next = root.right;
    if(root.right!=null) root.right.next = root.next==null?null : root.next.left;
    connectDFS(root.left);
    connectDFS(root.right);
    return root;
  }
  
  
  // Time: O(n) space: O(n)
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
  
  public static void main(String[] args){
//    BinaryTree tree = new BinaryTree();
//    tree.root = deSerializeDfs("1,2,3,4,5,6,7");
    
  }
  
  
}
