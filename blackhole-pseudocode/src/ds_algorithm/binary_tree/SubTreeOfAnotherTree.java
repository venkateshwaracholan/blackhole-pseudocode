/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/subtree-of-another-tree/description/

public class SubTreeOfAnotherTree {
    
    /*
    * ONE LINERS — Quick Reference:\
    *
    * isSubtreeDfsTopDown: if(root==null)return false(base case: empty tree can't contain subRoot), if(isSameTree(root,subRoot))return true(root matches subRoot, isSameTree check happens top‑down), boolean left=isSubtree(root.left,subRoot) and boolean right=isSubtree(root.right,subRoot), return left || right(subtree may exist in either branch, left/right comparison happens bottom‑up) → O(m*n), O(m+n) recursion space.
    * isSubtreeBottomUp(slower): if(root==null)return false(base case: empty tree can't contain subRoot), boolean left=isSubtreeBottomUp(root.left,subRoot) and boolean right=isSubtreeBottomUp(root.right,subRoot), return isSameTree(root,subRoot) || left || right(root check with isSameTree happens bottom‑up after left/right recursion unwinds) → O(m*n), O(m+n) recursion space.
    * isSubtreeBfsIte: Init queue=LinkedList<TreeNode>(), if(root!=null) add root to queue, while queue not empty poll node, if(isSameTree(node,subRoot)) return true(value and structure check happens level‑by‑level using isSameTree bottom‑up), add non‑null children to queue(left then right), return false(if no match found) → O(m*n), O(m) space.
    * isSubtreePreOrderString: call preOrder(root,new StringBuilder()) and preOrder(subRoot,new StringBuilder()) to build preorder sequences of root and subRoot (with null marker 'n' for null nodes and '#' as value separator(coz we wud call substring method) using preOrder(node,sb): if(node==null)sb.append("n"):encode null to preserve structure, sb.append('#').append(node.val):add value with '#' separator to avoid false matches in substring, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(rootPreOrder.indexOf(subRootPreOrder)!=-1)(substring search checks if subRoot structure+values exist in root), return true if found else false → O(m+n) serialization + O(m*n) substring search (naive) or O(m+n) with KMP, O(m+n) space.
    */

