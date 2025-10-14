/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ds_algorithm.binary_tree.TreeNode;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

public class LowestCommonAncestorOFBinarySearchTree {
    
    /*
    * ONE LINER => lowestCommonAncestorDfsBottomUpBSTBrute: if(root==null||p==root||q==root)return root to handle base case (null node or found p/q), TreeNode left=lowestCommonAncestorDfsBottomUp(root.left,p,q) and TreeNode right=lowestCommonAncestorDfsBottomUp(root.right,p,q) to recursively search left/right subtrees without BST property optimization, if(left!=null&&right!=null)return root because p and q found in different subtrees making root the LCA, if(left!=null)return left to propagate found target upwards, else return right to propagate found target or null if neither found → O(n) time, O(h) space for recursion stack where h=tree height.
    *
    * Code(Reason):
    * - if(root==null||p==root||q==root)return root: base case; end search if subtree is empty, or immediately return p/q if found to avoid unnecessary recursion.
    * - TreeNode left=lowestCommonAncestorDfsBottomUp(root.left,p,q): search left subtree without using BST ordering to locate p/q.
    * - TreeNode right=lowestCommonAncestorDfsBottomUp(root.right,p,q): search right subtree similarly.
    * - if(left!=null&&right!=null)return root: if both left and right subtrees return non-null, p and q are in different subtrees so current root is the LCA.
    * - if(left!=null)return left: if only left subtree returns a target node, propagate it upward as potential LCA.
    * - return right: if only right subtree returns a target node or both are null, propagate it upward (null if both null).
    *
    * Rationale: This brute‑force approach ignores BST properties and treats the tree as a binary tree, performing a post‑order DFS to find p and q in separate subtrees. It finds the first split point (lowest common ancestor) without assumptions about ordering. Base cases prevent unnecessary recursion and ensure correctness.
    *
    * Time Complexity: O(n) — visits every node in the worst case.
    * Space Complexity: O(h) — recursion stack space, where h = tree height (O(n) worst case for skewed tree).
    */
    public TreeNode lowestCommonAncestorDfsBottomUp(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || p==root || q==root){
            return root;
        }
        TreeNode left = lowestCommonAncestorDfsBottomUp(root.left, p, q);
        TreeNode right = lowestCommonAncestorDfsBottomUp(root.right, p, q);
        if(left!=null && right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        return right;
    }
    
    /*
    * ONE LINER => lcaRootToLeafwithBstProperty: create var pathP=new ArrayList<TreeNode>(), pathQ=new ArrayList<TreeNode>() to store root→p and root→q paths, call rootToLeaf(root,p,pathP) and rootToLeaf(root,q,pathQ) using BST property to avoid backtracking, iterate with int i=0;i<pathP.size()&&i<pathQ.size();i++ comparing pathP.get(i).val!=pathQ.get(i).val, break on mismatch to find deepest common node, return pathP.get(i-1) as LCA since mismatch means divergence point, rootToLeaf(node,target,path): path.add(node) to record node, if(node==target)return: found target, if(target.val<node.val)rootToLeaf(node.left,target,path) else rootToLeaf(node.right,target,path) to follow BST property for directed search → O(h) time (h=tree height), O(h) space for path storage.
    *
    * Code(Reason):
    * - var pathP=new ArrayList<TreeNode>(), pathQ=new ArrayList<TreeNode>(): initialize lists to store root→target paths using BST directed search.
    * - rootToLeaf(root,p,pathP), rootToLeaf(root,q,pathQ): construct paths by traversing tree following BST ordering (left if target.val<node.val else right) without backtracking/removal.
    * - int i=0; for(;i<pathP.size()&&i<pathQ.size();i++){if(pathP.get(i).val!=pathQ.get(i).val)break;}: compare nodes level by level, stop at first mismatch because that marks path divergence.
    * - return pathP.get(i-1): return last common node before mismatch as LCA since first mismatch marks divergence point.
    * - rootToLeaf(TreeNode node,TreeNode target,List<TreeNode> path):
    *   - path.add(node): add current node to path.
    *   - if(node==target)return: target found, stop recursion.
    *   - if(target.val<node.val)rootToLeaf(node.left,target,path) else rootToLeaf(node.right,target,path): follow BST property to direct search efficiently.
    *
    * Rationale: By exploiting BST property, we avoid unnecessary traversal and backtracking. Paths to p and q are constructed directly with O(h) time complexity, compared to brute‑force binary tree approach. This ensures more efficient path construction, with no need to remove nodes on recursion unwind.
    *
    * Time Complexity: O(2h) — height of BST; in balanced BST O(log n), in skewed O(n).
    * Space Complexity: O(2h) — path storage and recursion stack.
    */
    public TreeNode lcaRootToLeafwithBstProprty(TreeNode root, TreeNode p, TreeNode q) {
        var pathP = new ArrayList<TreeNode>();
        var pathQ = new ArrayList<TreeNode>();
        rootToLeaf(root, p,pathP);
        rootToLeaf(root, q,pathQ);
        int i=0;
        for(;i<pathP.size() && i<pathQ.size(); i++){
            if(pathP.get(i).val!=pathQ.get(i).val){
                break;
            }
        }
        return pathP.get(i-1);
    }

