package ds_algorithm.binary_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/binary-tree-maximum-path-sum/

public class BinaryTreeMaximumPathSum {


    /*
    * maxPathSumRecursiveBottomUp => int[] max = new int[]{Integer.MIN_VALUE}: (track global maximum path sum across recursion without globals); maxPathSum(root, max): (start recursion to compute max path sum), return max[0]: (final maximum path sum found); maxPathSum(root, max): if(root==null) return 0: (base case, null node contributes 0 path sum), int left = maxPathSum(root.left, max): (compute maximum path sum of left subtree), int right = maxPathSum(root.right, max): (compute maximum path sum of right subtree), int maxSinglePath = Math.max(root.val, Math.max(root.val+left, root.val+right)): (maximum path sum starting at current node and going downwards to either side or just the node itself), int subPathSum = root.val + left + right: (maximum path sum for a “subpath passing through node: left → node → right”, valid because problem allows such connections), max[0] = Math.max(max[0], Math.max(subPathSum, maxSinglePath)): (update global maximum considering both single‑side paths and subpaths passing through node), return maxSinglePath: (return maximum single‑side path for parent computation) → O(n), O(h) space.
    *
    * Code(Reason):
    * - int[] max = new int[]{Integer.MIN_VALUE}: store global maximum path sum, initialized to smallest integer to handle negative values.
    * - maxPathSum(root, max): start recursion to compute path sums.
    * - return max[0]: return the computed global maximum path sum.
    * - maxPathSum(root, max):
    *     - if(root == null) return 0: base case — a null node contributes zero to path sum.
    *     - int left = maxPathSum(root.left, max): compute maximum path sum from left subtree.
    *     - int right = maxPathSum(root.right, max): compute maximum path sum from right subtree.
    *     - int maxSinglePath = Math.max(root.val, Math.max(root.val+left, root.val+right)): compute maximum path sum starting at current node going downwards to one side or the node alone.
    *     - int subPathSum = root.val + left + right: compute maximum path sum for a “subpath passing through node: left → node → right”, which connects a node in the left subtree to a node in the right subtree through the current node — a valid candidate for the global maximum path sum.
    *     - max[0] = Math.max(max[0], Math.max(subPathSum, maxSinglePath)): update global maximum path sum considering both single‑side paths and subpaths passing through node.
    *     - return maxSinglePath: return maximum single‑side path sum for parent computations.
    *
    * Rationale: The problem allows paths that connect any two nodes in the tree without necessarily passing through the root. Therefore, at each node we must consider both (a) the maximum path sum going downwards to one side (maxSinglePath) and (b) the maximum path sum for a “subpath passing through node: left → node → right” (subPathSum). Tracking a global maximum ensures we catch the largest possible path anywhere in the tree.
    *
    * Time Complexity: O(n) — each node is visited exactly once.
    * Space Complexity: O(h) — recursion stack up to tree height.
    */
    public int maxPathSumRecursive(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSumRecursive(root, max);
        return max[0];
    }

