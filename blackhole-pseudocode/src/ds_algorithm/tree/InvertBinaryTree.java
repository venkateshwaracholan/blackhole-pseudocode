/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import ds_algorithm.Test;
import static ds_algorithm.tree.BinaryTree.breadthFirstSearch;
import static ds_algorithm.tree.PreOrderTraversal.preOrderTraversalRec;
import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/invert-binary-tree/

/*
     4
   /   \
  2     7
 / \   / \
1   3 6   9


     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/

import java.util.*;

public class InvertBinaryTree {

    
    /*
    * ONE LINERS — Quick Reference:\
    *
    * Invert Binary Tree (Top-Down DFS): if (root == null) return root (base case), save TreeNode left = root.left, swap children root.left = root.right, root.right = left (swapping with saved var coz root.left is modified), call invertTreeTopDown(root.left), invertTreeTopDown(root.right) (process subtrees after swap), return root → O(n), O(h) recursion stack.
    * Invert Binary Tree (Bottom-Up DFS): if (root == null) return root (base case), save TreeNode left = root.left, assign root.left = invertTreeRecBottomUp(root.right), root.right = invertTreeRecBottomUp(left) (cross-assign achieves swap using saved var coz root.left is modified), return root (children processed by recursion) → O(n), O(h).
    * Invert Binary Tree (BFS Iterative): Initialize queue = new LinkedList<TreeNode>(), if (root != null) enqueue root, while (queue not empty) poll node (TreeNode node = queue.poll()), save TreeNode left = node.left, swap node.left = node.right, node.right = left (swapping with saved var coz node.left is modified), enqueue node.left,node.right if not null (process subtrees after swap), finally return root → O(n), O(n).
    */

    /*
    * ONE LINER => Invert Binary Tree (Top-Down DFS): if (root == null) return root (base case), save TreeNode left = root.left, swap children root.left = root.right, root.right = left (swapping with saved var coz root.left is modified), call invertTreeTopDown(root.left), invertTreeTopDown(root.right) (process subtrees after swap), return root → O(n), O(h) recursion stack.
    *
    * Approach (Top-Down Recursive with explicit swap first):
    * - If root == null → return root (leaf base case).
    * - Save TreeNode left = root.left (backup original left pointer).
    * - Swap children:
    *     - root.left = root.right;
    *     - root.right = left;   (swapping with saved var coz root.left is modified)
    * - Recurse on swapped children (process subtrees after swap):
    *     - invertTreeTopDown(root.left);
    *     - invertTreeTopDown(root.right);
    * - Return root (inverted subtree).
    *
    * Reasoning:
    * - Swapping before recursion ensures recursive calls operate on already-inverted children (true top-down).
    * - The saved `left` prevents losing the original left child when overwriting root.left.
    * - Returning root preserves linkage when unwinding recursion.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(h) recursion stack (worst-case O(n) for skewed tree).
    *
    * Key Insight: Perform an immediate swap at the node, then recurse into children so inversion propagates top-down.
    */

    public TreeNode invertTreeTopDown(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTreeTopDown(root.left);
        invertTreeTopDown(root.right);
        return root;
    }
  
    /*
    * ONE LINER => Invert Binary Tree (Bottom-Up DFS): if (root == null) return root (base case), save TreeNode left = root.left, assign root.left = invertTreeRecBottomUp(root.right), root.right = invertTreeRecBottomUp(left) (cross-assign achieves swap using saved var coz root.left is modified), return root (children processed by recursion) → O(n), O(h).
    *
    * Approach (Bottom-Up Recursive with cross-assign):
    * - If root == null → return root.
    * - Save TreeNode left = root.left (backup original left before overwrite).
    * - Recurse and assign crosswise so swap is realized from recursion results:
    *     - root.left = invertTreeRecBottomUp(root.right);
    *     - root.right = invertTreeRecBottomUp(left);   (swap achieved by cross-assign using saved var)
    * - Return root (subtree now inverted).
    *
    * Reasoning:
    * - Recursion inverts child subtrees first (bottom-up). When the recursive calls return, assigning them to opposite children performs the swap.
    * - Saving `left` is necessary because root.left is overwritten by the first assignment.
    * - This style avoids an explicit temp-swap line; swap emerges from assignment order of recursion results.
    *
    * Time Complexity: O(n) — each node processed once.
    * Space Complexity: O(h) recursion stack (worst-case O(n)).
    *
    * Key Insight: Let recursion invert the subtrees, then cross-assign results to opposite children to realize the swap bottom-up.
    */
    public TreeNode invertTreeRecBottomUp(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode left = root.left;
        root.left = invertTreeRecBottomUp(root.right);
        root.right = invertTreeRecBottomUp(left);
        return root;
    }
  
    

    /*
    * ONE LINER => Invert Binary Tree (BFS Iterative): Initialize queue = new LinkedList<TreeNode>(), if (root != null) enqueue root, while (queue not empty) poll node (TreeNode node = queue.poll()), save TreeNode left = node.left, swap node.left = node.right, node.right = left (swapping with saved var coz node.left is modified), enqueue node.left,node.right if not null (process subtrees after swap), finally return root → O(n), O(n).
    *
    * Approach (Level-Order BFS Iterative):
    * - Initialize queue = new LinkedList<TreeNode>().
    * - If root != null → queue.add(root).
    * - While queue is not empty:
    *     - TreeNode node = queue.poll().
    *     - Save TreeNode left = node.left (backup original left).
    *     - Swap children:
    *         - node.left = node.right;
    *         - node.right = left;   (swapping with saved var coz node.left is modified)
    *     - Enqueue the (now swapped) children to continue processing (process subtrees after swap):
    *         - if (node.left != null) queue.add(node.left);
    *         - if (node.right != null) queue.add(node.right);
    * - Return root.
    *
    * Reasoning:
    * - BFS visits nodes level by level and applies swaps immediately; saved `left` prevents losing original reference during overwrite.
    * - Enqueuing children after swap ensures their subtrees will be processed in the inverted form.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — queue may hold up to O(n/2) nodes (last level) in the worst case.
    *
    * Key Insight: Iterative BFS avoids recursion limits and performs immediate per-node swaps while continuing level-order traversal by enqueueing swapped children.
    */


    public TreeNode invertTreeBfsIteQueue(TreeNode root) {
        var queue = new LinkedList<TreeNode>();
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
            
        }
        return root;
    }
  
    public static void main(String args[]){
      //Test.test(breadthFirstSearch(invertTree(deSerializeBfs("4,2,7,1,3,6,9"))), new ArrayList(Arrays.asList(4,7,2,9,6,3,1)));

    }
}
