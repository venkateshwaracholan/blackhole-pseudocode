/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author venkateshwarans
 */

// https://leetcode.com/problems/sum-of-left-leaves/

public class SumOfLeftLeaves {
  static boolean show = false;
  
  // Time: O(n) space: O(n)
  // solved with left boolean
  public static int sumOfLeftLeavesRec(TreeNode root) {
      return sumOfLeftLeavesRec(root, false, new int[]{0});
  }

  public static int sumOfLeftLeavesRec(TreeNode node, boolean left,int[] sum)
  {
      if(node==null)
          return sum[0];
      else if(node.left==null && node.right==null && left==true){
          sum[0] += node.val;
      }
      sumOfLeftLeavesRec(node.left, true, sum);
      sumOfLeftLeavesRec(node.right, false, sum);
      return sum[0];
  }
  
  
  // Time: O(n) space: O(n)
  // solved with simple if checks
  public static int sumOfLeftLeavesIte(TreeNode node)
  {
      int sum =0;
      Queue<TreeNode> q = new LinkedList();
      if(node!=null){
        q.add(node);
      }
      while(!q.isEmpty()){
        TreeNode n = q.poll();
        if(n.left!=null){
          if(n.left.left==null && n.left.right==null){
            sum+=n.left.val;
          }
          q.add(n.left);
        }
        if(n.right!=null){
          q.add(n.right);
        }
      }
      return sum;
  }
  
  public  static void main(String[] args){
    BinaryTree t1;
      
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7));
    test(sumOfLeftLeavesRec(t1.root), 0);
    
    t1 = new BinaryTree(Arrays.asList(3,9,20,null,null,15,7));
    test(sumOfLeftLeavesRec(t1.root), 24);
    
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(sumOfLeftLeavesRec(t1.root), 9);
    
    t1 = new BinaryTree(Arrays.asList(3));
    test(sumOfLeftLeavesRec(t1.root), 0);
    
    t1 = new BinaryTree(Arrays.asList());
    test(sumOfLeftLeavesRec(t1.root), 0);
    
    
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7));
    test(sumOfLeftLeavesIte(t1.root), 0);
    
    t1 = new BinaryTree(Arrays.asList(3,9,20,null,null,15,7));
    test(sumOfLeftLeavesIte(t1.root), 24);
  
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(sumOfLeftLeavesRec(t1.root), 9);
    
    t1 = new BinaryTree(Arrays.asList(3));
    test(sumOfLeftLeavesIte(t1.root), 0);
    
    t1 = new BinaryTree(Arrays.asList());
    test(sumOfLeftLeavesIte(t1.root), 0);

  }

  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