    public int maxPathSumRecursive(TreeNode root, int[] max) {
        if(root==null){
            return 0;
        }
        int left = maxPathSumRecursive(root.left, max);
        int right = maxPathSumRecursive(root.right, max);
        int maxSinglePath = Math.max(root.val, Math.max(root.val+left, root.val+right));
        int subPathSum = root.val + left + right;
        max[0] = Math.max(max[0], Math.max(subPathSum, maxSinglePath));
        return maxSinglePath;
    }
  
    
    /*
    * maxPathSumIgnoringNegativesBottomUp => int[] max = new int[]{Integer.MIN_VALUE}: (track global maximum path sum without globals), maxPathSum(root, max): (start recursion to compute max path sum ignoring negative paths), return max[0]: (final maximum path sum found); maxPathSum(root, max): if(root == null) return 0: (base case, null node contributes 0 path sum), int left = Math.max(0, maxPathSum(root.left, max)): (compute maximum path sum of left subtree ignoring negative contributions), int right = Math.max(0, maxPathSum(root.right, max)): (compute maximum path sum of right subtree ignoring negative contributions), int maxSinglePath = root.val + Math.max(left, right): (maximum single‑side path sum starting at current node ignoring negative subpaths), int subPathSum = root.val + left + right: (maximum path sum for a “subpath passing through node: left → node → right” ignoring negative contributions), max[0] = Math.max(max[0], subPathSum): (update global maximum considering only positive‑contributing subpaths), return maxSinglePath: (return maximum single‑side positive path for parent computation) → O(n), O(h) space.
    *
    * Code(Reason):
    * - int[] max = new int[]{Integer.MIN_VALUE}: store global maximum path sum, initialized to smallest integer to handle trees with all negative values.
    * - maxPathSum(root, max): start recursion to compute maximum path sums ignoring negative contributions.
    * - return max[0]: return the computed global maximum path sum ignoring negative subpaths.
    * - maxPathSum(root, max):
    *     - if(root == null) return 0: base case — a null node contributes zero to path sum.
    *     - int left = Math.max(0, maxPathSum(root.left, max)): compute maximum left subtree path sum ignoring negative contributions.
    *     - int right = Math.max(0, maxPathSum(root.right, max)): compute maximum right subtree path sum ignoring negative contributions.
    *     - int maxSinglePath = root.val + Math.max(left, right): compute maximum single‑side path sum starting at current node ignoring negative paths, since negative contributions are skipped.
    *     - int subPathSum = root.val + left + right: compute maximum path sum for a “subpath passing through node: left → node → right” ignoring negative contributions, which connects a node in the left subtree to a node in the right subtree through the current node.
    *     - max[0] = Math.max(max[0], subPathSum): update global maximum path sum considering only positive‑contributing subpaths.
    *     - return maxSinglePath: return maximum single‑side positive path sum for parent computations.
    *
    * Rationale: By ignoring negative contributions via Math.max(0, …), we avoid including subpaths that reduce the total path sum. This simplifies logic by eliminating the need for extra negative checks, and ensures we only consider beneficial paths. The subPathSum check still considers “subpaths passing through node: left → node → right” so we do not miss valid connecting paths with both left and right children.
    *
    * Time Complexity: O(n) — each node is visited exactly once.
    * Space Complexity: O(h) — recursion stack up to tree height.
    */

