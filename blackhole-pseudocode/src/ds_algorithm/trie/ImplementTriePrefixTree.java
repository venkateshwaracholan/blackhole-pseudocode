package ds_algorithm.trie;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/implement-trie-prefix-tree/
import java.util.*;

public class ImplementTriePrefixTree {
    class Trie {
        class TrieNode{
            Map<Character, TrieNode> ch= new HashMap();
            boolean end = false;
        }
        TrieNode root= new TrieNode();    
        public void insert(String word) {
            insert(word,0,root);
        }
        public boolean insert(String word, int i, TrieNode node) {
            if(i==word.length()) return node.end = true;
            char c = word.charAt(i);
            if(!node.ch.containsKey(c))
                node.ch.put(word.charAt(i),new TrieNode());
            return insert(word,i+1,node.ch.get(word.charAt(i)));
        }
        public boolean search(String word, int i,TrieNode node, boolean sw) {
            if(i==word.length())return sw||node.end;
            char c = word.charAt(i);
            return node.ch.get(c)!=null && search(word,i+1,node.ch.get(c),sw);
        }
        public boolean search(String word) {
            return search(word,0,root,false);
        }
        public boolean startsWith(String prefix) {
            return search(prefix,0,root,true);
        }
    }
    
    
    // 
    class Trie {
        class TrieNode{
            TrieNode[] ch = new TrieNode[26];
            boolean end = false;
        }
        TrieNode root= new TrieNode();    
        public void insert(String word) {
            TrieNode x = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                if(x.ch[c-'a']==null)
                     x.ch[c-'a'] =new TrieNode();
                x = x.ch[c-'a'];
            }
            x.end=true;
        }
        public boolean search(String word, boolean sw) {
            TrieNode x = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                if(x.ch[c-'a']==null) return false;
                x = x.ch[c-'a'];
            }
            return x.end || sw;
        }
        public boolean search(String word) {
            return search(word,false);
        }
        public boolean startsWith(String prefix) {
            return search(prefix,true);
        }
    }
    
    // TST ternary search Tree
    class Trie {
        class TrieNode{
            char val;
            boolean end;
            TrieNode left,mid,right;
            TrieNode(){}
            TrieNode(char c){
                val = c;
            }
        }
        TrieNode root= new TrieNode();    
        public void insert(String word) {
            insert(word,0,root);
        }
        public TrieNode insert(String word, int i, TrieNode node) {
            if(i==word.length()) return node;
            char c = word.charAt(i);
            if(node==null) node = new TrieNode(c);
            if(node.val==c) node.mid = insert(word,i+1,node.mid);
            else if(c<node.val) node.left = insert(word,i,node.left);
            else if(c>node.val)node.right = insert(word,i,node.right);
            if(i==word.length()-1) node.end=true;
            return node;
        }
        public boolean search(String word) {
            return searchTrie(word,0,root,false);
        }
        public boolean startsWith(String prefix) {
            return searchTrie(prefix,0,root,true);
        }
        public boolean searchTrie(String word, int i, TrieNode node, boolean sw) {
            if(node==null) return false;
            char c = word.charAt(i);
            if(node.val==c && i==word.length()-1) return node.end || sw;
            if(node.val==c) return searchTrie(word,i+1,node.mid,sw);
            else if(c<node.val) return searchTrie(word,i,node.left,sw);
            else return searchTrie(word,i,node.right,sw);
        }
    }
    
    /*  printing Ternary search trie TST
    public void print(){
        Queue<TrieNode> q = new LinkedList();
        q.add(root);
        while(!q.isEmpty()){
            int siz = q.size();
            while(siz-->0){
                TrieNode n = q.poll();
                if(n==null){
                    System.out.print("0 ");
                    continue;
                }
                System.out.print(n.val+" ");
                q.add(n.left);
                q.add(n.mid);
                q.add(n.right);
            }
            System.out.println("");
        }
    }


*/

}


/*
class Trie {
    class TrieNode{
        Map<Character, TrieNode> ch= new HashMap();
        boolean end = false;
    }
    TrieNode root= new TrieNode();    
    public void insert(String word) {
        TrieNode x = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!x.ch.containsKey(c))
                    x.ch.put(c,new TrieNode());
            x = x.ch.get(c);
        }
        x.end=true;
    }
    public TrieNode searchTrie(String word) {
        TrieNode x = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!x.ch.containsKey(c)) return null;
            x = x.ch.get(c);
        }
        return x;
    }
    public boolean search(String word) {
        TrieNode t = searchTrie(word);
        return t!=null && t.end;
    }
    public boolean startsWith(String prefix) {
        return searchTrie(prefix)!=null;
    }
}

*/
