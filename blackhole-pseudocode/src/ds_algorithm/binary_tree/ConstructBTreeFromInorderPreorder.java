/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
import java.util.*;

/*

* 0. preorder[0] is the root of tree
* 1. Iterate 0->len - each element of preorder 
* 2. Find the idx of that element in inorder
* Elements that are left to the idx are left sub-tree and right are right-subtree to that element
* 3. If you now divide the remaining elements in pre-order array into two, 
* the elements immediate to identified idx are part of left sub-tree and at the end it is right tree elements
* 4. recursively add left and right from preorder

*/ 

public class ConstructBTreeFromInorderPreorder {


    /*
    * ONE LINER =>BRUTE inorderHashmapPreorderIterationBuildTree  :  var inorderMap = new HashMap<>(): (create map for O(1) lookup of inorder indices); for(int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i): (populate map with inorder value→index); TreeNode root = new TreeNode(preorder[0]): (create root node from first preorder element); for(int i = 1; i < preorder.length; i++) buildTree(root, preorder[i], inorderMap): (iterate preorder and insert each element into tree); buildTree(root, elem, inMap): int rootIdx = inMap.get(root.val): (get inorder index of current node), int elemIdx = inMap.get(elem): (get inorder index of element to insert), if(elemIdx < rootIdx) if(root.left == null) root.left = new TreeNode(elem): (insert element as left child if empty), else buildTree(root.left, elem, inMap): (recurse into left subtree), else if(root.right == null) root.right = new TreeNode(elem): (insert element as right child if empty), else buildTree(root.right, elem, inMap): (recurse into right subtree) → O(n²), O(n) space.
    *
    * Code(Reason):
    * - var inorderMap = new HashMap<>(): create a hashmap to store inorder value→index mappings for O(1) lookups during insertion.
    * - for(int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i): populate the hashmap with inorder array indices for quick access.
    * - TreeNode root = new TreeNode(preorder[0]): take the first preorder element as root.
    * - for(int i = 1; i < preorder.length; i++) buildTree(root, preorder[i], inorderMap): iterate over remaining preorder elements inserting each into the correct position in the tree.
    * - buildTree(root, elem, inMap):
    *     - int rootIdx = inMap.get(root.val): fetch inorder index of current node to compare positions.
    *     - int elemIdx = inMap.get(elem): fetch inorder index of element to insert.
    *     - if(elemIdx < rootIdx):
    *         - if(root.left == null) root.left = new TreeNode(elem): insert element as left child if position is empty.
    *         - else buildTree(root.left, elem, inMap): recurse into left subtree to find correct insertion spot.
    *     - else:
    *         - if(root.right == null) root.right = new TreeNode(elem): insert element as right child if position is empty.
    *         - else buildTree(root.right, elem, inMap): recurse into right subtree to find correct insertion spot.
    *
    * Rationale: This brute-force approach inserts each preorder element one-by-one into the tree, using inorder indices to decide whether it belongs to the left or right subtree. This avoids slicing arrays or using global state, but requires repeated traversal for each insertion.
    *
    * Time Complexity: O(n²) — in worst case of a skewed tree each insertion may traverse up to O(n) depth, repeated for n nodes.
    * Space Complexity: O(n) — O(n) for the tree storage plus O(n) recursion stack in worst case.
    */

