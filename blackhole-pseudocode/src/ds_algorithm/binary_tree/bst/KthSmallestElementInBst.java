/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree.bst;

import java.sql.Time;
import java.util.*;

import ds_algorithm.binary_tree.TreeNode;

//https://leetcode.com/problems/kth-smallest-element-in-a-bst

public class KthSmallestElementInBst {

    //brute
    public int kthSmallestInorderArrayBuildGetK(TreeNode root, int k) {
        var inorder = new ArrayList<TreeNode>();
        inorder(root, inorder);
        return inorder.get(k-1).val;
    }
    public void inorder(TreeNode node, List<TreeNode> inorder) {
        if(node==null){
            return;
        }
        inorder(node.left, inorder);
        inorder.add(node);
        inorder(node.right, inorder);
    }

  
    //APPROACH 1 DFS inorder traversal + k[], update and chek with k in in order block, use temp var for return and return if not null   
    
    //  Time: O(n) space: O(n) rec - tree might not be balanced
    // using inorder traversal in bst will always return a sorted array
    // using a counter[] to return
    //  usng Integer to identify null and not return
    public int kthSmallestBSTInorderTravSkipKWithArray(TreeNode root, int k) {
        return kthSmallest(root, new int[]{k});
    }
    public int kthSmallest(TreeNode node, int[] k) {
        if(node==null){
            return -1;
        }
        int left = kthSmallest(node.left, k);
        if(left!=-1){
            return left;
        }
        k[0]--;
        if(k[0]==0){
            return node.val;
        }
        int right = kthSmallest(node.right, k);
        return right;
    }
    
  
    //APPROACH 2 Ite inorder traversal with stack + k[], update k if k==0 return k
    // Iterative inorder traversal using stack
    // Time: O(n) space: O(n) stack - tree might not be balanced
    // root!=null || !s.empty() is very important
    // popping after we go left and then proceed to its right
    // and once popped we can increment i or decement k whatever suits
    public int kthSmallestInorderIteStackReturnKth(TreeNode root, int k) {
        var stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(node!=null || !stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            node = stack.pop();
            k--;
            if(k==0){
                return node.val;
            }
            node = node.right;
        }
        return -1;
    }


