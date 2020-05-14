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
  
// Time: O(n) space: O(d)
// approach: recursion, bottom up
// we are making sure max gain is postive or zero
// and adding all three to get max value makes sense becozz of the above statement(there is no negative gain and we reduce a lot of checks)
// when returning back we can choose and return the max path
  public int maxPathSum(TreeNode root) {
      int max[] = new int[]{Integer.MIN_VALUE};
      maxPathSum(root, max);
      return max[0];
  }

  public int maxPathSum(TreeNode root, int max[]) {
      if(root == null) return 0;
      int l = Math.max(maxPathSum(root.left,max),0);
      int r = Math.max(maxPathSum(root.right,max),0);
      int x = root.val+l+r;
      max[0] = Math.max(max[0], x);
      return root.val + Math.max(l,r);
  }
  
  
// Time: O(n) space: O(d)
// approach: recursion, bottom up  
// now if both l and r are positive add them and acc in max
//  or find max between, val, val+l, val+r
//  and return that
  
//   if(l>0 && r>0)
//      max[0] = Math.max(max[0], root.val+l+r);
//      int x = Math.max(root.val, Math.max(root.val+l, root.val+r));
//   if we merge the statements above, it will chose to add everything rther than a path.
//  local max x shount contain only val, val+l, val+r and not val + l + r to return back.
  
  public int maxPathSumAlt(TreeNode root) {
      int max[] = new int[]{Integer.MIN_VALUE};
      maxPathSumAlt(root, max);
      return max[0];
  }

  public int maxPathSumAlt(TreeNode root, int max[]) {
      if(root == null) return 0;
      int l = maxPathSumAlt(root.left,max);
      int r = maxPathSumAlt(root.right,max);
      if(l>0 && r>0)
          max[0] = Math.max(max[0], root.val+l+r);
      int x = Math.max(root.val, Math.max(root.val+l, root.val+r));;
      max[0] = Math.max(max[0], x);
      return x;
  }
}
