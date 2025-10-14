/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

public class BinaryTreeLevelorderTraversal {
    
    //refer https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal for explanations
    
    //APPROACH 1 BFS+level order traversal, create and add in list
    
    public List<List<Integer>> levelOrderBFSIteQueueLevel(TreeNode root) {
        var queue = new LinkedList<TreeNode>();
        var result = new ArrayList<List<Integer>>();
        int level = 0;
        if(root!=null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            result.add(new ArrayList<Integer>());
            while(size>0){
                TreeNode node = queue.poll();
                result.get(level).add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                size--;
            }
            level++;
        }
        return result;
    }
    


    //APPROACH 2 BFS+depth queue, get list from index depth and add in list
    class Entity2{
        public TreeNode n;
        public int d;
        Entity2(TreeNode n, int d){
            this.n=n;
            this.d=d;
        }
    }
    record Entity(TreeNode node, int depth){}
    public List<List<Integer>> levelOrderBFSIteQueueDepthNoLevelOrder(TreeNode root) {
        var queue = new LinkedList<Entity>();
        var result = new ArrayList<List<Integer>>();
        if(root!=null){
            queue.add(new Entity(root, 0));
        }
        while(!queue.isEmpty()){
            Entity entity = queue.poll();
            TreeNode node = entity.node();
            int depth = entity.depth();
            if(result.size()<=depth){
                result.add(new ArrayList<Integer>());
            }
            result.get(depth).add(node.val);
            if(node.left!=null){
                queue.add(new Entity(node.left, depth+1));
            }
            if(node.right!=null){
                queue.add(new Entity(node.right, depth+1));
            }
        }
        return result;
    }
    
    //APPROACH 3 DFS+depth, get list from index depth and add in list
    
    public List<List<Integer>> levelOrderDFSRecTopDownDepth(TreeNode root) {
        var result = new ArrayList<List<Integer>>();
        levelOrder(root, 0, result);
        return result;
    }

    public void levelOrder(TreeNode node, int depth, List<List<Integer>> result){
        if(node==null){
            return;
        }
        if(result.size()<=depth){
            result.add(new ArrayList<Integer>());
        }
        result.get(depth).add(node.val);
        levelOrder(node.left,depth+1, result);
        levelOrder(node.right,depth+1, result);
    }


    public  static void main(String[] args){
        System.out.println("hahah");
    }
}
