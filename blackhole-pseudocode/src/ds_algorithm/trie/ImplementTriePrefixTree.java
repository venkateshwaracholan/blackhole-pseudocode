package ds_algorithm.trie;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/implement-trie-prefix-tree/
import java.util.*;




class TrieNode {
  // Initialize your data structure here.
  char c;
  boolean isLeaf;
  Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  
  public TrieNode() {}
  public TrieNode(char c) { this.c = c; }
}

//public class Trie{
class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    Map<Character, TrieNode> children = root.children;
    
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      
      if (!children.containsKey(c))
        children.put(c, new TrieNode(c));
      
      TrieNode t = children.get(c);
      
      if (i == word.length() - 1) 
        t.isLeaf = true;
      
      children = t.children;
    }
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
      TrieNode t = searchLastNode(word);
      
      return t != null && t.isLeaf;
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
      return searchLastNode(prefix) != null;
  }
  
  // Returns the last TrieNode of word
  private TrieNode searchLastNode(String word) {
    Map<Character, TrieNode> children = root.children;
    
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      
      if (!children.containsKey(c)) break;
          
      TrieNode t = children.get(c);
      
      if (i == word.length() - 1) 
        return t;
      
      children = t.children;
    }
    
    return null;
  }
}



/*
class TrieNode{
    boolean isLeaf = false;
    Character c;
    Map<Character, TrieNode> children = new HashMap();;
    
    TrieNode(){
    }
    TrieNode(Character c){
        this.c = c;
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode t = root;
        for(char c: word.toCharArray()){
            if(!t.children.containsKey(c))
                t.children.put(c,new TrieNode(c));
            t = t.children.get(c);
        }
        t.isLeaf = true;
    }
    
    public boolean search(String word) {
        TrieNode t = root;
        for(char c: word.toCharArray()){
            if(!t.children.containsKey(c))
                return false;
            t = t.children.get(c);
        }
        return t.isLeaf == true;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode t = root;
        for(char c: prefix.toCharArray()){
            if(!t.children.containsKey(c))
                return false;
            t = t.children.get(c);
        }
        return true;
    }
}



*/



/*
class TrieNode{
    boolean isLeaf = false;
    TrieNode children[] = new TrieNode[26];
    TrieNode(){
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode t = root;
        for(char c: word.toCharArray()){
            if(t.children[c-'a']==null)
                t.children[c-'a'] = new TrieNode();
            t = t.children[c-'a'];
        }
        t.isLeaf = true;
    }
    
    public boolean search(String word) {
        TrieNode t = root;
        for(char c: word.toCharArray()){
            if(t.children[c-'a']==null)
                return false;
            t = t.children[c-'a'];
        }
        return t.isLeaf == true;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode t = root;
        for(char c: prefix.toCharArray()){
            if(t.children[c-'a']==null)
                return false;
            t = t.children[c-'a'];
        }
        return true;
    }
}


*/

public class ImplementTriePrefixTree {
  
}
