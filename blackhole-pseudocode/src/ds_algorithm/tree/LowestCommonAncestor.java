/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


/*
         2
      1     3
    n   4  n  7
   n n 8 9
*/


public class LowestCommonAncestor {
  
  static boolean show = true;


    /*
    * ONE LINER => lowestCommonAncestorBrutePaths: create var pList=new ArrayList<TreeNode>(), qList=new ArrayList<TreeNode>() to store root→p and root→q paths, call rootToLeafPaths(root,p,pList) and rootToLeafPaths(root,q,qList) to populate paths, iterate with int i=0; i<pList.size()&&i<qList.size();i++ comparing pList.get(i).val==qList.get(i).val, break on mismatch to find deepest common node, return pList.get(i-1) as LCA since mismatch means divergence point, rootToLeafPaths(node,target,list): if(node==null)return false: end branch, list.add(node):add node to path, if(node.val==target.val)return true:found target, boolean left=rootToLeafPaths(node.left,target,list), right=rootToLeafPaths(node.right,target,list):DFS left/right, if(left||right)return true:target found in subtree, list.remove(list.size()-1):backtrack if target not found, return false:target absent → O(n) time per path (O(2n) absolute), O(h) space per path (O(2h) total).
    *
    * Code(Reason):
    * - var pList = new ArrayList<TreeNode>(), qList = new ArrayList<TreeNode>(): initialize lists for storing root→target paths.
    * - rootToLeafPaths(root,p,pList), rootToLeafPaths(root,q,qList): populate paths using DFS recursion with backtracking.
    * - int i=0; for(;i<pList.size()&&i<qList.size();i++){if(pList.get(i).val!=qList.get(i).val)break;}: compare nodes level by level, stop at first mismatch because that is the divergence point of the paths.
    * - return pList.get(i-1): return last common node before mismatch as LCA because the first mismatch marks where p and q paths split.
    * - rootToLeafPaths(TreeNode root, TreeNode target, List<TreeNode> list):
    *   - if(root==null)return false: null base case, end branch.
    *   - list.add(root): add current node to path.
    *   - if(root.val==target.val)return true: found target node.
    *   - boolean left=rootToLeafPaths(root.left,target,list), right=rootToLeafPaths(root.right,target,list): search left/right subtrees.
    *   - if(left||right)return true: propagate true if target found.
    *   - list.remove(list.size()-1): backtrack to previous state if target not found.
    *   - return false: target not found in this subtree.
    *
    * Rationale: Brute‑force approach explicitly generates root→target paths and compares them level by level to locate the deepest common ancestor; iteration stops at mismatch because that's where the paths diverge, returning the node before mismatch ensures the deepest common node is selected. Backtracking preserves correct path construction without excess storage.
    *
    * Time Complexity: O(n) per path (O(2n) absolute for both) as each node is visited once in DFS.
    * Space Complexity: O(h) recursion stack per path + O(h) list storage (O(2h) total).
    */
    public TreeNode lowestCommonAncestorBrutePaths(TreeNode root, TreeNode p, TreeNode q) {
        var pList = new ArrayList<TreeNode>();
        var qList = new ArrayList<TreeNode>();
        rootToLeafPaths(root, p, pList);
        rootToLeafPaths(root, q, qList);
        int i=0;
        for(;i<pList.size() && i<qList.size();i++){
            if(pList.get(i).val==qList.get(i).val){
                continue;
            }
            break;
        }
        return pList.get(i-1);
    }

    public boolean rootToLeafPaths(TreeNode root, TreeNode target, List<TreeNode> list){
        if(root==null){
            return false;
        }
        list.add(root);
        if(root.val==target.val){
            return true;
        }
        boolean left = rootToLeafPaths(root.left, target, list);
        boolean right = rootToLeafPaths(root.right, target, list);
        if(left || right){
            return true;
        }
        list.remove(list.size()-1);
        return false;
    }






