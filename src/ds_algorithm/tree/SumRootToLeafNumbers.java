/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/sum-root-to-leaf-numbers/

public class SumRootToLeafNumbers {
  public static int sumNumbers(TreeNode root) {
      int arr[] = new int[1];
      sumNumbers(root,0,arr);
      return arr[0];
  }

  public static void sumNumbers(TreeNode root, int pathSum, int arr[]) {
      if(root==null)
          return;
      pathSum = pathSum*10 + root.val;
      if(root.left==null&& root.right==null){
          arr[0]+=pathSum;
      }
      sumNumbers(root.left,pathSum,arr);
      sumNumbers(root.right,pathSum,arr);
  }
}
