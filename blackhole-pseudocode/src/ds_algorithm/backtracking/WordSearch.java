/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.backtracking;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/word-search/

public class WordSearch {
    
    
    // T/S: O(n * (4 ^ w))/O(n), where n is number of cells and w is word length.
    // To avoid confusion, T: O(r * c * (4 ^ w)), where r x c are the dimensions of the board.
    // optimised code and used board space instead of extra
    public boolean exist2(char[][] board, String word) {
        if(!wordPresent(board,word)) return false;
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(exist2(board,word,i,j,0))
                    return true;
        return false;
    }
    public boolean exist2(char[][] board, String word, int m, int n, int k) {
        if(k==word.length()) return true;
        if(m==-1||n==-1||m==board.length||n==board[0].length||board[m][n]!=word.charAt(k)||board[m][n]=='#') return false;
        char c = board[m][n];
        board[m][n] = '#';
        boolean x = exist2(board,word,m,n-1,k+1) ||
        exist2(board,word,m,n+1,k+1) ||
        exist2(board,word,m-1,n,k+1) ||
        exist2(board,word,m+1,n,k+1);
        board[m][n] = c;
        return x;
    }
    //NOTE: this is just a precheker and not the solution
    //to optimise further we can run a prechecker to see if all chars in word
    // this check made it run Beats
    //    98.64% time
    //    98.5% memory
    public boolean wordPresent(char[][] board, String word){
        Map<Character,Integer> map = new HashMap();
        for(int i=0;i<word.length();i++)
            map.put(word.charAt(i),map.getOrDefault(word.charAt(i),0)+1);
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(map.containsKey(board[i][j])){
                    if(map.get(board[i][j])>1) map.put(board[i][j],map.get(board[i][j])-1);
                    else map.remove(board[i][j]);
                }
        return map.size()==0;
    }
    
    
    // TLE 
    // word search in BFS, so memory heavy coz of nature of problem
    public boolean exist(char[][] board, String word) {
        if(!wordPresent(board,word)) return false;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}}; 
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(exist(board,word,i,j,dir)) return true;
        return false;
    }

    public boolean exist(char[][] board, String word, int i, int j, int[][]dir) {
        Queue<int[]> q = new LinkedList();
        Queue<Set<Integer>> vis = new LinkedList();
        q.add(new int[]{i,j,0});
        vis.add(new HashSet());
        while(!q.isEmpty()){
            int[] x = q.poll();
            Set<Integer> v = vis.poll();
            int a=x[0],b=x[1],c=x[2];
            if(c==word.length()) return true;
            if(a==-1||a==board.length||b==-1||b==board[0].length) continue;
            int rc = a*board[0].length+b;
            if(v.contains(rc)) continue;
            v.add(rc);
            if(word.charAt(c)!=board[a][b]) continue;
            for(int z=0;z<dir.length;z++){
                q.add(new int[]{a+dir[z][0],b+dir[z][1],c+1});
                vis.add(new HashSet(v));
            }
        }
        return false;
    }
}
