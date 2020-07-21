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

// https://leetcode.com/problems/cousins-in-binary-tree/
import java.util.*;


public class CousinsInBinaryTree {
  
//  Time: O(n) space: O(n) recursion alone not O(D) becoz in worst case d can be n if its a linear tree, only for a balnaced tree d = log2(n)
// check if x and y has same depth and not same parent and return tru or false acccrodingly
  
  public boolean isCousins(TreeNode root, int x, int y) {
      Map<Integer,Integer> map = new HashMap(), parent = new HashMap();
      constructDepthMap(root,x,y,0,0,parent,map);
      if(map.get(x)==map.get(y) && parent.get(x)!=parent.get(y))
          return true;
      return false;
  }

//  approach: tail recursion
//  pass depth and parent to recursion
//  populating depth only for x and y 
//  and if x or y, put them in map
  
  public void constructDepthMap(TreeNode root, int x, int y, int d, int p,Map<Integer,Integer> parent , Map<Integer,Integer> map) {
    if(root==null) return;
    if(root.val==x || root.val==y){
        map.put(root.val,d+1);
        parent.put(root.val, p);
    }
    constructDepthMap(root.left,x,y,d+1,root.val,parent,map);
    constructDepthMap(root.right,x,y,d+1,root.val,parent,map);
  }
  
  
  
//  Time O(n) space: (n)

// core idea: using depth from parent 
// populting depth for all nodes
// put 0 as depth fro root node

  
  public boolean isCousinsFullDepthMap(TreeNode root, int x, int y) {
      Map<Integer, Integer> parent = new HashMap(), depth = new HashMap();
      depth.put(root.val, 0);
      isCousinsFullDepthMap(root,x,y,parent,depth);
      if(depth.get(x)==depth.get(y) && parent.get(x)!=parent.get(y)){
          return true;
      }
      return false;
  }

// approach: tail recurion, hashing parent and depth
// if left present, put root as parent in map for left in parent map
// get depth of root and assign +1 for left in depth map
// vice vera for right
  public boolean isCousinsFullDepthMap(TreeNode root, int x, int y, Map<Integer, Integer> parent, Map<Integer, Integer> depth){
    if(root.left!=null){
        parent.put(root.left.val, root.val);
        depth.put(root.left.val,depth.get(root.val)+1);
        isCousinsFullDepthMap(root.left, x,y,parent, depth);
    }
    if(root.right!=null){
        parent.put(root.right.val, root.val);
        depth.put(root.right.val,depth.get(root.val)+1);
        isCousinsFullDepthMap(root.right, x,y,parent,depth);
    }
    return false;
        
  }
  
//  Time(n) space O(n)
//  approach: bfs, level order traversal
//  use two loops to iterate levels in groups
//  and have a f(found) variable outside veel grp loop, and increment f is node matches x or y
//  if not null add lefta nd right to queue
//  now special case of avoiding siblings, if both are not null and x and y are found in left and right return fasle, becoz they are sibling, so once we finng probable siblings we quit pre emptively
//  cousings will be found in the next level grp from n.val
//  if 2 are presetin that level grp return true
//  if only one is present return fasle and stop preemptively instead of proceeding further
  
  public boolean isCousinsLevelOrder(TreeNode root, int x, int y) {
      Queue<TreeNode> q = new LinkedList();
      q.add(root);
      while(!q.isEmpty()){
          int s = q.size();
          int f = 0;
          while(s>0){
              TreeNode n = q.poll();
              if(n.val==x||n.val==y) f++;
              if(n.left!=null) q.add(n.left);
              if(n.right!=null) q.add(n.right);
              if(n.left!=null && n.right!=null){
                  if(n.left.val==x && n.right.val==y || n.left.val==y && n.right.val==x)
                      return false;
              }
              s--;
          }
          if(f==2) return true;
          if(f==1) return false;
      }
      return false;
  }
}