    public void rootToLeaf(TreeNode node, TreeNode target, List<TreeNode> path){
        path.add(node);
        if(node==target){
            return;
        }
        if(target.val<node.val){
            rootToLeaf(node.left, target, path);
        }
        else{
            rootToLeaf(node.right, target, path);
        }
    }




    /*
    * ONE LINER => lowestCommonAncestorParentMapBstProperty: create var parentMapP=new HashMap<TreeNode,TreeNode>() and var parentMapQ=new HashMap<TreeNode,TreeNode>() to store child→parent mappings for p and q separately, call buildParentMap(root,null,p,parentMapP) and buildParentMap(root,null,q,parentMapQ) to build parent maps using BST property pruning, create var pSet=new HashSet<TreeNode>() to store p’s ancestors, iterate for(TreeNode t=p;t!=null;t=parentMapP.get(t))pSet.add(t) to add all ancestors of p to set, iterate while(!pSet.contains(q))q=parentMapQ.get(q) to walk q’s ancestors until first intersection with p’s ancestor set, return q as LCA; buildParentMap(node,parent,target,map): if(node==null)return to stop, map.put(node,parent) to store parent link, if(node==target)return to stop traversal, else if(target.val<node.val)recurse left child, else recurse right child → O(h) time per map build in balanced BST (O(h) total), O(h) space per map.
    *
    * Code(Reason):
    * - var parentMapP=new HashMap<TreeNode,TreeNode>(), var parentMapQ=new HashMap<TreeNode,TreeNode>(): initialize separate parent maps for p and q to allow independent path tracing in BST without missing nodes.
    * - buildParentMap(root,null,p,parentMapP), buildParentMap(root,null,q,parentMapQ): build parent maps for p and q using BST pruning so only relevant ancestor paths are stored.
    * - var pSet=new HashSet<TreeNode>(): initialize set to store ancestors of p for O(1) lookup.
    * - for(TreeNode t=p;t!=null;t=parentMapP.get(t))pSet.add(t): walk upward from p to root, adding each ancestor to pSet.
    * - while(!pSet.contains(q))q=parentMapQ.get(q): walk upward from q until finding a node that is in p’s ancestor set; the first such node is the LCA.
    * - return q: return the lowest common ancestor found.
    *
    * buildParentMap(TreeNode node,TreeNode parent,TreeNode target,Map<TreeNode,TreeNode> map):
    *   - if(node==null)return: base case; no more nodes to visit.
    *   - map.put(node,parent): store parent mapping for current node.
    *   - if(node==target)return: target found, no further recursion needed.
    *   - else if(target.val<node.val)buildParentMap(node.left,node,target,map): if target is smaller, traverse left child in BST.
    *   - else buildParentMap(node.right,node,target,map): otherwise, traverse right child in BST.
    *
    * Rationale: This method constructs separate parent maps for p and q using BST property to prune traversal, ensuring only relevant ancestor paths are stored. By walking upward from p and q independently, we ensure both paths are complete. The first intersection of p’s ancestor set with q’s upward path gives the LCA. Separate maps are necessary because pruning could skip one target’s path if both were stored together.
    *
    * Time Complexity: O(2h) for each buildParentMap call in balanced BSTs (h=tree height), so O(h) total.
    * Space Complexity: O(2h) for each parent map and ancestor set, so O(h) total.
    */

    public TreeNode lowestCommonAncestorParentMapBstPropety(TreeNode root, TreeNode p, TreeNode q) {
        var parentMapP = new HashMap<TreeNode,TreeNode>();
        var parentMapQ = new HashMap<TreeNode,TreeNode>();
        buildParentMap(root, null, p, parentMapP);
        buildParentMap(root, null, q, parentMapQ);
        var pSet = new HashSet<TreeNode>();
        for(TreeNode t=p; t!=null; t=parentMapP.get(t)){
            pSet.add(t);
        }
        while(!pSet.contains(q)){
            q = parentMapQ.get(q);
        }
        return q;
    }