    /*
    * ONE LINER => lowestCommonAncestorBuildParentMapRec: create var parentMap=new HashMap<TreeNode,TreeNode>() to store child→parent mapping, call buildParentMap(root,p,q,parentMap) to populate map with parent links for p and q, create var pSet=new HashSet<TreeNode>() to store path from p→root, iterate for(TreeNode t=p;t!=null;t=parentMap.get(t))pSet.add(t) to add all ancestors of p, iterate while(!pSet.contains(q))q=parentMap.get(q) to walk q’s ancestors until intersection with p’s path, return q as LCA, buildParentMap(node,p,q,map): if(node==null)return: base case, if(map.containsKey(p)&&map.containsKey(q))return: stop early if both found, map.put(node.left,node), map.put(node.right,node):store parent links, recurse buildParentMap(node.left,p,q,map), buildParentMap(node.right,p,q,map) to traverse tree → O(n) time, O(n) space for map and recursion.
    *
    * Code(Reason):
    * - var parentMap = new HashMap<TreeNode,TreeNode>(): initialize map to store parent links for each node.
    * - buildParentMap(root,p,q,parentMap): populate parentMap by DFS traversal, linking each child to its parent.
    * - var pSet = new HashSet<TreeNode>(): initialize set to store path from p to root for ancestor checking.
    * - parentMap.put(root,null): add root’s parent as null (optional if guaranteed p,q in tree).
    * - for(TreeNode t=p;t!=null;t=parentMap.get(t))pSet.add(t): walk up from p to root, adding all ancestors to set for O(1) lookup.
    * - while(!pSet.contains(q))q=parentMap.get(q): walk up from q until an ancestor is found in p’s path (first common ancestor).
    * - return q: return first common ancestor found as LCA.
    * - buildParentMap(TreeNode root,TreeNode p,TreeNode q,Map<TreeNode,TreeNode> map):
    *   - if(root==null)return: base case to end recursion.
    *   - if(map.containsKey(p)&&map.containsKey(q))return: early termination if both p and q’s parent links are found.
    *   - map.put(root.left,root), map.put(root.right,root): store parent for children.
    *   - buildParentMap(root.left,p,q,map), buildParentMap(root.right,p,q,map): recurse left and right subtrees to complete mapping.
    *
    * Rationale: This approach uses a parent pointer map to convert LCA problem to ancestor intersection problem. By storing child→parent links, we can walk upwards from p and q to find their first common ancestor efficiently. Early termination avoids unnecessary recursion once both nodes are found.
    *
    * Time Complexity: O(n) — every node visited once to build parent map (DFS traversal).
    * Space Complexity: O(n) — storage for parent map + recursion stack in worst case.
    */
    public TreeNode lowestCommonAncestorBuildParentMapRec(TreeNode root, TreeNode p, TreeNode q) {
        var parentMap = new HashMap<TreeNode,TreeNode>();
        buildParentMap(root, p, q, parentMap);
        var pSet = new HashSet<TreeNode>();
        parentMap.put(root, null); // optional coz p and q exist
        for(TreeNode t=p; t!=null; t=parentMap.get(t)){
            pSet.add(t);
        }
        while(!pSet.contains(q)){
            q = parentMap.get(q);
        }
        return q;
    }

    public void buildParentMap(TreeNode root, TreeNode p, TreeNode q, Map<TreeNode,TreeNode> map){
        if(root==null){
            return;
        }
        if(map.containsKey(p) && map.containsKey(q)){
            return;
        }
        map.put(root.left, root);
        map.put(root.right, root);
        buildParentMap(root.left, p, q, map);
        buildParentMap(root.right, p, q, map);
    }

    /*
    * ONE LINER => lowestCommonAncestorBuildParentMapIteQueue: create var parentMap=new HashMap<TreeNode,TreeNode>() to store child→parent mapping, var queue=new LinkedList<TreeNode>() for BFS traversal, put root→null in parentMap (root has no parent), if(root!=null)queue.add(root) to start BFS, while(!(parentMap.containsKey(p)&&parentMap.containsKey(q))) (stop BFS early when both p and q’s parents are found to avoid unnecessary traversal){node=queue.poll() to process next level node, map.put(node.left,node) and map.put(node.right,node) to record parent links for children, if(node.left!=null)queue.add(node.left) and if(node.right!=null)queue.add(node.right) to continue BFS only with valid children} to build parent links until both p and q found, var pSet=new HashSet<TreeNode>(), for(TreeNode t=p;t!=null;t=parentMap.get(t))pSet.add(t) to store all ancestors of p, while(!pSet.contains(q))q=parentMap.get(q) to walk q’s ancestors until first intersection with p’s ancestor set, return q as LCA → O(n) time, O(n) space for map and queue.
    *
    * Code(Reason):
    * - var parentMap=new HashMap<TreeNode,TreeNode>(), var queue=new LinkedList<TreeNode>(): initialize map for child→parent tracking, queue for BFS traversal.
    * - parentMap.put(root,null): root has no parent, so map it to null for termination of ancestor chain.
    * - if(root!=null)queue.add(root): start BFS if tree is not empty.
    * - while(!(parentMap.containsKey(p)&&parentMap.containsKey(q))): continue BFS until both p and q’s parent links are found; stopping early avoids unnecessary traversal and improves efficiency.
    *   - node=queue.poll(): fetch next node in BFS order.
    *   - map.put(node.left,node), map.put(node.right,node): record parent link for each child.
    *   - if(node.left!=null)queue.add(node.left), if(node.right!=null)queue.add(node.right): enqueue valid children to continue BFS traversal.
    * - var pSet=new HashSet<TreeNode>(): initialize set for storing ancestors of p for O(1) lookup.
    * - for(TreeNode t=p;t!=null;t=parentMap.get(t))pSet.add(t): walk upward from p to root adding each ancestor to pSet.
    * - while(!pSet.contains(q))q=parentMap.get(q): walk upward from q until first ancestor found in p’s ancestor set; that ancestor is the LCA.
    * - return q: return the first common ancestor found as LCA.
    *
    * Rationale: BFS level‑order traversal builds parent links efficiently; stopping early when both p and q are found avoids scanning entire tree unnecessarily. The ancestor set lookup makes the final LCA search O(h), where h = tree height.
    *
    * Time Complexity: O(n) — BFS visits nodes until both targets found; worst‑case visits all nodes.
    * Space Complexity: O(n) — storage for parentMap, queue, and ancestor set.
    */

