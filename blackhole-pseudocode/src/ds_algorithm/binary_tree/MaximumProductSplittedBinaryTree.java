/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree;

/**
 *
 * @author venka
 */

//https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/

public class MaximumProductSplittedBinaryTree {
    
    // Time: O(n) space: O(d)
    // approach: recursion, head recursion, product of sum and sum - nodes wight(node + l + r)
    // we are finding sum first
    // finding nodes weight => node + left + right and sum minus that value is the sum of the rest, this product has to be maximum
    // use long as metioned in ques and return mod %1000000007 to get leetcode accept
    public int maxProduct2(TreeNode root) {
        long[] max = new long[]{Integer.MIN_VALUE};
        long sum = sum2(root);
        maxProduct(root, max,sum);
        return (int)(max[0]%1000000007);
    }

    public long sum2(TreeNode node) {
        if(node==null) return 0;
        long l = sum2(node.left);
        long r = sum2(node.right);
        return node.val+l+r;
    }

    public long maxProduct2(TreeNode node, long[] max, long sum) {
        if(node==null) return 0;
        long l = maxProduct(node.left, max, sum);
        long r = maxProduct(node.right, max, sum);
        long c = node.val+l+r;
        long x = (sum - c) * c;
        max[0] = Math.max(max[0],x);
        return node.val+l+r;
    }
    
    
    //same as above but even better
    public int maxProduct(TreeNode root) {
        long sum = sum(root);
        long[] max = new long[]{Integer.MIN_VALUE};
        maxProduct(root, max, sum);
        return (int)(max[0]%1000000007);
    }

    public long maxProduct(TreeNode root, long[] max, long sum) {
        if(root==null)return 0;
        long x = root.val + maxProduct(root.left, max, sum) +
        maxProduct(root.right, max, sum);
        max[0] = Math.max(max[0],(x*(sum-x)));
        return x;
    }

    public long sum(TreeNode root){
        if(root==null)return 0;
        return root.val + sum(root.left) +sum(root.right);
    }
}
