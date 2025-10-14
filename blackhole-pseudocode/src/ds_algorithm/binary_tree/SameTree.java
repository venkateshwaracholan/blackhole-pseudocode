/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/same-tree/description/

public class SameTree {

    /*
    * ONE LINERS — Quick Reference:\
    *
    * isSameTreeBottomUp: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), recursively compute left=isSameTreeBottomUp(p.left,q.left) and right=isSameTreeBottomUp(p.right,q.right)(left/right comparison happens bottom‑up), return p.val==q.val && left && right(value check happens after left/right checks when recursion unwinds same bottom up) → O(n), O(n) recursion space.
    * isSameTreeTopDown: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), if(p.val!=q.val)return false(values differ)(value check happens top‑down), recursively compute left=isSameTreeTopDown(p.left,q.left) and right=isSameTreeTopDown(p.right,q.right)(left/right comparison happens after recursion unwinds, bottom up) → O(n), O(n) recursion space.
    * isSameTreeBfsIteQueueDoubleAdd: Init queue queue = new LinkedList<TreeNode>(), initialize queue with both root nodes(p,q) for double poll, while queue not empty poll pNode,qNode(double poll), (if both null means equal, continue), if either null or pNode.val!=qNode.val return false(value check happens level by level), add left children and right children pairs(queue.add(pNode.left,qNode.left,pNode.right,qNode.right)(add both left and then only both right(polling twice for p and q), left/right comparison happens level‑by‑level)), return true(all node pairs match) → O(n), O(n) space.
    * isSameTreePreOrderString: call preOrder(p,new StringBuilder()) and preOrder(q,new StringBuilder()) to build preorder sequence of p and q (with null marker 'n' using preOrder(node,sb): if(node==null)sb.append("n"):encode null, sb.append(node.val):add value, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(pPreorder.equals(qPreorder)), return true if equal else false → O(2n), O(2n) space.
    */
    
    /*
    * ONE LINER => isSameTreeBottomUp: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), recursively compute left=isSameTreeBottomUp(p.left,q.left) and right=isSameTreeBottomUp(p.right,q.right)(left/right comparison happens bottom‑up), return p.val==q.val && left && right(value check happens after left/right checks when recursion unwinds same bottom up) → O(n), O(n) recursion space.
    *
    * Code(Reason):
    * - if(p==null && q==null) return true: both null means structure matches.
    * - if(p==null || q==null) return false: structure mismatch.
    * - boolean left = isSameTreeBottomUp(p.left,q.left): bottom‑up left subtree check.
    * - boolean right = isSameTreeBottomUp(p.right,q.right): bottom‑up right subtree check.
    * - return p.val==q.val && left && right: value check happens after left/right comparisons, all bottom up.
    *
    * Rationale: Bottom‑up traversal ensures structural equality is verified before value comparison, allowing early detection of subtree mismatch without redundant checks at higher levels.
    *
    * Time Complexity: O(n) — each node is visited once.
    * Space Complexity: O(n) — recursion stack in worst case (skewed tree).
    */

    public boolean isSameTreeBottomUp(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        boolean left = isSameTreeBottomUp(p.left,q.left);
        boolean right = isSameTreeBottomUp(p.right,q.right);
        return p.val==q.val && left && right;
    }

    /*
    * ONE LINER => isSameTreeTopDown: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null)return false(one null means structure mismatch), if(p.val!=q.val)return false(values differ)(value check happens top‑down), recursively compute left=isSameTreeTopDown(p.left,q.left) and right=isSameTreeTopDown(p.right,q.right)(left/right comparison happens after recursion unwinds, bottom up) → O(n), O(n) recursion space.
    *
    * Code(Reason):
    * - if(p==null && q==null) return true: both null means identical structure.
    * - if(p==null || q==null) return false: structure mismatch.
    * - if(p.val!=q.val) return false: top‑down value check.
    * - boolean left = isSameTreeTopDown(p.left,q.left): left subtree check if current node matches.
    * - boolean right = isSameTreeTopDown(p.right,q.right): right subtree check.
    * - return left && right: combine results happens bottom up because it depends on bottom result.
    *
    * Rationale: Top‑down traversal checks values first, allowing early mismatch detection before deeper recursion; subtree comparison happens afterward to confirm full structural equality.
    *
    * Time Complexity: O(n) — each node is visited once.
    * Space Complexity: O(n) — recursion stack in worst case.
    */

