/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import java.util.*;

/**
 *
 * @author venkateshwarans
 */

//https://leetcode.com/problems/maximum-depth-of-binary-tree/


public class MaximumDepthOfBinaryTree {
  static boolean show = true;

    /*
    * ONE LINERS — Quick Reference:\
    *
    * Maximum Depth (DFS Bottom‑Up): if(root==null)return 0(base case: empty tree depth=0(coz recursion visits null leafs)), recursively compute lDepth=maxDepthRecBottomUp(root.left)+1 and rDepth=maxDepthRecBottomUp(root.right)+1(add 1 for current node, depth computation happens bottom‑up when recursion unwinds), return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    * Maximum Depth (DFS Top‑Down): call maxDepthTopDown(node,0) with depth=0(start at root, 0 coz recursion visits null leafs), if(node==null)return d(base case: leaf child depth=d coz top down depth compute), recursively call left and right with depth+1(depth computation happens top‑down) and return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    * Maximum Depth (BFS Level Order): initialize depth=0 (coz depth is incremented after each level), use queue for level order traversal, if(root!=null) Add root to queue, while queue not empty —> size=queue.size(), iterate size times to process current level, add non‑null children to queue, increment depth after processing level → O(n), O(n) space.
    * Maximum Depth (BFS with Node+Depth): use queue storing Entity(node,depth)(depth stored explicitly since recursion is not used, level order not done), create record record Entity(TreeNode node, int depth){}, queue = new LinkedList<Entity>() (Create queue storing Node+Depth pairs.), initialize maxDepth=0(track max), if(root!=null) queue.add(new Entity(root, 1)) (add non null entity to queue root depth=1 coz ite wont visit null leafs), while queue not empty — poll Entity, node, depth, add non‑null children with depth+1, track max depth encountered → O(n), O(n) space.
    */
  
    /*
    * ONE LINER => Maximum Depth (DFS Bottom‑Up): if(root==null)return 0(base case: empty tree depth=0(coz recursion visits null leafs)), recursively compute lDepth=maxDepthRecBottomUp(root.left)+1 and rDepth=maxDepthRecBottomUp(root.right)+1(add 1 for current node, depth computation happens bottom‑up when recursion unwinds), return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    *
    * Approach: Post‑order DFS to compute depth from bottom up.
    * - if(root==null) return 0 (base case: depth of null node is 0).
    * - Recursively get depth of left subtree lDepth.
    * - Recursively get depth of right subtree rDepth.
    * - Return max(lDepth,rDepth) + 1 (add current level, return max depth from both sides, depth computation happens bottom‑up).
    *
    * Reasoning: Recursion naturally unwinds from leaf nodes upwards, so bottom‑up accumulation computes maximum depth correctly; base case returns 0 to indicate empty subtree.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack, worst‑case depth for skewed tree.
    *
    * Key Insight: Bottom‑up recursion efficiently aggregates subtree depths without extra state variables.
    */

    public int maxDepthRecBottomUp(TreeNode root) {
        if(root==null){
            return 0;
        }
        int lDepth = maxDepthRecBottomUp(root.left)+1;
        int rDepth = maxDepthRecBottomUp(root.right)+1;
        return Math.max(lDepth,rDepth);
    }
  
    /*
    * ONE LINER => Maximum Depth (DFS Top‑Down): call maxDepthTopDown(node,0) with depth=0(start at root, 0 coz recursion visits null leafs), if(node==null)return d(base case: leaf child depth=d coz top down depth compute), recursively call left and right with depth+1(depth computation happens top‑down) and return Math.max(lDepth,rDepth)(return max depth from both sides, depth comparison happens bottom‑up) → O(n), O(n) recursion space.
    *
    * Approach: Pre‑order DFS passing current depth.
    * - Start with depth=0 (root level is counted from 0 for consistency).
    * - If node==null return depth d (base case: empty node depth equals passed depth).
    * - Recursively call left and right subtrees with d+1 (depth computation happens top‑down).
    * - Return max(lDepth,rDepth) (return max depth from both sides, depth comparison happens bottom‑up).
    *
    * Reasoning: Top‑down recursion explicitly tracks depth during traversal, so each node knows its depth without needing to compute from leaves; base case returns depth directly.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — recursion stack for skewed trees.
    *
    * Key Insight: Passing depth explicitly avoids needing to compute depth from subtree returns.
    */

