/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.trie;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/word-search-ii/description/

public class WordSearch2 {
    
    
    // TLE
    // T/S: O( n * RC * (4 ^ w))/O(n), where RC is number of cells and w is max word length, n is number of words
    class Solution {
        public List<String> findWords(char[][] board, String[] words) {
            int m= board.length,n=board[0].length;
            Set<String> res = new HashSet();
            for(String word: words)
                for(int i=0;i<m;i++)
                    for(int j=0;j<n;j++)
                        if(dfs(board,word,0,i,j))
                            res.add(word);
            return new ArrayList(res);
        }
        public boolean dfs(char[][] board,String word, int k, int i, int j){
            if(k==word.length())  return true;
            if(i==-1||i==board.length||j==-1||j==board[0].length||word.charAt(k)!=board[i][j]) return false;
            char c = board[i][j];
            board[i][j] = '#';
            boolean x = dfs(board,word,k+1,i-1,j)||
            dfs(board,word,k+1,i+1,j)||
            dfs(board,word,k+1,i,j-1)||
            dfs(board,word,k+1,i,j+1);
            board[i][j] = c;
            return x;
        }
    }
    
    
    // NO TLE
    // T/S: O( n + RC * (4 ^ w))/O(n), where RC is number of cells and w is max word length, n is number of words
    class Solution2 {
        public List<String> findWords(char[][] board, String[] words) {
            int m= board.length,n=board[0].length;
            Set<String> set = new HashSet();
            Set<String> res = new HashSet();
            int max=0;
            for(String word: words){
                set.add(word);
                max = Math.max(max,word.length());
            }
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                    dfs(board,set,res,new StringBuilder(),0,max,i,j);
            return new ArrayList(res);
        }
        public void dfs(char[][] board,Set<String> words, Set<String> res, StringBuilder sb, int k, int max, int i, int j){
            if(i==-1||i==board.length||j==-1||j==board[0].length||k==max||board[i][j]=='#') return ;
            char c = board[i][j];
            sb.append(c);
            if(words.contains(sb.toString())) res.add(sb.toString());
            board[i][j] = '#';
            dfs(board,words,res,sb,k+1,max,i-1,j);
            dfs(board,words,res,sb,k+1,max,i+1,j);
            dfs(board,words,res,sb,k+1,max,i,j-1);
            dfs(board,words,res,sb,k+1,max,i,j+1);
            sb.deleteCharAt(sb.length()-1);
            board[i][j] = c;
        }
    }
    
    
     /* Create Trie of all words. And then search in Trie.
    *
    * Time Complexity: O(R*C * 4*(3^(L-1))) + O(N)
    *      O(4*(3^(L-1))) ==> For the dfsHelper function, first time we have at most 4 directions
    *                         to explore, but the choices are reduced to 3 (since no need to go back to the
    *                         cell from where we came). Therefore, in the worst case, the total number of
    *                         calls to dfsHelper will be 3^L
    *      O(N) ==> For building trie
    *
    * Space Complexity: O(N + L)
    *      O(N) ==> For Trie. We are storing reference of word. So no space used by word.
    *      O(L) ==> For Recursion Depth.
    *
    * R = Number of rows. C = Number of columns. N = Total number of chars in words
    * array. L = Maximum length of a word in the words array.
    */
    class Solution3 {
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
    
    
    //
    class Solution4 {
        class TrieNode{
            TrieNode[] ch=new TrieNode[26];
            boolean end;
        }
        public List<String> findWords(char[][] board, String[] words) {
            int m= board.length,n=board[0].length;
            Set<String> res = new HashSet();
            TrieNode root = new TrieNode();
            for(String word: words){
                TrieNode node = root;
                for(int i=0;i<word.length();i++){
                    char c = word.charAt(i);
                    if(node.ch[c-'a']==null)
                        node.ch[c-'a']=new TrieNode();
                    node=node.ch[c-'a'];
                }
                node.end = true;
            }
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                    dfs(board,root,new StringBuilder(),i,j,res);
            return new ArrayList(res);
        }
        public void dfs(char[][] board, TrieNode node, StringBuilder sb, int i, int j, Set<String> res){
            if(i==-1||i==board.length||j==-1||j==board[0].length) return;
            char c = board[i][j];
            if(c=='#'||node.ch[c-'a']==null) return;
            sb.append(c);
            node=node.ch[c-'a'];
            if(node.end) res.add(sb.toString());
            board[i][j] = '#';
            dfs(board,node,sb,i-1,j,res);
            dfs(board,node,sb,i+1,j,res);
            dfs(board,node,sb,i,j-1,res);
            dfs(board,node,sb,i,j+1,res);
            sb.deleteCharAt(sb.length()-1);
            board[i][j] = c;
        }
    }
}
