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
    
    
    
    //
    //Time:  Use StringBuilder: O(m + n)
    //indexOf takes O(m times n), or O(m + n) with KMP algorithm.
    //Space: O(max{(m, n)}) because of strings.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null)return false;
        String x = path(root,new StringBuilder()).toString();
        String y = path(subRoot,new StringBuilder()).toString();
        return x.indexOf(y)!=-1;
    }

    public StringBuilder path(TreeNode p, StringBuilder sb) {
        if(p==null){
            sb.append("#n");
            return sb;
        }
        sb.append('#').append(p.val);
        path(p.left,sb);
        path(p.right,sb);
        return sb;
    }
}
