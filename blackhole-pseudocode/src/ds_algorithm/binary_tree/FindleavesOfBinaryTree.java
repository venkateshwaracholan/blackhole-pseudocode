package ds_algorithm.binary_tree;

import java.util.*;
// https://www.naukri.com/code360/problems/collect-leaves_1281192?leftPanelTabValue=PROBLEM

// ============================================================
// Problem Name: FindLeavesOfBinaryTree
// Alias: LeavesByRemoval
// ============================================================
//
// Problem Description (LeetCode-style):
// -------------------------------------
// Given a binary tree, collect and return all its leaves in stages. 
// Repeatedly remove all leaves, record them, and continue until the tree is empty.
//
// Example:
//      1
//     / \
//    2   3
//   / \
//  4   5
//
// Output: [[4,5,3],[2],[1]]
// Explanation: 
// - First leaves: 4,5,3 → remove them
// - Next leaf: 2 → remove it
// - Final leaf: 1 → remove it
//
// Input format:
// - TreeNode root
//
// Output format:
// - List<List<Integer>>, each inner list contains leaf values at each stage
//
// Constraints:
// - 1 <= number of nodes <= 1000
// - Node values are unique integers
// - Tree can be any shape (skewed, full, complete, etc.)
//
// Edge cases:
// - Empty tree (root == null) → return []
// - Single node → [[root.val]]
// - Skewed left/right trees
// - Very deep tree (watch recursion depth)
//
// ============================================================

public class FindleavesOfBinaryTree {

    // Definition for TreeNode
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    // ============================================================
    // 1) Brute Force Recursive Removal (modifies tree)
    // ============================================================
    public List<List<Integer>> findLeavesBrute(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        while(root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = removeLeaves(root, leaves);
            result.add(leaves);
        }

        return result;
    }

    private TreeNode removeLeaves(TreeNode node, List<Integer> leaves) {
        if(node == null) return null;
        if(node.left == null && node.right == null) {
            leaves.add(node.val);
            return null; // remove leaf
        }
        node.left = removeLeaves(node.left, leaves);
        node.right = removeLeaves(node.right, leaves);
        return node;
    }

    // ============================================================
    // 2) Iterative Remaining Nodes Approach (does not modify tree)
    // ============================================================
    public List<List<Integer>> findLeavesIterative(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> remaining = new ArrayList<>();
        collectAllNodesLeftToRight(root, remaining);

        while (!remaining.isEmpty()) {
            List<Integer> leaves = new ArrayList<>();
            List<TreeNode> nextRemaining = new ArrayList<>();
            for (TreeNode node : remaining) {
                boolean isLeaf = (node.left == null || !remaining.contains(node.left)) &&
                                (node.right == null || !remaining.contains(node.right));
                if (isLeaf) leaves.add(node.val);
                else nextRemaining.add(node);
            }
            remaining = nextRemaining;
            result.add(leaves);
        }

        return result;
    }

    // Collect all nodes in **left-to-right order** into a list
    private void collectAllNodesLeftToRight(TreeNode node, List<TreeNode> list) {
        if (node == null) return;
        list.add(node);
        collectAllNodesLeftToRight(node.left, list);
        collectAllNodesLeftToRight(node.right, list);
    }


    // ============================================================
    // 3) Optimal DFS Bottom-Up (assign height, single-pass)
    // ============================================================
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        collectLeaves(root, result);
        return result;
    }

    private int collectLeaves(TreeNode node, List<List<Integer>> result) {
        if(node==null){
            return 0;
        }
        int left = collectLeaves(node.left, result);
        int right = collectLeaves(node.right, result);
        int curHeight = Math.max(left, right);
        if(result.size()<=curHeight){
            result.add(new ArrayList<>());
        }
        result.get(curHeight).add(node.val);

        return curHeight+1;
    }

    // ============================================================
    // 4) Main Method with Tests
    // ============================================================
    public static void main(String[] args) {
        FindleavesOfBinaryTree sol = new FindleavesOfBinaryTree();

        // Example tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        TreeNode root1Clone = clone(root1);

        System.out.println("Brute Test1: " + sol.findLeavesBrute(root1));
        System.out.println("Iterative Test1: " + sol.findLeavesIterative(root1Clone));
        System.out.println("DFS Optimal Test1: " + sol.findLeaves(clone(root1Clone)));

        // Single node
        TreeNode root2 = new TreeNode(10);
        System.out.println("DFS Optimal Test2: " + sol.findLeaves(root2)); // [[10]]

        // Empty tree
        System.out.println("DFS Optimal Test3: " + sol.findLeaves(null)); // []

        // Skewed left tree
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println("DFS Optimal Test4: " + sol.findLeaves(root4)); // [[3],[2],[1]]
    }

    private static TreeNode clone(TreeNode root) {
        if(root == null) return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = clone(root.left);
        copy.right = clone(root.right);
        return copy;
    }

}
