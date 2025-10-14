package ds_algorithm.binary_tree.bst;
import ds_algorithm.binary_tree.TreeNode;

    

public class MorrisTraversalInorderNoStack {

    /*
    * ============================= MORRIS TRAVERSAL EXPLANATION =============================
    *
    * Key Terms:
    * - current node: The node we are currently visiting in traversal.
    * - predecessor: The rightmost node in current's left subtree (in inorder sense).
    * - right-mode node: The predecessor's right pointer, temporarily linked to the current node
    *   to simulate a return path without using a stack or recursion.
    *
    * How linking works:
    * -------------------
    * In Morris Traversal, we avoid recursion/stack by creating a temporary "thread"
    * from a predecessor's right pointer back to the current node. This allows us to
    * return to the current node after completing traversal of its left subtree.
    *
    * Example Tree:
    *
    *           10
    *         /    \
    *        5      15
    *       / \    /  \
    *      2   7  12  20
    *     /     \
    *    1       9
    *
    * Step-by-step Example:
    *
    * 1. Start at root (current = 10)
    *    - current.left exists (5) → find predecessor of 10 in left subtree:
    *      while (predecessor.right != null && predecessor.right != current) { predecessor = predecessor.right; }
    *      → predecessor = node 9 (rightmost node in left subtree of 10).
    *    - predecessor.right is null → create thread: predecessor.right = current (9.right = 10).
    *    - Move current = current.left (current = 5).
    *
    * 2. current = 5
    *    - current.left exists (2) → find predecessor of 5:
    *      while (predecessor.right != null && predecessor.right != current) { predecessor = predecessor.right; }
    *      → predecessor = node 2 (rightmost node in left subtree of 5).
    *    - predecessor.right is null → thread: 2.right = 5.
    *    - Move current = current.left (current = 2).
    *
    * 3. current = 2
    *    - current.left exists (1) → find predecessor of 2:
    *      while (predecessor.right != null && predecessor.right != current) { predecessor = predecessor.right; }
    *      → predecessor = node 1 (rightmost node in left subtree of 2).
    *    - predecessor.right is null → thread: 1.right = 2.
    *    - Move current = current.left (current = 1).
    *
    * 4. current = 1
    *    - current.left is null → visit node (print 1).
    *    - Move current = current.right → thread to node 2.
    *
    * 5. current = 2 (via thread)
    *    - current.left exists (1) has left child so goes to else branch in Morris Traversal.
    *    - predecessor.right == current → thread exists, meaning we are returning from left subtree.
    *      when a node has a left child (to simulate a return path without stack/recursion).
    *    - If current.left == null, there is no predecessor thread and we simply visit and move right.
    *
    * Code(Reason):
    *    - if (current.left == null):
    *        visit(current): no left child means no predecessor → visit directly.
    *        current = current.right: move to right subtree.
    *    - else:
    *        predecessor = current.left: find rightmost node in left subtree.
    *        while (predecessor.right != null && predecessor.right != current):
    *            predecessor = predecessor.right: find predecessor.
    *        if (predecessor.right == null):
    *            predecessor.right = current: create thread to return later.
    *            current = current.left: traverse left subtree.
    *        else:
    *            predecessor.right = null: remove thread → returning from left subtree.
    *            visit(current): visit after left subtree is done.
    *            current = current.right: proceed to right subtree.
    *
    * Result:
    * Inorder Traversal: 1 2 5 7 9 10 12 15 20
    *
    * Advantages:
    * - O(1) space traversal (no recursion/stack).
    *
    * Caveats:
    * - Modifies tree temporarily → must ensure threads are removed.
    * - More complex logic compared to recursive or iterative stack approaches.
    *
    * =================================================================
    */

    public static void morrisInorder(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // Case 1: No left child — visit node and move right
                System.out.print(current.val + " ");
                current = current.right;
            } else {
                // Case 2: Has left child — find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // MODIFY: Create temporary thread to current
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // UNMODIFY: Remove temporary thread and visit current
                    predecessor.right = null;
                    System.out.print(current.val + " ");
                    current = current.right;
                }
            }
        }
    }

    // Utility to build the big example tree
    public static TreeNode buildExampleTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);

        root.left.left.left = new TreeNode(1);
        root.left.right.right = new TreeNode(9);

        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildExampleTree();

        System.out.println("Morris Inorder Traversal of Large Tree:");
        morrisInorder(root);

        System.out.println("\nExpected: 1 2 5 7 9 10 12 15 20");
    }
}

