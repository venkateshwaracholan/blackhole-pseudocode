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
    
    //APPROACH 1 ITE + DFS4 +Set<String>=> n*r*c loop findWords-> for evry wor, every row col-> if(exist(i,j,k)) set.add(word) return set->list
    //              DFS exist(i,j,k)-> k==len ret true, i,j not in bound or b[i][j]=='#'(visited) or w[k]!=b[i][j] ret false, c=b[i][j], b[i][j]='#'(mark vis)
    //              go all  4 dir x= rec(i-1,j,k)||rec(i+1,j,k)||rec(i,j-1,k)||rec(i,j+1,k), b[i][j]=c(unmark vis) ret x;
    
    // TLE
    // T/S: O( n * RC * (4 ^ w))/O(n), where RC is number of cells and w is max word length, n is number of words
    class Solution {
        public List<String> findWords(char[][] board, String[] words) {
            Set<String> ans = new HashSet();
            for(String word: words)
                for(int i=0;i<board.length;i++)
                    for(int j=0;j<board[0].length;j++)
                        if(exist(board,word,i,j,0))
                            ans.add(word);
            return new ArrayList(ans);
        }
        public boolean exist(char[][] board, String word,int i, int j, int k) {
            if(k==word.length()) return true;
            if(i==-1||i==board.length||j==-1||j==board[0].length||board[i][j]=='#'||word.charAt(k)!=board[i][j]) return false;
            char c = board[i][j];
            board[i][j] = '#';
            boolean x = exist(board,word,i-1,j,k+1)||
                        exist(board,word,i+1,j,k+1)||
                        exist(board,word,i,j-1,k+1)||
                        exist(board,word,i,j+1,k+1);                  
            board[i][j] = c;
            return x;
        }
    }
    
    //APPROACH 2 ITE + DFS4 + 2 Set<String>=> n+r*c loop findWords-> put word in set and find maxlen, every row col-> dfs(i,j,k,ans,wordset,sb) return set->list
    //              DFS dfs(i,j,k,ans,wordset,sb)-> k==maxor i,j not in bound or b[i][j]=='#'(visited) ret false, 
    //              c=b[i][j], b[i][j]='#'(mark vis), sb(c),if(wordset(sb)) ans.add(sb)
    //              go all  4 dir x= rec(i-1,j,k)||rec(i+1,j,k)||rec(i,j-1,k)||rec(i,j+1,k), sb.del(sb.len-1), b[i][j]=c(unmark vis) ret x;
    
    //APPROACH
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
    
    
    //APPROACH 3 Ite + DFS4 + TrieNode(Map<C, TN> ch, bool end))+Set<String>, create root, 
    //            create trie for all words-> for(i,len) n=root c=s[i] if n.ch(c)==null->n.ch(c,new TN), n=n.ch(c), i++, finally end=true
    //           every row col-> dfs(i,j,k,ans,sb) return set->list
    //          DFS dfs(i,j,k,ans,sb)-> i,j not in bound or b[i][j]=='#'(visited) ret false, c=b[i][j], n.ch(c)==null,n=n.ch(c) ret sb(c), if(n.end)ans.add(sb) b[i][j]='#'(mark vis)
    //          go all  4 dir x= rec(i-1,j,k)||rec(i+1,j,k)||rec(i,j-1,k)||rec(i,j+1,k), sb.del(sb.len-1), b[i][j]=c(unmark vis) ret x;
   
     /* Create Trie of all words. And then search in Trie.
    * Time Complexity: O(R*C * 4*(3^(L-1))) + O(N)
    *      O(4*(3^(L-1))) ==> For the dfsHelper function, first time we have at most 4 directions, but the choices are reduced to 3 (since no need to go back to the
    *                         cell from where we came). the total number of calls to dfsHelper will be 3^L
    *      O(N) ==> For building trie
    * Space Complexity: O(N + L)
    *      O(N) ==> For Trie. We are storing reference of word. So no space used by word.
    *      O(L) ==> For Recursion Depth.
    * R = Number of rows. C = Number of columns. N = Total number of chars in words
    * array. L = Maximum length of a word in the words array. */
    class Solution3 {
        class TrieNode{
            Map<Character,TrieNode> ch = new HashMap();
            boolean end;
        }
        TrieNode root= new TrieNode();
        public List<String> findWords(char[][] board, String[] words) {
            Set<String> ans = new HashSet();
            for(String word: words){
                TrieNode n = root;
                for(int i=0;i<word.length();i++){
                    char c = word.charAt(i);
                    if(!n.ch.containsKey(c)) n.ch.put(c,new TrieNode());
                    n=n.ch.get(c);
                }
                n.end = true;
            }
            for(int i=0;i<board.length;i++)
                for(int j=0;j<board[0].length;j++)
                    dfs(board,root,ans,i,j,new StringBuilder());
            return new ArrayList(ans);
        }
        public void dfs(char[][] board, TrieNode n, Set<String> ans,int i, int j,StringBuilder sb) {
            if(i==-1||i==board.length||j==-1||j==board[0].length||board[i][j]=='#') return;
            char c = board[i][j];
            if(!n.ch.containsKey(c)) return;
            n=n.ch.get(c);
            sb.append(c);
            if(n.end) ans.add(sb.toString());
            board[i][j] = '#';
            dfs(board,n,ans,i-1,j,sb);
            dfs(board,n,ans,i+1,j,sb);
            dfs(board,n,ans,i,j-1,sb);
            dfs(board,n,ans,i,j+1,sb);                  
            board[i][j] = c;
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    //APPROACH 4 Ite + DFS4 + TrieNode(TN[26] ch, String word)+Set<String>+List<String> ans(ans),create root,  
    //    create trie for all words-> for(i,len) c=s[i] create root, create trie for all words-> for(i,len) c=s[i], if n.ch[c]==null put n[c]=new TN, n=n.ch[c], i++, finally end=true
    //     every row col-> dfs(i,j,k,ans) return list
    //       DFS dfs(i,j,k,ans,sb)-> i,j not in bound or b[i][j]=='#'(visited) ret false, c=b[i][j], n.ch[c]==null ret, if(n.word){ans.add(n.word) n.word=null}, b[i][j]='#'(mark vis)
    //        go all  4 dir x= rec(i-1,j,k)||rec(i+1,j,k)||rec(i,j-1,k)||rec(i,j+1,k), b[i][j]=c(unmark vis) ret x; 
    class Solution4 {
        class TrieNode{
            TrieNode[] ch = new TrieNode[26];
            String word;
        }
        TrieNode root= new TrieNode();
        public List<String> findWords(char[][] board, String[] words) {
            List<String> ans = new ArrayList();
            for(String word: words){
                TrieNode n = root;
                for(int i=0;i<word.length();i++){
                    char c = word.charAt(i);
                    if(n.ch[c-'a']==null) n.ch[c-'a']=new TrieNode();
                    n=n.ch[c-'a'];
                }
                n.word = word;
            }
            for(int i=0;i<board.length;i++)
                for(int j=0;j<board[0].length;j++)
                    dfs(board,root,ans,i,j);
            return ans;
        }
        public void dfs(char[][] board, TrieNode n, List<String> ans,int i, int j) {
            if(i==-1||i==board.length||j==-1||j==board[0].length||board[i][j]=='#') return;
            char c = board[i][j];
            if(n.ch[c-'a']==null) return;
            n=n.ch[c-'a'];
            if(n.word!=null) {
                ans.add(n.word);
                n.word=null;
            }
            board[i][j] = '#';
            dfs(board,n,ans,i-1,j);
            dfs(board,n,ans,i+1,j);
            dfs(board,n,ans,i,j-1);
            dfs(board,n,ans,i,j+1);                  
            board[i][j] = c;
        }
    }
}
