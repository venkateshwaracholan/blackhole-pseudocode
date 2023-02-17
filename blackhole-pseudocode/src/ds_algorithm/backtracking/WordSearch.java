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
    
    //APPROACH 1 ITE + DFS4 => r*c loop every row col-> if(exist(i,j,k)) ret true
    //              DFS exist(i,j,k)-> k==len ret true, i,j not in bound or b[i][j]=='#'(visited) or w[k]!=b[i][j] ret false, c=b[i][j], b[i][j]='#'(mark vis)
    //              go all  4 dir x= rec(i-1,j,k)||rec(i+1,j,k)||rec(i,j-1,k)||rec(i,j+1,k), b[i][j]=c(unmark vis) ret x;
    
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
    public boolean exist2(char[][] board, String word, int i, int j, int k) {
        if(k==word.length()) return true;
        if(i==-1||j==-1||i==board.length||j==board[0].length||board[i][j]!=word.charAt(k)||board[i][j]=='#') return false;
        char c = board[i][j];
        board[i][j] = '#';
        boolean x = exist2(board,word,i,j-1,k+1) ||
                    exist2(board,word,i,j+1,k+1) ||
                    exist2(board,word,i-1,j,k+1) ||
                    exist2(board,word,i+1,j,k+1);
        board[i][j] = c;
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
    
    
    //APPROACH 2 ITE + BFS Queue<int[]>+Queue<Set<Integer>> => r*c loop every row col-> if(exist(i,j,k)) ret true
    //              BFS queues for int[] i,j,k , Set<Integer>, do bfs c==len ret true, i,j not in bound continue, vis.contains(rc) continue; word[i]!=board[a][b] continuevis.add(rc)
    //              go all  4 dir using dir[] add contexts in queue int[] and clone of vis
    
    
    // TLE 
    // word search in BFS, so memory heavy coz of nature of problem
    class Entity{
        int i,j,k;
        Set<Integer> set;
        Entity(int i, int j, int k, Set<Integer> set){
            this.i=i;
            this.j=j;
            this.k=k;
            this.set=set;
        }
    }

    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(dfs(board,i,j,word,0)) return true;
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, String word, int k) {
        int m = board.length, n =board[0].length;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        Queue<Entity> q = new LinkedList();
        q.add(new Entity(i,j,0, new HashSet()));
        while(!q.isEmpty()){
            Entity e = q.poll();
            int a = e.i, b = e.j, c = e.k;
            Set<Integer> vis = e.set;
            int rc = a*n+b;
            if(c==word.length()) return true;
            if(a<0||a>=m||b<0||b>=n|| vis.contains(rc)|| word.charAt(c)!=board[a][b]) continue;
            vis.add(rc);
            for(int x=0;x<dir.length;x++){
                q.add(new Entity(a+dir[x][0],b+dir[x][1],c+1, new HashSet(vis)));
            }
        }
        return false;
    }
    
    
    //unwanted cos nt using classes
    public boolean existx(char[][] board, String word) {
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
            int rc = a*board[0].length+b;
            if(a==-1||a==board.length||b==-1||b==board[0].length||v.contains(rc)||word.charAt(c)!=board[a][b]) continue;
            v.add(rc);
            for(int z=0;z<dir.length;z++){
                q.add(new int[]{a+dir[z][0],b+dir[z][1],c+1});
                vis.add(new HashSet(v));
            }
        }
        return false;
    }
}
