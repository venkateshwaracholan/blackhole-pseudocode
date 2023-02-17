/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;
import java.util.Arrays;
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
  
    //APPROACH 1 DFS if found in return root, else return whcihever node is found
  
    // Time: O(n) space: O(n)
    // if ruul or p found or q founmd return root
    // get the left and right
    // if left and right both foun then root is ancestor
    // else if x is null y is ans or vice versa
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q) return root;
        TreeNode x = lowestCommonAncestor(root.left,p,q);
        TreeNode y = lowestCommonAncestor(root.right,p,q);
        if(x!=null && y!=null) return root;
        return x!=null ? x:y;
    }
    
    
  
    //APPROACH 2 DFS+hashMap+set leaf to root paths in map dfs until both p and q in map, put p in set, and move q until q is in set, return q
    
    // using parents maps to fetch leaf to root path from p or q
    // run while until map contains p and q both
    // then add root, null in map for ending
    // add path nodes of p to set
    // then while set not contains q, move queue up using map
    // return q
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode,TreeNode> map = new HashMap();
        Set<TreeNode> pset = new HashSet();
        paths(root,map,p,q);
        map.put(root,null);
        for(TreeNode t = p;t!=null;t=map.get(t))
            pset.add(t);
        while(!pset.contains(q)) q = map.get(q);
        return q;
    }
    public void paths(TreeNode root, Map<TreeNode,TreeNode> map, TreeNode p, TreeNode q) {
        if(root==null||map.containsKey(p)&&map.containsKey(q)) return;
        map.put(root.left,root);
        map.put(root.right,root);
        paths(root.left,map,p,q);
        paths(root.right,map,p,q);
    }
    //APPROACH 2.2 BFS+hashMap+set leaf to root paths in map bfs until both p and q in map, put p in set, and move q until q is in set, return q
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList();
        Map<TreeNode, TreeNode> map= new HashMap();
        qu.add(root);
        map.put(root,null);
        while(!map.containsKey(p) || !map.containsKey(q)){
            TreeNode n= qu.poll();
            if(n==null) continue;
            if(n.left!=null) map.put(n.left, n);
            if(n.right!=null) map.put(n.right, n);
            qu.add(n.left);
            qu.add(n.right);
        }
        Set<TreeNode> set = new HashSet();
        for(;p!=null;p=map.get(p))
            set.add(p);
        while(!set.contains(q)) q=map.get(q);
        return q;
    }
    
    
    //APPROACH 3 DFS+hashMap+set root to leaf paths in map as AL, move AL until same, return prev
    // finding root to leaf paths and storing in arraylist
    // then iterate the paths to find common ancestor
    // NOTE: if we try doing this iterative, we need more space to create the root to leaf path for p and q
    // n arraylists with all parents or n stringbuilder with all parents is required
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> x = new ArrayList(), y = new ArrayList();
        path(root,x,p);
        path(root,y,q);
        int i=0;
        for(;i<x.size()&&i<y.size()&&x.get(i).val==y.get(i).val;i++);
        return x.get(i-1);
    }
    public boolean path(TreeNode n, List<TreeNode> a, TreeNode t){
        if(n==null) return false;
        a.add(n);
        if(n.val==t.val) return true;
        if(path(n.left,a,t) || path(n.right,a,t)) return true;
        a.remove(a.size()-1);
        return false;
    }
    
  
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
  
  public  static void main(String[] args){
    BinaryTree t1;
      
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7, null, null, 8, 9));
//    test(lowestCommonAncestor(t1.root, new TreeNode(1), new TreeNode(3)), 2);
//    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(4)), 4);
//    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(1)), 1);
//    test(lowestCommonAncestor(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
    
    
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(1), new TreeNode(3)), 2);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(8), new TreeNode(4)), 4);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(8), new TreeNode(1)), 1);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
  }
  
  public static void test(TreeNode n, int exp){
    int got = n.val;
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
}
