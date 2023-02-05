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
  
    //APPROACH 1 DFS inorder traversal + k[], update and chek with k in in order block, use temp var for return and return if not null   
    
    //  Time: O(n) space: O(n) rec - tree might not be balanced
    // using inorder traversal in bst will always return a sorted array
    // using a counter[] to return
    //  usng Integer to identify null and not return
    public int kthSmallest(TreeNode root, int k) {
        return kthSmallest(root,new int[]{k});
    }
    public Integer kthSmallest(TreeNode root, int[] k) {
        if(root==null) return null;
        Integer a = kthSmallest(root.left,k);
        if(a!=null) return a;
        if(--k[0]==0) return root.val;
        return kthSmallest(root.right,k);
    }
    
  
    //APPROACH 2 Ite inorder traversal with stack + k[], update k if k==0 return k
    // Iterative inorder traversal using stack
    // Time: O(n) space: O(n) stack - tree might not be balanced
    // root!=null || !s.empty() is very important
    // popping after we go left and then proceed to its right
    // and once popped we can increment i or decement k whatever suits
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack();
        TreeNode n = root;
        while(n!=null || !s.isEmpty()){
            while(n!=null){
                s.add(n);
                n=n.left;
            }
            n=s.pop();
            if(--k==0) return n.val;
            n=n.right;
        }
        return -1;
    }
}