    /*
    * ============================= ALTERNATE APPROACHES NOTES =============================
    *
    * Approach 4 — Augmented BST (with subtree node counts)
    * ------------------------------------------------------------------------
    * - Idea: Augment each BST node with a field `count` = number of nodes in its left subtree (and optionally itself).
    * - This allows direct O(H) traversal to the k-th smallest element without visiting all nodes.
    * 
    * Example:
    *   if k == node.left.count + 1 → current node is k-th smallest.
    *   if k <= node.left.count → go to left subtree with same k.
    *   if k > node.left.count + 1 → go to right subtree with k adjusted as k - (node.left.count + 1).
    *
    * Time Complexity:
    *   - Query: O(H) in balanced BST (H = log n), O(n) worst-case for skewed tree.
    *   - Insert/Delete: O(H) to update counts along the path of modification.
    *
    * Why Insert/Delete Complexity Increases:
    *   - In a normal BST, insert/delete is O(H) — only navigation to the correct position matters.
    *   - In an Augmented BST, **each insert/delete requires updating the `count` field of every ancestor node** along the path.
    *     This is because insertion/deletion changes the size of subtrees, and counts must remain correct.
    *   - Thus, while navigation cost stays O(H), extra O(H) updates add overhead.
    *
    * Example:
    *   Inserting a node:
    *     1. Navigate to correct position → O(H).
    *     2. Increment `count` for every ancestor node in path → O(H).
    *     → Total: O(H) + O(H) = O(H), but with doubled work compared to a normal BST.
    *
    * Space Complexity:
    *   - O(n) extra storage for counts.
    *
    * Pros:
    *   - Extremely fast queries when there are multiple k-th smallest queries.
    *   - Low query time for large trees compared to repeated traversal approaches.
    *
    * Cons:
    *   - Increased complexity and time for insert/delete operations.
    *   - Additional memory overhead.
    *   - More error-prone due to count maintenance.
    *
    * Use case:
    *   - Best when tree is static or rarely modified and there are many k-th smallest queries.
    *
    *
    * Approach 5 — Morris Inorder Traversal (Easy Explanation)
    * ------------------------------------------------------------------------
    * - Think of Morris Traversal as “walking through the tree without a backpack.”
    *   Normally, in inorder traversal, we use a stack or recursion (which is like carrying a backpack of nodes we still need to visit).
    *   Morris Traversal instead temporarily changes the tree itself so we don’t need to carry a stack.
    *
    * - How it works:
    *   1. For the current node, if it has no left child → visit it and go to its right child.
    *   2. If it has a left child → find its inorder predecessor (rightmost node in left subtree).
    *      - If predecessor’s right pointer is null → set it to current node (temporary link) and go to the left child.
    *      - If predecessor’s right pointer points to current node → remove the temporary link, visit the node, and go to its right child.
    *
    * - The “temporary link” is the magic that avoids using extra space (stack or recursion).
    * - After traversal, the tree is restored exactly as it was.
    *
    * Time Complexity:
    *   - O(n) because every link (edge) in the tree is visited at most twice.
    *
    * Space Complexity:
    *   - O(1) extra space — no stack or recursion call stack needed.
    *
    * Pros:
    *   - Saves memory (important for huge trees).
    *   - No recursion/stack overhead.
    *
    * Cons:
    *   - Modifies tree temporarily (must be careful if the tree should remain unchanged during traversal).
    *   - More complex to implement compared to normal inorder traversal.
    *
    * Use case:
    *   - When memory is critical and we want O(1) space inorder traversal.
    *
    * Simple analogy:
    *   - Imagine you’re walking through a house and instead of carrying a map, you temporarily mark doorways to remember where to come back.
    *   - When done, you remove your marks and the house is exactly how it was before you started walking.
    *
    * GENERAL TRADE-OFFS FOR ALL APPROACHES:
    * ------------------------------------------------------------------------
    * | Approach                          | Time Complexity   | Space Complexity |
    * |-----------------------------------|--------------------|-------------------|
    * | Inorder array build               | O(n)               | O(n)              |
    * | Recursive inorder skip k          | O(H + k)          | O(H)              |
    * | Iterative stack inorder           | O(H + k)          | O(H)              |
    * | Augmented BST                     | O(H)               | O(n)              |
    * | Morris inorder traversal          | O(n)               | O(1)              |
    *
    * Key Notes:
    * - H = height of tree, n = number of nodes.
    * - For balanced BST: H = log n.
    * - For Augmented BST:
    *      - Query: O(H)
    *      - Insert/Delete: O(H) with extra work (count updates).
    * - Recursive approaches are easier to write but have recursion stack overhead.
    * - Iterative approaches avoid recursion but use an explicit stack.
    * - Augmented BST offers best query performance at the cost of update complexity.
    * - Morris traversal is optimal in space but complex to implement.
    *
    * UPDATE COMPLEXITY IN AUGMENTED BST:
    * - Insert/Delete: O(H) due to navigation + updating `count` along path.
    * - Insertion: increment counts for nodes along the path where insertion happens in the left subtree.
    * - Deletion: decrement counts similarly.
    * - This extra cost makes Augmented BST slower for frequent modifications but much faster for repeated k-th smallest queries.
    *
    * Recommendation for FAANG:
    * - For single k-th smallest query: recursive skip k or iterative stack approaches are clean and sufficient.
    * - For multiple queries: Augmented BST is optimal to demonstrate advanced knowledge.
    * - Mention Morris Traversal if asked for space optimization or O(1) space traversal.
    */

    
    public static void main(){
        
    }
}
