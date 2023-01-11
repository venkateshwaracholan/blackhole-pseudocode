/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.trie;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/word-search-ii/description/

public class WordSearch2 {
    
    //
    class Solution {
        class TrieNode{
            Map<Character,TrieNode> ch=new HashMap();
            String word;
        }
        public List<String> findWords(char[][] board, String[] words) {
            int m= board.length,n=board[0].length;
            List<String> res = new ArrayList();
            TrieNode root = new TrieNode();
            for(String word: words){
                TrieNode node = root;
                for(int i=0;i<word.length();i++){
                    char c = word.charAt(i);
                    if(!node.ch.containsKey(c))
                        node.ch.put(c,new TrieNode());
                    node=node.ch.get(c);
                }
                node.word = word;
            }
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                    dfs(board,root,i,j,res);
            return res;
        }
        public void dfs(char[][] board, TrieNode node, int i, int j, List<String> res){
            if(i==-1||i==board.length||j==-1||j==board[0].length) return;
            char c = board[i][j];
            if(!node.ch.containsKey(c)) return;
            node=node.ch.get(c);
            if(node.word!=null){
                res.add(node.word);
                node.word = null;
            }
            board[i][j] = '#';
            dfs(board,node,i-1,j,res);
            dfs(board,node,i+1,j,res);
            dfs(board,node,i,j-1,res);
            dfs(board,node,i,j+1,res);
            board[i][j] = c;
        }
    }
}