    public boolean isSameTreeTopDown(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        if(p.val!=q.val){
            return false;
        }
        boolean left = isSameTreeTopDown(p.left,q.left);
        boolean right = isSameTreeTopDown(p.right,q.right);
        return left && right;
    }
    
    /*
    * ONE LINER => isSameTreeBfsIteQueueDoubleAdd: Init queue queue = new LinkedList<TreeNode>(), initialize queue with both root nodes(p,q) for double poll, while queue not empty poll pNode,qNode(double poll), (if both null means equal, continue), if either null or pNode.val!=qNode.val return false(value check happens level by level), add left children and right children pairs(queue.add(pNode.left,qNode.left,pNode.right,qNode.right)(add both left and then only both right(polling twice for p and q), left/right comparison happens level‑by‑level)), return true(all node pairs match) → O(n), O(n) space.
    *
    * Code(Reason):
    * - Init queue queue = new LinkedList<TreeNode>()
    * - queue.add(p), queue.add(q): initialize BFS with both trees(polls twice later).
    * - while(!queue.isEmpty()): process level by level.
    * - poll pNode,qNode: paired node comparison.
    * - if(both null) continue: structure matches at this position.
    * - if(one null or pNode.val!=qNode.val) return false: structure/value mismatch.
    * - queue.add(pNode.left,qNode.left,pNode.right,qNode.right): add children for next level comparison(add both left and then only both right coz polling twice to get p and q otherwise we have to use record).
    * - return true: all node pairs matched.
    *
    * Rationale: BFS with paired node comparison enables level‑wise structure and value checking without recursion, providing an iterative alternative that naturally compares trees level by level.
    *
    * Time Complexity: O(n) — each node is visited once.
    * Space Complexity: O(n) — queue stores pairs of nodes.
    */

    public boolean isSameTreeBfsIteQueueDoubleAdd(TreeNode p, TreeNode q) {
        var queue = new LinkedList<TreeNode>();
        queue.add(p);
        queue.add(q);
        while(!queue.isEmpty()){
            var pNode = queue.poll();
            var qNode = queue.poll();
            if(pNode==null && qNode==null){
                continue;
            }
            if(pNode==null || qNode==null || pNode.val!=qNode.val){
                return false;
            }
            queue.add(pNode.left);
            queue.add(qNode.left);
            queue.add(pNode.right);
            queue.add(qNode.right);
        }
        return true;
    }
    
    
    
    /*
    * ONE LINER => isSameTreePreOrderString: call preOrder(p,new StringBuilder()) and preOrder(q,new StringBuilder()) to build preorder sequence of p and q (with null marker 'n' using preOrder(node,sb): if(node==null)sb.append("n"):encode null, sb.append(node.val):add value, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(pPreorder.equals(qPreorder)), return true if equal else false → O(2n), O(2n) space.
    *
    * Code(Reason):
    * - String pPreorder = preOrder(p,new StringBuilder()): serialize p in preorder with null markers.
    * - String qPreorder = preOrder(q,new StringBuilder()): serialize q in preorder with null markers.
    * - return pPreorder.equals(qPreorder): compare structure+value.
    * - preOrder(node,sb):
    *   - if(node==null) sb.append("n"): encode null.
    *   - sb.append(node.val): add value.
    *   - preOrder(node.left,sb), preOrder(node.right,sb): preorder traversal(root‑left‑right ensures structure+value comparison).
    *   - return sb: final serialization.
    *
    * Rationale: Preorder serialization with null markers encodes both structure and values, making a single string equality check a complete test for tree equality without explicit recursion comparison.
    *
    * Time Complexity: O(2n) — serialize both trees.
    * Space Complexity: O(2n) — storage for preorder strings and recursion stack.
    */
    public boolean isSameTreePreOrderString(TreeNode p, TreeNode q) {
        String pPreorder = preOrder(p, new StringBuilder()).toString();
        String qPreorder = preOrder(q, new StringBuilder()).toString();
        return pPreorder.equals(qPreorder);
    }

    public StringBuilder preOrder(TreeNode node, StringBuilder sb){
        if(node==null){
            sb.append("n");
            return sb;
        }
        sb.append(node.val);
        preOrder(node.left,sb);
        preOrder(node.right,sb);
        return sb;
    }
    
    
}



