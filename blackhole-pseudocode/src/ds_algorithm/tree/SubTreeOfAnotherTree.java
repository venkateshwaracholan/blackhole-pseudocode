/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/subtree-of-another-tree/description/

public class SubTreeOfAnotherTree {
    
    // Time:  O(mn)
    // space  O(m+n)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null) return false;
        if(same(root,subRoot)) return true;
        return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
    }

    public boolean same(TreeNode p, TreeNode q){
        return p==null || q==null ? p==q: p.val==q.val&&same(p.left,q.left)&& same(p.right,q.right); 
    }
    
    // same as above, outer rec made to bfs, we can also solve issame with bfs, same timecomplexity
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(isSame(n,subRoot)) return true;
            if(n.left!=null) q.add(n.left);
            if(n.right!=null) q.add(n.right);
        }
       return false;
    }

    public boolean isSame(TreeNode p, TreeNode q){
        if(p==null&&q==null) return true;
        if(p==null||q==null||p.val!=q.val) return false;
        return isSame(p.left,q.left)&&isSame(p.right,q.right);
    }
    
    
    // preorder -> root left right
    // building preorder sequence
    //Time:  Use StringBuilder: O(m + n)
    //indexOf takes O(m times n), or O(m + n) with KMP algorithm.
    //Space: O(max{(m, n)}) because of strings.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String r = pre(root,new StringBuilder()).toString();
        String s = pre(subRoot,new StringBuilder()).toString();
        return r.indexOf(s)!=-1;
    }
    public StringBuilder pre(TreeNode n, StringBuilder sb){
        if(n==null) return sb.append("N");
        sb.append('#').append(n.val);
        pre(n.left,sb);
        pre(n.right,sb);
        return sb;
    }
}
