package ds_algorithm.trie;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/implement-trie-prefix-tree/
import java.util.*;

public class ImplementTriePrefixTree {
    
    //APPROACH 1 DFS Lin REC + TrieNode(Map<C, TN> ch, bool end), create root, insert-> i==len return end=true, c=s[i] if n.ch not contains, put n.ch(c,new TN), call rec(i+n,n.ch.get(c))
    //           search(sw=false) +startswith(sw=true) calls searchrec-> i==len retturn end||sw, c=s[i], ret n.ch(c)!=null && rec(i+1,n.ch(c))
   
    
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
                node.ch.put(c,new TrieNode());
            return insert(word,i+1,node.ch.get(c));
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
    
    //APPROACH 2 Iterative + TrieNode(TN[26] ch, bool end), create root, insert-> for(i,len) c=s[i] if n.ch not contains, put n[c]=new TN, n=n.ch[c], i++, finally end=true
    //           search(sw=false) +startswith(sw=true) calls searchrec-> for(i,len)c=s[i], ret n.ch[c]==null ret false, n=n.ch[c], ret end||sw
   
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
    
    //APPROACH 3 DFS Rec + TST TrieNode(C val, TN l,mid,r, bool end), create root, insert-> i==len return node, c=s[i] if n null, new TN(val) n.val==c n.mid=rec(i+1,mid)
    //                 c<n.val n.left=rec(i,left) c>n.val n.right=rec(i,right), i==len-1 end=true return n
    //           search(sw=false) +startswith(sw=true) calls searchrec-> n==null return false, c=s[i], c==n.val&&i=len-1 ret end|sw, n.val==c ret rec(i+1,mid)
    //              c<n.val ret rec(i,left) c>n.val ret rec(i,right)
   
    // TST ternary search Tree
    class Trie {
        class TrieNode{
            char val; boolean end;
            TrieNode left,mid,right;
            TrieNode(){}
            TrieNode(char c){  val = c; }
        }
        TrieNode root= new TrieNode();    
        public void insert(String word) {
            insert(word,0,root);
        }
        public TrieNode insert(String word, int i, TrieNode node) {
            if(i==word.length()) return node;
            char c = word.charAt(i);
            if(node==null) node = new TrieNode(c);
            if(c==node.val) node.mid = insert(word,i+1,node.mid);
            else if(c<node.val) node.left = insert(word,i,node.left);
            else if(c>node.val)node.right = insert(word,i,node.right);
            if(i==word.length()-1) node.end=true;
            return node;
        }
        public boolean searchTrie(String word, int i, TrieNode node, boolean sw) {
            if(node==null) return false;
            char c = word.charAt(i);
            if(node.val==c && i==word.length()-1) return node.end || sw;
            if(node.val==c) return searchTrie(word,i+1,node.mid,sw);
            else if(c<node.val) return searchTrie(word,i,node.left,sw);
            else return searchTrie(word,i,node.right,sw);
        }
        public boolean search(String word) {
            return searchTrie(word,0,root,false);
        }
        public boolean startsWith(String prefix) {
            return searchTrie(prefix,0,root,true);
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