    /*
    * ONE LINER => isSameTree: if(p==null && q==null)return true(base case: both null means identical), if(p==null || q==null || p.val!=q.val)return false(one null or value mismatch means structure/value mismatch, value check happens top‑down), recursively compute left=isSameTree(p.left,q.left) and right=isSameTree(p.right,q.right)(left/right comparison happens bottom‑up), return left && right → O(n), O(n) recursion space.
    *
    * Code(Reason):
    * - if(p==null && q==null) return true: both null means structure matches.
    * - if(p==null || q==null || p.val!=q.val) return false: structure/value mismatch, value check happens top‑down.
    * - boolean left = isSameTree(p.left,q.left): bottom‑up left subtree check.
    * - boolean right = isSameTree(p.right,q.right): bottom‑up right subtree check.
    * - return left && right: combine left/right results after value check at current node.
    *
    * Rationale: Recursion unwinds bottom‑up for subtree checks while value check happens top‑down at each node, ensuring both structure and value match.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack for skewed trees.
    */
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null || p.val!=q.val){
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    
    /*
    * ONE LINER => isSubtreeDfsTopDown: if(root==null)return false(base case: empty tree can't contain subRoot), if(isSameTree(root,subRoot))return true(root matches subRoot, isSameTree check happens top‑down), boolean left=isSubtree(root.left,subRoot) and boolean right=isSubtree(root.right,subRoot), return left || right(subtree may exist in either branch, left/right comparison happens bottom‑up) → O(m*n), O(m+n) recursion space.
    *
    * Code(Reason):
    * - if(root==null) return false: empty root can't contain subtree.
    * - if(isSameTree(root,subRoot)) return true: subtree match found, isSameTree check happens top‑down.
    * - boolean left = isSubtree(root.left, subRoot): bottom‑up left subtree check.
    * - boolean right = isSubtree(root.right, subRoot): bottom‑up right subtree check.
    * - return left || right: subtree may exist in either branch, left/right comparison happens bottom‑up
    *
    * Rationale: Top‑down value check quickly identifies matches at root level; left/right recursion checks happen bottom‑up to aggregate results.
    *
    * Time Complexity: O(m*n) — each node of root potentially compared with entire subRoot.
    * Space Complexity: O(m+n) — recursion stack.
    */
    public boolean isSubtreeDfsTopDown(TreeNode root, TreeNode subRoot) {
        if(root==null){
            return false;
        }
        if(isSameTree(root,subRoot)){
            return true;
        }
        boolean left = isSubtree(root.left, subRoot); 
        boolean right = isSubtree(root.right, subRoot);
        return left || right;
    }

    /*
    * ONE LINER => isSubtreeBottomUp(slower): if(root==null)return false(base case: empty tree can't contain subRoot), boolean left=isSubtreeBottomUp(root.left,subRoot) and boolean right=isSubtreeBottomUp(root.right,subRoot), return isSameTree(root,subRoot) || left || right(root check with isSameTree happens bottom‑up after left/right recursion unwinds) → O(m*n), O(m+n) recursion space.
    *
    * Code(Reason):
    * - if(root==null) return false: empty root can't contain subtree.
    * - boolean left = isSubtreeBottomUp(root.left, subRoot): bottom‑up left subtree check.
    * - boolean right = isSubtreeBottomUp(root.right, subRoot): bottom‑up right subtree check.
    * - return isSameTree(root,subRoot) || left || right: root check with isSameTree happens bottom‑up after left/right recursion unwinds) or subtree may exist in either branch
    *
    * Rationale: Bottom‑up recursion ensures left/right subtrees are fully checked before comparing current node with subRoot; avoids unnecessary value checks unless needed.
    *
    * Time Complexity: O(m*n) — each node of root potentially compared with entire subRoot.
    * Space Complexity: O(m+n) — recursion stack.
    */

    public boolean isSubtreeBottomUp(TreeNode root, TreeNode subRoot) {
        if(root==null){
            return false;
        }
        boolean left = isSubtree(root.left, subRoot); 
        boolean right = isSubtree(root.right, subRoot);
        return isSameTree(root,subRoot) || left || right;
    }
    
    /*
    * ONE LINER => isSubtreeBfsIte: Init queue=LinkedList<TreeNode>(), if(root!=null) add root to queue, while queue not empty poll node, if(isSameTree(node,subRoot)) return true(value and structure check happens level‑by‑level using isSameTree bottom‑up), add non‑null children to queue(left then right), return false(if no match found) → O(m*n), O(m) space.
    *
    * Code(Reason):
    * - var queue = new LinkedList<TreeNode>(): BFS queue initialization.
    * - if(root!=null) queue.add(root): add root to start BFS.
    * - while(!queue.isEmpty()):
    *     - var node = queue.poll(): process current level node.
    *     - if(isSameTree(node, subRoot)) return true: match found, isSameTree does value and structure check.
    *     - if(node.left!=null) queue.add(node.left): enqueue left child.
    *     - if(node.right!=null) queue.add(node.right): enqueue right child.
    * - return false: no matching subtree found.
    *
    * Rationale: BFS iteratively traverses level by level, checking potential subtree roots early; isSameTree ensures complete equality check for each candidate node.
    *
    * Time Complexity: O(m*n) — each node of root potentially compared with entire subRoot.
    * Space Complexity: O(m) — queue storing nodes at a level.
    */
    public boolean isSubtreeBfsIte(TreeNode root, TreeNode subRoot) {
        var queue = new LinkedList<TreeNode>();
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            var node = queue.poll();
            if(isSameTree(node, subRoot)){
                return true;
            }
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return false;
    }
    
    
    /*
    * ONE LINER => isSubtreePreOrderString: call preOrder(root,new StringBuilder()) and preOrder(subRoot,new StringBuilder()) to build preorder sequences of root and subRoot (with null marker 'n' for null nodes and '#' as value separator(coz we wud call substring method) using preOrder(node,sb): if(node==null)sb.append("n"):encode null to preserve structure, sb.append('#').append(node.val):add value with '#' separator to avoid false matches in substring, preOrder(node.left,sb), preOrder(node.right,sb):preorder traversal(root‑left‑right ensures structure+value comparison)), compare sequences(rootPreOrder.indexOf(subRootPreOrder)!=-1)(substring search checks if subRoot structure+values exist in root), return true if found else false → O(m+n) serialization + O(m*n) substring search (naive) or O(m+n) with KMP, O(m+n) space.
    *
    * Code(Reason):
    * - String rootPreOrder = preOrder(root, new StringBuilder()): serialize root in preorder with null markers ('n') and separators ('#') to preserve structure and prevent false positives in substring search.
    * - String subRootPreOrder = preOrder(subRoot, new StringBuilder()): serialize subRoot similarly.
    * - return rootPreOrder.indexOf(subRootPreOrder)!=-1: check if subRoot serialization is a substring of root serialization (structure+value match).
    * - preOrder(node,sb):
    *   - if(node==null) sb.append('n'): encode null to preserve structure and avoid partial matches.
    *   - sb.append('#').append(node.val): append node value with '#' separator to prevent false matches (e.g., node values 12 and 2 should not match accidentally).
    *   - preOrder(node.left,sb), preOrder(node.right,sb): preorder traversal (root‑left‑right ensures structure+value comparison).
    *   - return sb: final serialized representation.
    *
    * Rationale: Preorder serialization with null markers ('n') encodes structure and '#' separators encode value boundaries, making substring search reliable for subtree detection without explicit recursion.
    *
    * Time Complexity: O(m+n) for serialization + O(m*n) substring search (naive) or O(m+n) with KMP algorithm.
    * Space Complexity: O(m+n) — storage for preorder strings and recursion stack.
    */

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String rootPreOrder = preOrder(root, new StringBuilder()).toString();
        String subRootPreOrder = preOrder(subRoot, new StringBuilder()).toString();
        return rootPreOrder.indexOf(subRootPreOrder)!=-1;
    }

    public StringBuilder preOrder(TreeNode node,  StringBuilder sb){
        if(node==null){
            sb.append('n');
            return sb;
        }
        sb.append('#').append(node.val);
        preOrder(node.left, sb);
        preOrder(node.right, sb);
        return sb;
    }
}
