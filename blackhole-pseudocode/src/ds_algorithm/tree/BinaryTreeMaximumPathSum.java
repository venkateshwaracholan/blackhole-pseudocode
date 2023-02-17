package ds_algorithm.tree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/binary-tree-maximum-path-sum/

public class BinaryTreeMaximumPathSum {
  
    
    //APPROACH 1 DFS postorder + max[inf], get l=max(left,0) r=max(right,0), max=max,val+l+r, but return only max(val+l,val+r)    
    
    // Time: O(n) space: O(d)
    // approach: recursion, head recursion
    // we are making sure max gain is postive or zero
    // and adding all three to get max value makes sense becozz of the above statement(there is no negative gain and we reduce a lot of checks)
    // when returning back we can choose and return the max path
    public int maxPathSum(TreeNode root) {
        int max[] = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, max);
        return max[0];
    }
    public int maxPathSum(TreeNode root, int[] max) {
        if(root==null) return 0;
        int l = Math.max(maxPathSum(root.left,max),0);
        int r = Math.max(maxPathSum(root.right,max),0);
        max[0] = Math.max(max[0], root.val+l+r);
        return Math.max(l,r)+root.val;
    }
  
  
    //APPROACH 1.2 DFS postorder + max[inf], l=(left) r=(right), x=max(val,val+l,val+r), max=max,x,val+l+r but return x
    
    // Time: O(n) space: O(d)
    // approach: recursion, head recursion 
    // now if both l and r are positive add them and acc in max
    //  or find max between, val, val+l, val+r and return that
    //   if(l>0 && r>0)
    //      max[0] = Math.max(max[0], root.val+l+r);
    //      int x = Math.max(root.val, Math.max(root.val+l, root.val+r));
    //   if we merge the statements above, it will chose to add everything rther than a path.
    //  local max x shount contain only val, val+l, val+r and not val + l + r to return back.
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root,max);
        return max[0];
    }
    public int maxPathSum(TreeNode root, int[] max) {
        if(root==null) return 0;
        int l = maxPathSum(root.left,max);
        int r = maxPathSum(root.right,max);
        int x = Math.max(root.val,Math.max(l+root.val,r+root.val));
        max[0] = Math.max(max[0],Math.max(l+r+root.val,x));
        return x;
    }
  
  
    //APPROACH 2 => Rev Ite + Map<TreeNode,Integer> , rev ite -> 2 stacks to acc(or arraylist rev), l=max(left,0) r=max(right,0), map(null,0), max=max,val+l+r map(node,max(val+l,val+r)) but return x
   
    // iterative approach
    //idea is to get the leaf nodes process first
    // so putting in stack, we can also use arraylist and traverse in reverse order
    // reversing can be done with both bfs and dfs, your choice
    // map.put(null,0) solves lot of null checks
    public int maxPathSum(TreeNode root) {
        Map<TreeNode,Integer> map = new HashMap();
        map.put(null,0);
        int max = Integer.MIN_VALUE;
        Stack<TreeNode> s = topsort(root);
        while(!s.isEmpty()){
            TreeNode n = s.pop();
            int l = Math.max(map.get(n.left),0);
            int r = Math.max(map.get(n.right),0);
            max = Math.max(max, n.val+l+r);
            map.put(n, n.val+Math.max(l,r));
        }
        return max;
    }
    public Stack<TreeNode> topsort(TreeNode root){
        Stack<TreeNode> ans = new Stack();
        Stack<TreeNode> s = new Stack();
        s.add(root);
        while(!s.isEmpty()){
            TreeNode n = s.pop();
            ans.push(n);
            if(n.left!=null)s.push(n.left);
            if(n.right!=null)s.push(n.right);
        }
        return ans;
    }
    
    // Topsor alternatives
    // use it in reverse order
    public int maxPathSum(TreeNode root) {
        int max = Integer.MIN_VALUE;
        List<TreeNode> list = topsort(root);
        Map<TreeNode, Integer> map = new HashMap();
        map.put(null, 0); 
        for(int i=list.size()-1;i>=0;i--){
            TreeNode n = list.get(i);
            int l = Math.max(map.get(n.left),0);
            int r = Math.max(map.get(n.right),0);
            max = Math.max(max, n.val+l+r);
            map.put(n, Math.max(l,r)+n.val);
        } 
        return max;
    }
    public List<TreeNode> topsort2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        List<TreeNode> ans = new ArrayList();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            ans.add(n);
            if(n.left!=null) q.add(n.left);
            if(n.right!=null) q.add(n.right);
        }
        return ans;
    }
}
