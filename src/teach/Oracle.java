/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teach;

import java.util.*;
/**
 *
 * @author vshanmugham
 */

class TreeNode{
  int val;
  TreeNode left,right;
  TreeNode(int val){
    this.val = val;
  }
}


/*
1
2
3
4
0
5
0
1
1
1
1
1
       1
     2   3
   4    5 1
  1 1  1
*/

//11101

/*
     1
   2   3
  0 4 0 0 
*/

//[1,2,3,0,4,0,0]




public class Oracle {
  TreeNode root = null;
  
  public void insert(int nums[]){
    if(nums.length==0) return;
    TreeNode node = new TreeNode(nums[0]);
    if(root==null){
      root = node;
    }
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    int i=1;
    while(!q.isEmpty() && i<nums.length){
      TreeNode parent = q.poll();
      if(nums[i]!=0){
        TreeNode left = new TreeNode(nums[i]);
        parent.left = left;
        q.add(left);
      }
      i++;
      if(i<nums.length && nums[i]!=0){
        TreeNode right = new TreeNode(nums[i]);
        parent.right = right;
        q.add(right); 
      }
      i++;
    }
  }
  
  public void print(){
    if(root==null) return;
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    while(!q.isEmpty()){
      int n = q.size();
      while(n>0){
        TreeNode node = q.poll();
        System.out.print(node.val+" ");
        if(node.left!=null){
          q.add(node.left);
        }
        if(node.right!=null){
          q.add(node.right);
        }
        n--;
      }
      System.out.println();
    }
  }
  
  public int[] swapMinMax(int arr[]){
    int min= Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int minI = -1, maxI = -1;
    for(int i=0;i<arr.length;i++){
      if(arr[i]!=0 && arr[i]<min){
        minI = i;
        min = arr[i];
      }
      if(arr[i]!=0 && arr[i]>max){
        maxI = i;
        max = arr[i];
      }
      if(minI==-1 || maxI==-1){
        return arr;
      }
    }
    if(minI!=maxI){
      arr[minI] = max;
      arr[maxI] = min;
    }
    return arr;
  }
  
  
  public static void main(String args[]){
      int input[] = new int[]{1,2,3,4,0,5,0};
      Oracle o = new Oracle();
//      o.insert(input);
//      o.print();
      int a[] = o.swapMinMax(input);
      for(int i=0;i<a.length;i++){
        System.out.print(a[i]+" ");
      }
  }
  
}
