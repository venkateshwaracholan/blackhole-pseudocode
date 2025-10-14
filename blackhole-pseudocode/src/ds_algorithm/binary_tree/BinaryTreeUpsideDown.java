package ds_algorithm.binary_tree;

// https://www.naukri.com/code360/problems/upside-down-binary-tree_1281853?leftPanelTabValue=PROBLEM


// ============================================================
// Problem Name: BinaryTreeUpsideDown
// Alias: UpsideDownBinaryTree
// ============================================================
//
// Problem Description (LeetCode-style):
// -------------------------------------
// Given a binary tree where every right node is either a leaf with a sibling
// (i.e., it has a left sibling) or null, flip the tree upside down and return
// the new root.
//
// More concretely, for a binary tree that looks like:
//     1
//    / \
//   2   3
//  / \
// 4   5
//
// The flipped tree should be:
//    4
//   / \
//  5   2
//     / \
//    3   1
//
// Input format:
// - A binary tree root node (TreeNode).
//
// Output format:
// - The new root (TreeNode) of the transformed tree.
//
// Constraints & realistic values:
// - The tree nodes count n can be from 0 up to typical interview constraints (e.g., 10^4).
// - Node values fit in int range.
// - The tree structure satisfies the condition that every right child is either a leaf
//   with a left sibling or null (the LeetCode constraint for this specific problem).
//
// Edge cases explicitly listed:
// - Empty input (root == null) → return null.
// - Single node tree (no children) → return the same node.
// - Linear left-leaning tree (every node has only left child).
// - Tree with right leaves as described by problem constraints.
// - Duplicated values in nodes (values don't affect structure).
// - Very deep tree (watch recursion depth; iterative is safer).
// - Repeated calls on already flipped tree (idempotence is not guaranteed).
//
// Recap constraints:
// - n = number of nodes, typically up to 10^4 in interviews.
// - Node values in int range.
// - Structure requirement: right nodes either null or leaf with left sibling.
//
// ============================================================
// Solutions, discussion, and code (Brute → Optimal, FAANG-ready)
// ============================================================
import java.util.* ;
public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTreeBrute(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        // Push all left children
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        // Leftmost becomes new root
        TreeNode leftmost = stack.pop();
        TreeNode newRoot = new TreeNode(leftmost.val);
        cur = newRoot;

        // Rebuild tree upward
        while (!stack.isEmpty()) {
            TreeNode parent = stack.pop();
            if (parent.right != null) {
                cur.left = new TreeNode(parent.right.val);
            }
            cur.right = new TreeNode(parent.val);
            cur = cur.right;
        }

        return newRoot;
    }


    // ============================================================
    // 2) Optimal In-place Recursive (clean, O(n) time, O(h) recursion space)
    // ============================================================
    //
    // Idea (key insight):
    // - Recursively process the left subtree until the leftmost node.
    // - That leftmost node becomes the new root.
    // - While unwinding recursion, for each node:
    //     node.left.left  = node.right;
    //     node.left.right = node;
    //   and then set node.left = null, node.right = null (cleanup).
    //
    // This flips pointers in-place, requires no extra node allocations.
    //
    // Complexity:
    // - Time: O(n) — each node visited once.
    // - Space: O(h) recursion stack (worst-case h = n).
    //
    public TreeNode upsideDownBinaryTreeRecursive(TreeNode root) {
        if (root == null || root.left == null) {
            // root is null or leftmost/root becomes itself
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTreeRecursive(root.left);
        // root.left is the node we are processing upon unwinding
        TreeNode left = root.left;
        left.left = root.right; // original right becomes new left
        left.right = root;      // original parent becomes new right
        // clean up original pointers
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // ============================================================
    // 3) Optimal Iterative (in-place, O(n) time, O(1) extra space)
    // ============================================================
    //
    // Idea:
    // - Iterate down the left spine, rewiring pointers as we go using three helper pointers:
    //    - curr : current node being processed
    //    - prev : previous node (becomes right child)
    //    - right : previous right child (becomes left child)
    //
    // Pseudocode:
    // prev = null; right = null; curr = root;
    // while(curr != null) {
    //   next = curr.left;
    //   curr.left = right;
    //   right = curr.right;
    //   curr.right = prev;
    //   prev = curr;
    //   curr = next;
    // }
    // return prev; // new root
    //
    // Complexity:
    // - Time: O(n)
    // - Space: O(1) extra
    //
    public TreeNode upsideDownBinaryTreeIterative(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode right = null;
        while (curr != null) {
            next = curr.left;        // move down left
            curr.left = right;       // new left is previously recorded right
            right = curr.right;      // update right to current's original right
            curr.right = prev;       // new right is previous node (parent in original)
            prev = curr;             // move prev forward
            curr = next;             // continue with left child
        }
        return prev;
    }

    // ============================================================
    // 4) Alternate Paradigms Summary
    // ============================================================
    //
    // - Brute (Reconstruct): deep-copy nodes to preserve original; useful if mutation is disallowed.
    // - Recursive (In-place): elegant, easy to reason about; uses recursion stack (O(h)).
    // - Iterative Two-Pointer (In-place): constant extra space; preferred for very deep trees to avoid recursion overflow.
    //
    // Distinct insights:
    // - Brute emphasizes immutability at the cost of memory.
    // - Recursive leverages unwind to wire children to parents.
    // - Iterative converts recursion logic into pointer juggling (two-pointers style).
    //
    // ============================================================
    // 5) Brute-to-Optimal Comparison Table
    // ============================================================
    // - Brute:
    //   * Time: O(n) (absolute: process each node once)
    //   * Space: O(n) (absolute: n new nodes + recursion stack)
    //   * Strengths: Preserves original tree, straightforward
    //   * Weaknesses: High memory; slower GC costs
    //
    // - Recursive In-place:
    //   * Time: O(n) (absolute: visit each node once)
    //   * Space: O(h) (recursion stack)
    //   * Strengths: Clean and concise
    //   * Weaknesses: Recursion depth issue for very deep trees
    //
    // - Iterative In-place:
    //   * Time: O(n)
    //   * Space: O(1) extra
    //   * Strengths: No recursion, minimal memory
    //   * Weaknesses: Slightly trickier to reason about; pointer juggling
    //
    // ============================================================
    // 6) Complexity Comparison Table (summary)
    // ============================================================
    // Approach               | Time (interview) | Space (interview) | Absolute expressions
    // -----------------------|------------------|-------------------|---------------------
    // Brute (reconstruct)    | O(n)             | O(n)              | time: n, space: n
    // Recursive (in-place)   | O(n)             | O(h)              | time: n, space: h
    // Iterative (in-place)   | O(n)             | O(1)              | time: n, space: 1
    //
    // ============================================================
    // 7) Interview Talking Points (1-2 sentence pitch)
    // ============================================================
    // - Pitch: Transform by rotating the left spine: the leftmost node becomes the new root;
    //   while unwinding (recursively) or iterating we reassign child/parent pointers so that
    //   left child's left becomes original right and left child's right becomes original parent.
    // - Trade-off: Recursive is most readable; iterative is constant-space and preferred for deep trees.
    //
    // ============================================================
    // 8) Follow-up / Scaling / Discussion Points
    // ============================================================
    // - Scaling to large N: prefer iterative approach to avoid recursion stack overflow.
    // - Memory vs speed: brute reconstruction uses more memory but preserves the original tree.
    // - Repeated queries: if you need many flips, consider caching results or not flipping in-place.
    // - Real-world handling: tree node validation, null checks, and safe mutation are necessary.
    // - Parallelization: flipping is inherently sequential along left spine; parallelization is not natural for in-place.
    //
    // ============================================================
    // 9) Tests / Main method
    // ============================================================
    //
    // We'll include a set of tests with expected outputs in comments.
    //
    public static void main(String[] args) {
        BinaryTreeUpsideDown solver = new BinaryTreeUpsideDown();

        // Helper to print tree level-order for verification
        java.util.function.Consumer<TreeNode> printLevel = root -> {
            if (root == null) {
                System.out.println("null");
                return;
            }
            java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
            q.add(root);
            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                int sz = q.size();
                boolean any = false;
                for (int i = 0; i < sz; i++) {
                    TreeNode n = q.poll();
                    if (n == null) {
                        sb.append("null ");
                        q.add(null); // preserve structure but prevent infinite loop by trimming later
                    } else {
                        sb.append(n.val).append(" ");
                        q.add(n.left);
                        q.add(n.right);
                        any = any || (n.left != null) || (n.right != null);
                    }
                }
                if (!any) break;
            }
            System.out.println(sb.toString().trim());
        };

        // Test 1: Example from problem
        // Build tree: 1 / \ 2 3; 2 / \ 4 5
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        // Expected flipped root value = 4, structure printed shows hierarchy
        System.out.println("Original (Test1):");
        printLevel.accept(root1);
        TreeNode resRec = solver.upsideDownBinaryTreeRecursive(cloneTree(root1)); // use clone to preserve original
        System.out.println("Recursive flipped (Test1):");
        printLevel.accept(resRec); // Expected something like "4 5 2 3 1"
        TreeNode resIt = solver.upsideDownBinaryTreeIterative(cloneTree(root1));
        System.out.println("Iterative flipped (Test1):");
        printLevel.accept(resIt);

        // Test 2: Single node
        TreeNode root2 = new TreeNode(10);
        System.out.println("Original (Test2):");
        printLevel.accept(root2);
        System.out.println("Flipped (Test2) recursive:");
        printLevel.accept(solver.upsideDownBinaryTreeRecursive(cloneTree(root2))); // Expected same single node
        System.out.println("Flipped (Test2) iterative:");
        printLevel.accept(solver.upsideDownBinaryTreeIterative(cloneTree(root2))); // same

        // Test 3: Linear left-leaning tree: 1 -> 2 -> 3 -> 4
        TreeNode r3 = new TreeNode(1);
        r3.left = new TreeNode(2);
        r3.left.left = new TreeNode(3);
        r3.left.left.left = new TreeNode(4);
        System.out.println("Original (Test3):");
        printLevel.accept(r3);
        System.out.println("Flipped (Test3) iterative:");
        printLevel.accept(solver.upsideDownBinaryTreeIterative(cloneTree(r3))); // new root should be 4

        // Test 4: Empty tree
        System.out.println("Original (Test4): null");
        System.out.println("Flipped (Test4): " + solver.upsideDownBinaryTreeIterative(null)); // should be null

        // Test results are shown as level-order prints; verify visually.
        // ✅ If they match expected shapes, tests pass.
    }

    // Helper clone to avoid mutating original in tests
    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode r = new TreeNode(root.val);
        r.left = cloneTree(root.left);
        r.right = cloneTree(root.right);
        return r;
    }

    // ============================================================
    // 10) Comments for Recall / Discussion Table
    // ============================================================
    // - Key Idea (Recursive): Recurse to leftmost, then wire left.left = right, left.right = node.
    // - Key Idea (Iterative): Slide down left spine, use prev/right pointers to reassign children.
    // - Use iterative when recursion depth may be large (stack overflow risk).
    // - Use brute-copy when the original tree must be preserved.
    //
    // ============================================================
    // 11) Normalization Note
    // ============================================================
    // - Not applicable (operating on structure, not strings).
    //
    // ============================================================
    // 12) Advanced Expectations (FAANG completeness)
    // - Defensive input validation: handle null roots.
    // - Explain trade-offs between in-place mutation vs copying.
    // - Discuss recursion limits and prefer iterative for depth > ~10^4.
    // - For concurrent environments ensure synchronization if tree is shared.
    //
    // ============================================================
}

