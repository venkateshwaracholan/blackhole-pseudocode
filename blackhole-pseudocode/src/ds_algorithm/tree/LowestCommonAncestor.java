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
    
    
  
  
    // using parents maps to fetch leaf to root path from p or q
    // run while until map contains p and q both
    // then add root, null in map for ending
    // add path nodes of p to set
    // then while set not contains q, move queue up using map
    // return q
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList();
        Map<TreeNode, TreeNode> map= new HashMap();
        qu.add(root);
        map.put(root,null);
        while(!map.containsKey(p) || !map.containsKey(q)){
            TreeNode n= qu.poll();
            if(n.left!=null) {
                qu.add(n.left);
                map.put(n.left, n);
            }
            if(n.right!=null) {
                qu.add(n.right);
                map.put(n.right, n);
            }
        }
        Set<TreeNode> set = new HashSet();
        while(p!=null){
            set.add(p);
            p=map.get(p);
        }
        while(!set.contains(q))
            q=map.get(q);
        return q;
    }
    
    
    
    // finding root to leaf paths and storing in arraylist
    // then iterate the paths to find common ancestor
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> x=new ArrayList(),y=new ArrayList();
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
    
  
    //
    //  using inorder traversal
    //  if found assign to ancestor, and set ans level
    //  if found within subtree return ancetor;
    // else if node returns and moves up stack, set ans and ans levels accordingly
    public static TreeNode lowestCommonAncestorIteDFS(TreeNode root, TreeNode a, TreeNode b){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ancestor = null;
        int ancestorLevel = -1;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            root = stack.pop();
            if (stack.size() < ancestorLevel) {
                ancestor = root;
                ancestorLevel = stack.size();
            }
            if (root.val == a.val || root.val == b.val) {
                if (ancestor != null) break;
                ancestor = root;
                ancestorLevel = stack.size();
            }
            node = root.right;
        }
        return ancestor;
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
