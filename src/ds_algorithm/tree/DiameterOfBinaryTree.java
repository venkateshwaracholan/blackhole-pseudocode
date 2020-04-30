/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import ds_algorithm.Test;
import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/diameter-of-binary-tree/
 
public class DiameterOfBinaryTree {
  
  // wrapper class to avoid static vriables and use arrays object 
  // property to store value as a reference
  // Time: O(n) space: O(n)
  public static int DiameterOfBinaryTree(TreeNode root){
    if(root==null)
      return 0;
    int ans[] = new int[1];
    DiameterOfBinaryTree(root, ans);
    return ans[0];
  }
  
  // core idea is to start counting from below using dfs,
  // using ans[] to get object property for storage instead of static variables,
  // check and assign ans if new max encountered for l+r+1;
  // return max of leaft and right to parent rec.
  public static int DiameterOfBinaryTree(TreeNode root, int ans[]){
    if(root==null)
      return 0;
    int l = DiameterOfBinaryTree(root.left, ans);
    int r = DiameterOfBinaryTree(root.right, ans);
    ans[0] = Math.max(ans[0], l+r);
    return Math.max(l,r)+1;
  }
  
  public static void main(String[] args){
    BinaryTree tree = new BinaryTree();
    Test.test(DiameterOfBinaryTree(deSerializeBfs("1,2,3,4,5")), 3);
    Test.test(DiameterOfBinaryTree(deSerializeBfs("")), 0);
    Test.test(DiameterOfBinaryTree(deSerializeBfs("1")), 0);
    Test.test(DiameterOfBinaryTree(deSerializeBfs("4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2")), 8);
    
    int n = 7>>1;
    
    System.out.println(n);
    
  }
  
  
  
  
}
