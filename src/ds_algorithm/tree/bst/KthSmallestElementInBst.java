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

//https://leetcode.com/problems/kth-smallest-element-in-a-bst

public class KthSmallestElementInBst {
  
//  Time: O(n) space: O(n) rec - tree might not be balanced
// using inorder traversal in bst will always return a sorted array
// using a counter[] to return
//  usng Integer to identify null and not return
  public int kthSmallest(TreeNode root, int k) {
      return kthSmallest(root,new int[]{0},k);
  }

  
// alternatively we can also change i to k[] and decrement that and check k[0]==0 
  public Integer kthSmallest(TreeNode root, int i[], int k) {
      if(root==null) return null;
      Integer l = kthSmallest(root.left, i, k);
      if(l!=null)return l;
      i[0]++;
      if(k==i[0]) return root.val;
      return kthSmallest(root.right, i, k);
  }
  
// same as above with alt impl
  public int kthSmallestAlt(TreeNode root, int k) {
      int ans[] = new int[]{0};
      kthSmallestAlt(root,new int[]{0}, ans,k);
      return ans[0];
  }

  public void kthSmallestAlt(TreeNode root, int i[], int[] ans, int k) {
      if(root==null) return;
      kthSmallestAlt(root.left, i, ans, k);
      i[0]++;
      if(k==i[0]) {
          ans[0] = root.val;
          return;
      }
      kthSmallestAlt(root.right, i, ans, k);
  }
  
  
// Iterative inorder traversal using stack
// Time: O(n) space: O(n) stack - tree might not be balanced
// root!=null || !s.empty() is very important
// popping after we go left and then proceed to its right
// and once popped we can increment i or decement k whatever suits
  public int kthSmallestIte(TreeNode root, int k) {
      Stack<TreeNode> s = new Stack();
      while(root!=null || !s.empty()){
          while(root!=null){
              s.add(root);
              root = root.left;
          }
          root = s.pop();
          if(--k==0) return root.val;
          root = root.right;

      }
      return 0;
  }
}
