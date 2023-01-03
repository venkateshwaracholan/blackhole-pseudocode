/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/same-tree/description/

public class SameTree {
    
    
    // Time O(n) space O(n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null) return q==null;
        if(q==null) return p==null;
        if(p.val!=q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        else if(p==null||q==null || p.val!=q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        return p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }   
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return p == null || q == null ? p == q : p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    } 
    
    
    
    //
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> x= new LinkedList();
        x.add(p);
        x.add(q);
        while(!x.isEmpty()){
            p=x.poll();
            q=x.poll();
            if(p==null && q==null) continue;
            if(p==null||q==null) return false;
            if(p.val!=q.val) return false;
            x.add(p.left);
            x.add(q.left);
            x.add(p.right);
            x.add(q.right);
        }
        return true;
    }
    
    
    
    
    
    
    //
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> x= new LinkedList();
        Queue<TreeNode> y= new LinkedList();
        if(p==null&&q==null) return true;
        if(!check(p,q)) return false;
        x.add(p);
        y.add(q);
        while(!x.isEmpty()){
            TreeNode n1=x.poll();
            TreeNode n2=y.poll();
            if(!check(n1,n2)) return false;
            if(!check(n1.left,n2.left)) return false;
            if(!check(n1.right,n2.right)) return false;
            if(n1.left!=null) {
                x.add(n1.left);
                y.add(n2.left);
            }
            if(n1.right!=null){
                x.add(n1.right);
                y.add(n2.right);
            }
        }
        return true;
    }

    public boolean check(TreeNode p, TreeNode q){
        if(p==null&&q==null)return true;
        if(p==null||q==null)return false;
        return p.val==q.val;
    }
    
}


