/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/same-tree/description/

public class SameTree {
    
    //APPROACH 1 dfs, both nulls true, either null or val not equal false, and return left && right
    // Time O(n) space O(n)
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        else if(p==null||q==null || p.val!=q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    //unwnated
    public boolean isSameTree3(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        return p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }   
    public boolean isSameTree4(TreeNode p, TreeNode q) {
        return p == null || q == null ? p == q : p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    } 
    
    
    //APPROACH 2 BFS, allow nulls in queue, both nulls true, either null or val not equal false, and return left && right
    
    // critical, allowe nulls to pass on to queue to solve this easily
    public boolean isSameTree5(TreeNode p, TreeNode q) {
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
    
    
    
    //APPROACH 3 preorder sequencing, builod preorder seq with sb, check if equal
   
    // preorder -> root left right
    // Building Preorder sequence, u can build pre or post with # and N 
    // NOTE: inorder wont work
    // TIme O(2n) space: O(2n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        String ps = pre(p,new StringBuilder()).toString();
        String qs = pre(q,new StringBuilder()).toString();
        return ps.equals(qs);
    }
    public StringBuilder pre(TreeNode n, StringBuilder sb){
        if(n==null) return sb.append("N");
        sb.append('#').append(n.val);
        pre(n.left,sb);
        pre(n.right,sb);
        return sb;
    }
    
    
}



