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

//

public class DesignAddAndSearchWordsDataStructure {
    
    
    //TLE
    //TC : O(n * m), where n is the number of words and m is length of word we need to search.
    //SC : O(n)
    class WordDictionary {
        Map<Integer,Set<String>> map = new HashMap();
        public void addWord(String word) {
            int len = word.length();
            if(!map.containsKey(len))
                map.put(len, new HashSet());
            map.get(len).add(word);
        }
        public boolean search(String word) {
            int len = word.length();
            if(map.containsKey(len)){
                for(String s: map.get(len)){
                    int i=0;
                    while(i<len&&(word.charAt(i)=='.'||word.charAt(i)==s.charAt(i)))
                        i++;
                    if(i==len) return true;
                }
            }
            return false;
        }
    }

    
    //    TC : O(∑ n) + O(26^m), where n is the number of words and m is length of word we need to search.
    //    SC : O(∑ n)
    // DFS REC
    class WordDictionary {
        class TrieNode{
            Map<Character,TrieNode> ch= new HashMap();
            boolean end = false;
        }
        TrieNode root = new TrieNode();
        public void addWord(String word) {
            addWord(word,0,root);
        }
        public boolean addWord(String word, int i, TrieNode n) {
            if(i==word.length()) return n.end = true;
            char c = word.charAt(i);
            if(n.ch.get(c)==null)
                n.ch.put(c,new TrieNode());
            return addWord(word, i+1, n.ch.get(c));
        }
        public boolean search(String word) {
            return search(word,0,root);
        }
        public boolean search(String word, int i, TrieNode n) {
            if(i==word.length()) return n.end;
            char c = word.charAt(i);
            if(c=='.')
                for(TrieNode x: n.ch.values())
                    if(search(word,i+1,x)) return true; 
            if(n.ch.get(c)==null) return false;
            return search(word,i+1,n.ch.get(c));
        }
    }

    
    
    
    // DFS ITE + REC
    class WordDictionary2 {
        class TrieNode{
            Map<Character,TrieNode> ch = new HashMap();
            boolean end = false;
        }
        TrieNode root = new TrieNode();
        public void addWord(String word) {
            TrieNode t = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                if(!t.ch.containsKey(c))
                    t.ch.put(c,new TrieNode());
                t=t.ch.get(c);
            }
            t.end = true;
        }
        public boolean search(String word) {
            return search(word,0,root);
        }
        public boolean search(String word,int idx,TrieNode node) {
            for(int i=idx;i<word.length();i++){
                char c = word.charAt(i);
                if(c=='.'){
                    for(char x: node.ch.keySet())
                        if(search(word,i+1,node.ch.get(x))) return true;
                }
                if(!node.ch.containsKey(c)) return false;
                node=node.ch.get(c);
            }
            return node.end;
        }
    }
    
    
    // BFS ITE
    class WordDictionary3 {
        class TrieNode{
            TrieNode[] ch = new TrieNode[26];
            boolean end = false;
        }
        TrieNode root = new TrieNode();
        public void addWord(String word) {
            TrieNode node = root;
            for(char c: word.toCharArray()){
                if(node.ch[c-'a']==null)
                    node.ch[c-'a']=new TrieNode();
                node=node.ch[c-'a'];
            }
            node.end = true;
        }
        public boolean search(String word) {
            Queue<TrieNode> q = new LinkedList();
            q.add(root);
            int i=0;
            while(!q.isEmpty()){
                int siz = q.size();
                while(siz-->0){
                    TrieNode node = q.poll();
                    if(i==word.length()) {
                        if(node.end) return true;
                        else continue;
                    }
                    char c = word.charAt(i);
                    if(c=='.'){
                        for(TrieNode x: node.ch)
                            if(x!=null)
                                q.add(x);
                    }
                    else if(node.ch[c-'a']!=null) 
                        q.add(node.ch[c-'a']);
                }
                i++;
            }
            return false;
        }
    }
    
    
    
}
