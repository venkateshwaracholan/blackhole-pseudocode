/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.backtracking;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/word-search/

public class WordSearch {
    

    //
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(exist(board,word,i,j,0, new int[board.length][board[0].length]))
                    return true;
        return false;
    }
    public boolean exist(char[][] board, String word, int m, int n, int k, int[][] dp) {
        if(k==word.length()) return true;
        if(m==-1||n==-1||m==board.length||n==board[0].length) return false;
        if(dp[m][n]==0&&board[m][n]==word.charAt(k)){
            dp[m][n] = 1;
            boolean x = exist(board,word,m,n-1,k+1,dp) ||
            exist(board,word,m,n+1,k+1,dp) ||
            exist(board,word,m-1,n,k+1,dp) ||
            exist(board,word,m+1,n,k+1,dp);
            dp[m][n] = 0;
            return x;
        }
        return false;
    }
    
    // T/S: O(n * (4 ^ w))/O(n), where n is number of cells and w is word length.
    // To avoid confusion, T: O(r * c * (4 ^ w)), where r x c are the dimensions of the board.
    // optimised code and used board space instead of extra
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(exist(board,word,i,j,0))
                    return true;
        return false;
    }
    public boolean exist(char[][] board, String word, int m, int n, int k) {
        if(k==word.length()) return true;
        if(m==-1||n==-1||m==board.length||n==board[0].length||board[m][n]!=word.charAt(k)||board[m][n]=='#') return false;
        char c = board[m][n];
        board[m][n] = '#';
        boolean x = exist(board,word,m,n-1,k+1) ||
        exist(board,word,m,n+1,k+1) ||
        exist(board,word,m-1,n,k+1) ||
        exist(board,word,m+1,n,k+1);
        board[m][n] = c;
        return x;
    }
    
    
    //to optimise further we can run a prechecker to see if all chars in word
    // is present in matrix but too costly n*n*n
    // i can do this better with frequency maps
    public boolean checkWordCharactersInBoard(char[][] board, int rows, int cols, String word){              //   This method helped reducing Runtime further to only 55ms
        List<Character> wordList = new LinkedList<Character>();
        for(char ch : word.toCharArray()){ wordList.add(ch); }
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                char ch = board[r][c];
                if(wordList.size() == 0){ return true; }
                if(wordList.contains(ch) ){ wordList.remove((Character)ch); }                
            }
        }
        if(wordList.size() == 0){ return true; }
        return false;
    }
}