    public int maxPathSumIgnoringNegatives(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, max);
        return max[0];
    }

    public int maxPathSum(TreeNode root, int[] max) {
        if(root==null){
            return 0;
        }
        int left = Math.max(0, maxPathSum(root.left, max));
        int right =  Math.max(0, maxPathSum(root.right, max));
        int maxSinglePath = root.val + Math.max(left, right);
        int subPathSum = root.val + left + right;
        max[0] = Math.max(max[0], subPathSum);
        return maxSinglePath;
    }
  
  
  
    /*
    * maxPathSumTopologicalSort => var map = new HashMap<TreeNode, Integer>(); map.put(null, 0): (store downward path sums per node; treat null as 0 to skip null checks), int max = Integer.MIN_VALUE: (track global maximum path sum), var stack = new Stack<TreeNode>(); topologicalSortDfs(root, stack): (perform DFS-based postorder topological sort so children are processed before parent), while(!stack.isEmpty()): (process nodes bottom-up), TreeNode node = stack.pop(): (take next node from bottom-up order), int left = map.get(node.left), int right = map.get(node.right): (fetch precomputed path sums), int maxSinglePath = Math.max(node.val, node.val + Math.max(left, right)): (best one-side path sum through node), int subPathSum = node.val + left + right: (path sum passing through node connecting left→node→right), max = Math.max(max, Math.max(subPathSum, maxSinglePath)): (update global max considering both local options), map.put(node, maxSinglePath): (store single-side sum for parent use), return max: (return global maximum path sum) → O(n) iterative (absolute O(2n)), O(n) space.
    *
    * Code(Reason):
    * - var map = new HashMap<TreeNode, Integer>(); map.put(null, 0): create map to store each node’s maximum downward path sum; mapping null to 0 avoids explicit null checks when accessing child sums.
    * - int max = Integer.MIN_VALUE: initialize global maximum path sum to smallest integer to handle all-negative node values safely.
    * - var stack = new Stack<TreeNode>(); topologicalSortDfs(root, stack): build a stack in postorder fashion so each node is processed only after its children.
    * - while(!stack.isEmpty()):
    *     - TreeNode node = stack.pop(): process nodes from bottom-up order.
    *     - int left = map.get(node.left), int right = map.get(node.right): retrieve left/right maximum downward path sums already computed.
    *     - int maxSinglePath = Math.max(node.val, node.val + Math.max(left, right)): compute best one-sided path sum (extendable upward to parent).
    *     - int subPathSum = node.val + left + right: compute path sum connecting left and right subtrees through current node.
    *     - max = Math.max(max, Math.max(subPathSum, maxSinglePath)): update global max considering both types of paths.
    *     - map.put(node, maxSinglePath): store computed single-side path for reuse by parent node’s computation.
    * - return max: output global maximum path sum across the tree.
    *
    * topologicalSortDfs(root, stack):
    * - if(root == null) return: base case.
    * - topologicalSortDfs(root.left, stack): recursively traverse left subtree first.
    * - topologicalSortDfs(root.right, stack): then traverse right subtree.
    * - stack.add(root): push node after visiting both children → ensures postorder (children before parent).
    *
    * topologicalSortBfs(root, stack):
    * - var queue = new LinkedList<TreeNode>(); queue.add(root): start BFS traversal.
    * - while(!queue.isEmpty()):
    *     - TreeNode node = queue.poll(): remove current node.
    *     - stack.add(node): store nodes in the same order for later reversal.
    *     - if(node.left != null) queue.add(node.left): enqueue left child.
    *     - if(node.right != null) queue.add(node.right): enqueue right child.
    * - After BFS, stack holds nodes top-down; processing in reverse (pop order) yields bottom-up (postorder equivalent).
    * - Alternatively, collect nodes into an ArrayList and iterate from end to start for the same bottom-up DP order.
    *
    * Rationale: Both DFS and BFS versions create a valid bottom-up (topologically sorted) processing order where children are guaranteed to be handled before parents. This mirrors recursion’s natural order but avoids call-stack depth limitations. Using a map to cache subtree results replaces recursion with iterative DP.
    *
    * Time Complexity: O(n) — one traversal to build topological order and one pass to compute path sums (absolute O(2n)).
    * Space Complexity: O(n) — map + stack or queue, replacing recursion stack from the recursive version.
    */

    public int maxPathSumTopologicalSort(TreeNode root) {
        var map = new HashMap<TreeNode, Integer>();
        map.put(null,0);
        int max = Integer.MIN_VALUE;
        var stack = new Stack<TreeNode>();
        topologicalSortDfs(root, stack);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            int left = map.get(node.left);
            int right = map.get(node.right);
            int maxSinglePath = Math.max(node.val, node.val+Math.max(left, right));
            int subPathSum = node.val + left + right;
            max = Math.max(max, Math.max(subPathSum, maxSinglePath));
            map.put(node, maxSinglePath);
        }
        return max;
    }

    public void topologicalSortDfs(TreeNode root, Stack<TreeNode> stack){
        if(root==null){
            return;
        }
        stack.add(root);
        topologicalSortDfs(root.left, stack);
        topologicalSortDfs(root.right, stack);
    } 

    public void topologicalSortBfs(TreeNode root, Stack<TreeNode> stack){
        var queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            stack.add(node);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }
}