    public int maxDepthTopDown(TreeNode root) {
        return maxDepthTopDown(root, 0);
    }

    public int maxDepthTopDown(TreeNode node,int d){
        if(node==null){
            return d;
        }
        int lDepth = maxDepthTopDown(node.left, d+1);
        int rDepth = maxDepthTopDown(node.right, d+1);
        return Math.max(lDepth, rDepth);
    }
  
    /*
    * ONE LINER => Maximum Depth (BFS Level Order): initialize depth=0 (coz depth is incremented after each level), use queue for level order traversal, if(root!=null) Add root to queue, while queue not empty —> size=queue.size(), iterate size times to process current level, add non‑null children to queue, increment depth after processing level → O(n), O(n) space.
    *
    * Approach: Iterative BFS level‑order to measure depth level by level.
    * - Initialize queue = new LinkedList<TreeNode>()
    * - Initialize depth=0 (starting level count).
    * - if(root!=null) Add root to queue;
    * - While queue is not empty:
    *     - size=queue.size() (nodes at current level).
    *     - For each node at this level:
    *         - Poll node, add its non‑null children to queue.
    *     - Increment depth (completed one level).
    * - Return depth.
    *
    * Reasoning: BFS naturally processes tree level by level, so counting levels visited yields depth; base case depth=0 since empty tree; depth increments reflect level count.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — maximum queue size is number of nodes at largest level (up to n/2).
    *
    * Key Insight: BFS avoids recursion, depth corresponds to level count.
    */

    public static int maximumDepthBFSIte(TreeNode root){
        var queue = new LinkedList<TreeNode>();
        int depth = 0;
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                var node = queue.poll();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;  
    }
  
    /*
    * ONE LINER => Maximum Depth (BFS with Node+Depth): use queue storing Entity(node,depth)(depth stored explicitly since recursion is not used, level order not done), create record record Entity(TreeNode node, int depth){}, queue = new LinkedList<Entity>() (Create queue storing Node+Depth pairs.), initialize maxDepth=0(track max), if(root!=null) queue.add(new Entity(root, 1)) (add non null entity to queue root depth=1 coz ite wont visit null leafs), while queue not empty — poll Entity, node, depth, add non‑null children with depth+1, track max depth encountered → O(n), O(n) space.
    *
    * Approach: Iterative BFS tracking depth explicitly with Entity record.
    * - create record record Entity(TreeNode node, int depth){}  
    * - Initialize queue = new LinkedList<Entity>() (Create queue storing Node+Depth pairs.)
    * - Initialize maxDepth=0 (starting level count).
    * - if(root!=null) Add Entity(root,1) to queue; queue.add(new Entity(root, 1)) (root depth=1 coz ite wont visit null leafs)
    * - While queue is not empty:
    *     - Poll entity, node, depth, update max depth if current depth is greater.
    *     - Add left child with depth+1 if non‑null.
    *     - Add right child with depth+1 if non‑null.
    * - Return max depth.
    *
    * Reasoning: BFS processes nodes iteratively without recursion; depth must be stored explicitly since recursion is not used and level order is not done; base depth=1 for root matches intuitive level counting starting from root as level 1.
    *
    * Time Complexity: O(n) — each node visited once.
    * Space Complexity: O(n) — queue size up to number of nodes at largest level.
    *
    * Key Insight: Explicit depth storage allows BFS to compute max depth without recursion.
    */

    public int maxDepth(TreeNode root) {
        record Entity(TreeNode node, int depth){}
        var queue = new LinkedList<Entity>();
        int maxDepth = 0;
        if(root!=null){
            queue.add(new Entity(root, 1));
        }
        while(!queue.isEmpty()){
            var polled = queue.poll();
            var node = polled.node();
            var depth = polled.depth();
            if(node.left!=null){
                queue.add(new Entity(node.left,depth+1));
            }
            if(node.right!=null){
                queue.add(new Entity(node.right, depth+1));
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;  
    }
  
  
  public  static void main(String[] args){
  }

  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