    public TreeNode lowestCommonAncestorBuildParentMapIteQueue(TreeNode root, TreeNode p, TreeNode q) {
        var parentMap = new HashMap<TreeNode, TreeNode>();
        var queue = new LinkedList<TreeNode>();
        parentMap.put(root, null);
        if(root!=null){
            queue.add(root);
        }
        while(!(parentMap.containsKey(p) && parentMap.containsKey(q))){
            TreeNode node = queue.poll();
            parentMap.put(node.left, node);
            parentMap.put(node.right, node);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        var pSet = new HashSet<TreeNode>();
        for(TreeNode t = p; t!=null; t=parentMap.get(t)){
            pSet.add(t);
        }
        while(!pSet.contains(q)){
            q = parentMap.get(q);
        }
        return q;
    }




  
    /*
    * ONE LINER => lowestCommonAncestorDfsBottomUp: if(root==null||root==p||root==q)return root to handle base case (null node or found p/q), TreeNode left=lowestCommonAncestorDfsBottomUp(root.left,p,q) and TreeNode right=lowestCommonAncestorDfsBottomUp(root.right,p,q) to recursively search left/right subtrees, if(left!=null&&right!=null)return root because p and q found in different subtrees making root the LCA, else if(left!=null)return left to propagate found target upwards, else return right to propagate found target or null if neither found → O(n) time, O(h) space for recursion stack where h=tree height.
    *
    * Code(Reason):
    * - if(root==null||root==p||root==q)return root: base case; return null if subtree is empty, or return p/q immediately if found to avoid unnecessary recursion.
    * - TreeNode left=lowestCommonAncestorDfsBottomUp(root.left,p,q): recursively search left subtree for p/q.
    * - TreeNode right=lowestCommonAncestorDfsBottomUp(root.right,p,q): recursively search right subtree for p/q.
    * - if(left!=null&&right!=null)return root: if both sides return non-null, p and q are found in different subtrees so current root is their lowest common ancestor.
    * - else if(left!=null)return left: if only left subtree returns a target node, propagate it upward as potential LCA.
    * - else return right: if only right subtree returns a target node, propagate it upward; if both null return null.
    *
    * Rationale: Post‑order DFS bottom‑up recursion naturally discovers p and q in subtrees, unwinding to identify the first split point where both are found; that split point is the LCA. Base cases prevent unnecessary recursion and ensure correctness.
    *
    * Time Complexity: O(n) — visits each node once.
    * Space Complexity: O(h) — recursion stack, worst‑case h=n for skewed trees.
    */

    public TreeNode lowestCommonAncestorDfsBottomUp(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q){
            return root;
        }
        TreeNode left = lowestCommonAncestorDfsBottomUp(root.left,p,q);
        TreeNode right = lowestCommonAncestorDfsBottomUp(root.right,p,q);
        if(left!=null && right!=null){
            return root;
        }
        else if(left!=null){
            return left;
        }
        else{ 
            return right;
        }
    }
    
  
  public  static void main(String[] args){
    BinaryTree t1;
      
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7, null, null, 8, 9));
    //    test(lowestCommonAncestor(t1.root, new TreeNode(1), new TreeNode(3)), 2);
    //    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(4)), 4);
    //    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(1)), 1);
    //    test(lowestCommonAncestor(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
    // test(lowestCommonAncestorBrutePaths(t1.root, new TreeNode(1), new TreeNode(3)), 2);
    // test(lowestCommonAncestorBrutePaths(t1.root, new TreeNode(8), new TreeNode(4)), 4);
    // test(lowestCommonAncestorBrutePaths(t1.root, new TreeNode(8), new TreeNode(1)), 1);
    // test(lowestCommonAncestorBrutePaths(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
  }
  
  public static void test(TreeNode n, int exp){
    int got = n.val;
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  



















  //UNWANTED APPROACH DONT TRY THIS AND WASTE YOUR TIME, NO ADVANTAGE(BUT WORKS)

  //APPROACH 4 Iterative Inorder traversal with stack, if p or q found assing anc and anclev, found again return, stack size<anclevel, update anc and anclevel
    //  using inorder traversal -> left root right
    //  if found assign to ancestor, and set ans level
    //  if found within subtree return ancetor;
    // else if node returns and moves up stack, set ans and ans levels accordingly
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> st = new Stack();
        TreeNode n = root, anc =null;
        int anclev = -1;
        while(n!=null||!st.isEmpty()){
            while(n!=null){
                st.add(n);
                n=n.left;
            }
            n = st.pop();
            if(st.size()<anclev) {
                anc = n;
                anclev = st.size();
            }
            if(p==n||q==n){
                if(anc!=null) return anc;
                anc = n;
                anclev = st.size();
            }
            n=n.right;
        }
        return anc;
    }




}