    public TreeNode inorderHashmapPreorderIterationBuildTree(int[] preorder, int[] inorder) {
        var inorderMap = new HashMap<Integer, Integer>();
        for(int i=0;i<inorder.length;i++){
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        for(int i=1;i<preorder.length;i++){
            buildTree(root, preorder[i], inorderMap);
        }
        return root;
    }
    public void buildTree(TreeNode root, int elem, Map<Integer,Integer> inMap){
        int rootIdx = inMap.get(root.val);
        int elemIdx = inMap.get(elem);
        if(elemIdx<rootIdx){
            if(root.left==null){
                root.left = new TreeNode(elem);
            }
            else{
                buildTree(root.left, elem, inMap);
            }
        }
        else{
            if(root.right==null){
                root.right = new TreeNode(elem);
            }else{
                buildTree(root.right, elem, inMap);
            }
        }
    }



  
    /*
    * inorderHashmapPreorderRecursion => var inorderMap = new HashMap<>(): (create map for O(1) lookup of inorder indices); for(int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i): (populate map with inorder value→index); return buildTree(preorder, inorderMap, 0, preorder.length-1, new int[]{0}): (start recursion with full inorder range [0,n-1] because the entire tree must be built initially and track preorder index); buildTree(preorder, inMap, left, right, prIdx): if(left > right) return null: (base case, no subtree in given inorder range), int root = preorder[prIdx[0]]: (take current preorder element as root), int inIdx = inMap.get(root): (find inorder index of root to split inorder into left and right subtrees), TreeNode node = new TreeNode(root): (create node), prIdx[0]++: (move to next preorder index), node.left = buildTree(preorder, inMap, left, inIdx-1, prIdx): (left subtree range [left,inIdx-1] excludes root index because in inorder traversal all nodes before root belong to left subtree and idx is excluded to avoid duplication or infinite recursion), node.right = buildTree(preorder, inMap, inIdx+1, right, prIdx): (right subtree range [inIdx+1,right] excludes root index because in inorder traversal all nodes after root belong to right subtree and idx is excluded to avoid duplication or infinite recursion), return node: (return constructed subtree) → O(n), O(n) space.
    *
    * Code(Reason):
    * - var inorderMap = new HashMap<>(): create a map to store inorder value→index for O(1) lookups during recursion.
    * - for(int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i): populate the hashmap with inorder array indices for fast access.
    * - return buildTree(preorder, inorderMap, 0, preorder.length-1, new int[]{0}): start recursion with entire inorder range [0, n-1] because the whole tree must be built initially, and preorder index tracker array to maintain current root position without globals.
    * - buildTree(preorder, inMap, left, right, prIdx):
    *     - if(left > right) return null: base case — when there are no elements in the given inorder range, subtree is empty.
    *     - int root = preorder[prIdx[0]]: pick current preorder element as root value.
    *     - int inIdx = inMap.get(root): get inorder index of root to determine subtree boundaries.
    *     - TreeNode node = new TreeNode(root): create a new TreeNode with root value.
    *     - prIdx[0]++: increment preorder index so next recursive call uses the next preorder element.
    *     - node.left = buildTree(preorder, inMap, left, inIdx-1, prIdx): recurse to build left subtree using inorder range [left, inIdx-1]; all nodes in this range appear before root in inorder traversal and hence belong to left subtree, and we exclude root index itself to avoid including the root again, which would cause duplication or infinite recursion.
    *     - node.right = buildTree(preorder, inMap, inIdx+1, right, prIdx): recurse to build right subtree using inorder range [inIdx+1, right]; all nodes in this range appear after root in inorder traversal and hence belong to right subtree, and we exclude root index itself to avoid duplication or infinite recursion.
    *     - return node: return the root of constructed subtree.
    *
    * Rationale: Passing inorder range boundaries allows recursion to precisely define the scope of left and right subtrees without physically slicing arrays. The inIdx splits the inorder array logically: left subtree range is [left, inIdx-1], right subtree range is [inIdx+1, right]. This ensures that the root itself is not reconsidered for either subtree and recursion stops correctly when left > right.
    *
    * Time Complexity: O(n) — each node is processed exactly once.
    * Space Complexity: O(n) — for recursion stack in worst case and inorder index map storage.
    */

    public TreeNode inorderHashmapPreorderRecursion(int[] preorder, int[] inorder) {
        var inorderMap = new HashMap<Integer, Integer>();
        for(int i=0;i<inorder.length;i++){
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorderMap, 0, preorder.length-1, new int[]{0});
    }
    public TreeNode buildTree(int[] preorder, Map<Integer,Integer> inMap, int left, int right, int[] prIdx){
        if(left>right){
            return null;
        }
        int root = preorder[prIdx[0]];
        int inIdx = inMap.get(root);
        TreeNode node = new TreeNode(root);
        prIdx[0]++;
        node.left = buildTree(preorder, inMap, left, inIdx-1, prIdx);
        node.right = buildTree(preorder, inMap, inIdx+1, right, prIdx);
        return node;
    }

    
  
    
    //APPROACH 3 DFS inorder traversal+ boundary, if boundry found in inordr return null, send val as b in left, parent b to right, inc iorder idx in inorder block
    
    // using inorder traversal -> left root right
    // we use a boundary
    // for left we pass val as boundary
    // for right we pass inf as boundary
    // when left most val matches int[0] return null;
    // increment i in the inroder block
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, new int[]{0},new int[]{0},Integer.MAX_VALUE);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder, int[] prIdx, int[] inIdx, int boundary){
        if(prIdx[0]==preorder.length || boundary == inorder[inIdx[0]]){
            return null;
        }
        int rootVal = preorder[prIdx[0]];
        TreeNode node = new TreeNode(rootVal);
        prIdx[0]++;
        node.left = buildTree(preorder, inorder, prIdx, inIdx, rootVal);
        inIdx[0]++;
        node.right = buildTree(preorder, inorder, prIdx, inIdx, boundary);
        return node;
    }
    
    
    //APPROACH 4 preorder Ite+stack, while stack val == inorder remove from stack and assign to prev, create node, 
    // if prev null assinn to stack peek, or assign to right, push n to stack
    
    // grab first node and create root
    // iterate rest, create a stack
    // in the loop assign prev = null
    // until stack not empty and stack top has inorder[x] pop and asssign to prev and inc x;
    // create a node
    // if prev is null put node to stack peek's left
    // else put to right
    // push node to stack
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        var stack = new Stack<TreeNode>();
        var root = new TreeNode(preorder[0]);
        stack.add(root);
        int inIdx = 0;
        for(int i=1;i<preorder.length;i++){
            TreeNode parent = null;
            while(!stack.isEmpty() && stack.peek().val==inorder[inIdx]){
                parent = stack.pop();
                inIdx++;
            }
            TreeNode node = new TreeNode(preorder[i]);
            if(parent==null){
                stack.peek().left = node;
            }
            else{
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
        
    }
    
    
    
  
  
  public static void main(String args[]){
    buildTreeBstWay(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
  }
}