    public void buildParentMap(TreeNode node, TreeNode parent, TreeNode target,  Map<TreeNode,TreeNode> map){
        if(node==null){
            return;
        }
        map.put(node, parent);
        if(node==target){
            return;
        }
        else if(target.val<node.val){
            buildParentMap(node.left, node, target, map);
        }
        else{
            buildParentMap(node.right, node, target, map);
        }
    }



    /*
    * ONE LINER => lowestCommonAncestorOptimalRecursionTopDown: if(root==null)return root to handle empty subtree base case, if(p.val<root.val && q.val<root.val) return lowestCommonAncestorOptimalRecursionTopDown(root.left,p,q) to recurse into left subtree when both targets are smaller than root, else if(p.val>root.val && q.val>root.val) return lowestCommonAncestorOptimalRecursionTopDown(root.right,p,q) to recurse into right subtree when both targets are greater than root, else return root when p and q lie on different sides (or one matches root) as that is the LCA → O(h) time, O(h) space for recursion stack in balanced BST.
    *
    * Code(Reason):
    * - if(root==null)return root: base case; subtree empty so return null.
    * - if(p.val<root.val && q.val<root.val): both p and q are smaller than root → LCA must be in left subtree due to BST property.
    *   - return lowestCommonAncestorOptimalRecursionTopDown(root.left,p,q): recurse left.
    * - else if(p.val>root.val && q.val>root.val): both p and q are larger than root → LCA must be in right subtree.
    *   - return lowestCommonAncestorOptimalRecursionTopDown(root.right,p,q): recurse right.
    * - else: p and q are on different sides of root (or one equals root) → root is the split point, hence the LCA.
    *   - return root.
    *
    * Rationale: This is a top‑down recursion leveraging BST property to prune search space efficiently. By comparing p and q values to root’s value, recursion avoids unnecessary traversal and finds LCA directly at the split point where p and q diverge. This makes the approach optimal for BSTs.
    *
    * Time Complexity: O(h) — where h = height of BST (O(log n) for balanced trees, O(n) in worst case skewed trees).
    * Space Complexity: O(h) — recursion stack space.
    */

    public TreeNode lowestCommonAncestorOptimalRecursionTopDown(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return root;
        }
        if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestorOptimalRecursionTopDown(root.left, p, q);
        }
        else if(p.val>root.val && q.val>root.val){
            return lowestCommonAncestorOptimalRecursionTopDown(root.right, p, q);
        }
        else{
            return root;
        }
    }

    /*
    * ONE LINER => lowestCommonAncestorOptimalIte: iterate while(root!=null) to traverse BST, if(p.val<root.val && q.val<root.val) root=root.left to move left when both targets are smaller, else if(p.val>root.val && q.val>root.val) root=root.right to move right when both targets are larger, else return root when p and q split sides (or one equals root) as that is the LCA → O(h) time, O(1) space.
    *
    * Code(Reason):
    * - while(root!=null): iterate until subtree is exhausted or LCA is found.
    *   - if(p.val<root.val && q.val<root.val): both p and q are smaller than root → LCA must be in left subtree by BST property.
    *     - root=root.left: traverse left.
    *   - else if(p.val>root.val && q.val>root.val): both p and q are larger than root → LCA must be in right subtree.
    *     - root=root.right: traverse right.
    *   - else: p and q are on different sides of root (or one matches root) → root is the split point, hence the LCA.
    *     - return root: LCA found.
    * - return root: if loop exits without finding split point (edge cases), return current root.
    *
    * Rationale: This is an iterative BST traversal leveraging the BST property for optimal LCA search. By walking down the tree until p and q diverge, it avoids recursion and additional space usage while maintaining optimal search time.
    *
    * Time Complexity: O(h) — where h = height of BST (O(log n) for balanced trees, O(n) in worst case skewed trees).
    * Space Complexity: O(1) — no extra space used apart from variables.
    */

    public TreeNode lowestCommonAncestorOptimalIte(TreeNode root, TreeNode p, TreeNode q) {
        while(root!=null){
            if(p.val<root.val && q.val<root.val){
                root=root.left;
            }
            else if(p.val>root.val && q.val>root.val){
                root=root.right;
            }
            else{
                return root;
            }
        }
        return root;
    }
}
