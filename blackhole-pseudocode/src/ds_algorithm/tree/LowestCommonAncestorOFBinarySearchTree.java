/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

public class LowestCommonAncestorOFBinarySearchTree {
    
    
    // Time O(logn) space:O(logn)call stack of height of bst,h=logn
    //since its bst, if both p and q are less move left
    // else if both p and q are in greater move right
    // else cur is the ans
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val<root.val&&q.val<root.val)
            return lowestCommonAncestor(root.left,p,q);
        else if(p.val>root.val&&q.val>root.val)
            return lowestCommonAncestor(root.right,p,q);
        else return root;
    }
    
    // Time O(logn) space:O(logn)
    // same approadh in BFS
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> x=new LinkedList();
        x.add(root);
        while(!x.isEmpty()){
            TreeNode n=x.poll();
            if(p.val<n.val&&q.val<n.val)
                x.add(n.left);
            else if (p.val>n.val&&q.val>n.val)
                x.add(n.right);
            else return n;
        }
        return root;
    }
}