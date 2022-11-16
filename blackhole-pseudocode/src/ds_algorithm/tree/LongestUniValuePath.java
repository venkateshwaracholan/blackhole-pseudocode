/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/longest-univalue-path/

public class LongestUniValuePath {
  
  
// Time: O(n) space: O(n)
//  core idea: recursion, head recursion
//  if root has left and matches we increment, other wise make 0
//  to specifically avoid its propogation to the top
//  same for right
//  ans  = max of ans and left + right
//  return max of left and right for propogation towards top
  public static int longestUnivaluePath(TreeNode root) {
      int ans[] = new int[]{0};
      longestUnivaluePath(root, ans);
      return ans[0];
  }

  public static int longestUnivaluePath(TreeNode root, int ans[]) {
      if(root==null) return 0;
      int left = longestUnivaluePath(root.left, ans);
      int right = longestUnivaluePath(root.right, ans);
      if(root.left!=null && root.val==root.left.val) left++;
      else left=0;
      if(root.right!=null && root.val==root.right.val) right++;
      else right=0;
      ans[0] = Math.max(ans[0], left+right);
      return  Math.max(left,right);
  }


// Time: O(n) space: O(n)
//  core idea: recursion, head recursion
//  we use two extra variables aleft and aright which are initialized to 0
//  if root has left and matches we inrement, we aleft = left+1, 
//  so that if it doesnt match its 0 and avoid propogation from below
//  same for right
//  ans  = max of ans and aleft + aright
//  return max of aleft and aright for propogation towards top
  public int longestUnivaluePathExtraVariable(TreeNode root) {
    int ans[] = new int[]{0};
    longestUnivaluePathExtraVariable(root, ans);
    return ans[0];
  }

  public int longestUnivaluePathExtraVariable(TreeNode root, int ans[]) {
    if(root==null) return 0;
    int left = longestUnivaluePathExtraVariable(root.left, ans);
    int right = longestUnivaluePathExtraVariable(root.right, ans);
    int aleft = 0, aright = 0;
    if(root.left!=null && root.val==root.left.val) aleft=left+1;
    if(root.right!=null && root.val==root.right.val) aright=right+1;
    ans[0] = Math.max(ans[0], aleft+aright);
    return  Math.max(aleft,aright);
  }
  
  
  public int longestUnivaluePathAlt(TreeNode root) {
    int ans[] = new int[]{0};  
    find(root,-1,ans);
    return ans[0];
  }

  public int find(TreeNode root,int prev, int ans[]){
      if(root==null) return 0;
      int left=find(root.left,root.val, ans);
      int right=find(root.right,root.val, ans);
      ans[0]=Math.max(ans[0],left+right);
      if(prev==-1 || prev!=root.val) return 0;
      return Math.max(left,right)+1;
  }
  
  
  public static void main(String args[]){
    longestUnivaluePath(deSerializeBfs("1,4,5,4,4,5")); 
  }
}
