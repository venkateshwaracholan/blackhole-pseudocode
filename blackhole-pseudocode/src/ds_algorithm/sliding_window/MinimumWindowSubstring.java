/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.sliding_window;

/**
 *
 * @author venka
 */
import java.util.*;
import ds_algorithm.Pair;

// https://leetcode.com/problems/minimum-window-substring/description/

public class MinimumWindowSubstring {
    
    //Time Complexity: O(∣S∣+∣T∣)
    // Space Complexity: O(∣S∣+∣T∣)
    // approach: sliding window, 2 freq maps, ans array, formed
    // fill freq of t in tmap
    // start window and put freq of j char in smap
    // if(tmap has j char and both freq count match) increase formed
    // while(i<=j and formed==tmap size) fill ans array if ans not set or indoming ans in new min
    // decrease i char from smap and check if freq of i char decreased if so, formed--; and then move i->i++;
    // while returning asn, if ans not set, retrun "" or build min window from ans
    public String minWindow(String s, String t) {
        Map<Character,Integer> smap = new HashMap();
        Map<Character,Integer> tmap = new HashMap();
        for(int i=0;i<t.length();i++) 
            tmap.put(t.charAt(i),tmap.getOrDefault(t.charAt(i),0)+1);
        int ans[] = new int[]{-1,0,0};
        for(int i=0,j=0,formed=0;j<s.length();j++){
            char c = s.charAt(j);
            smap.put(c,smap.getOrDefault(c,0)+1);
            if(tmap.containsKey(c) && smap.get(c).equals(tmap.get(c))) formed++;
            while(formed==tmap.size()){
                if(ans[0]==-1|| j-i+1<ans[0]){
                    ans[0] = j-i+1;
                    ans[1] = i;
                    ans[2] = j;
                }
                char d = s.charAt(i);
                smap.put(d,smap.getOrDefault(d,0)-1);
                if(tmap.containsKey(d) && smap.get(d).intValue()<tmap.get(d).intValue())
                    formed--;
                i++;
            }
        }
        return ans[0]==-1 ? "" : s.substring(ans[1],ans[2]+1);
    }
    
    
    
    //Time Complexity: O(∣S∣+∣T∣)
    // Space Complexity: O(∣S∣+∣T∣)
    // approach: sliding window, 2 freq maps, list of filtered values, ans array, formed
    // fill freq of t in tmap
    // filter s only if chars present in t, add them as pair c, i we need index to form ans; 
    // start window over the filtered array and put freq of j char in smap
    // if(tmap has j char and both freq count match) increase formed
    // while(i<=j and formed==tmap size) fill ans array if ans not set or incoming ans in new min, fill ans with index in filtered
    // decrease i char from smap and check if freq of i char decreased if so, formed--; and then move i->i++;
    // while returning asn, if ans not set, retrun "" or build min window from ans
    public String minWindow3(String s, String t) {
        if(s.length()==0||t.length()==0) return "";
        Map<Character,Integer> smap = new HashMap();
        Map<Character,Integer> tmap = new HashMap();
        List<Pair<Character, Integer>> filtered= new ArrayList();
        for(int i=0;i<t.length();i++)
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i),0)+1);
        for(int i=0;i<s.length();i++)
            if(tmap.containsKey(s.charAt(i)))
                filtered.add(new Pair(s.charAt(i),i));
        int ans[] = new int[]{-1,0,0};
        for(int i=0,j=0,formed=0;j<filtered.size();j++){
            char c = filtered.get(j).getKey();
            smap.put(c, smap.getOrDefault(c,0)+1);
            if(tmap.containsKey(c) && smap.get(c).equals(tmap.get(c))) formed++;
            while(formed==tmap.size()){
                char d = filtered.get(i).getKey();
                int l = filtered.get(i).getValue();
                int r = filtered.get(j).getValue();
                if(ans[0]==-1 || r-l+1< ans[0]){
                    ans[0] = r-l+1;
                    ans[1] = l;
                    ans[2] = r;
                }
                smap.put(d, smap.getOrDefault(d,0)-1);
                if(tmap.containsKey(d) && smap.get(d).intValue()<tmap.get(d).intValue()) 
                    formed--;
                i++;
            }
        }
        return ans[0]==-1 ? "" : s.substring(ans[1],ans[2]+1);
    }
    
    
    
    
    
    
    
    
    
    
  
    
    
    // we can use int[] instead of map with above 2 approaches
    //same as above usng int[] intead of map
    public String minWindow2(String s, String t) {
        int[] smap = new int[64],tmap = new int[64];
        for(int i=0;i<t.length();i++) tmap[t.charAt(i)-'A']++;
        int uniqt = 0;
        for(int i=0;i<64;i++) if(tmap[i]!=0) uniqt++;
        int formed=0, max=0;
        int[] ans = new int[]{-1,0,0};
        for(int i=0,j=0;j<s.length();j++){
            char c = s.charAt(j);
            smap[c-'A']++;
            if(tmap[c-'A']!=0 && tmap[c-'A']==smap[c-'A']) formed++;
            while(formed==uniqt){
                if(ans[0]==-1||j-i+1<ans[0]){
                    ans[0] = j-i+1;
                    ans[1] = i;
                    ans[2] = j;
                }
                char d = s.charAt(i++);
                if(smap[d-'A']!=0)smap[d-'A']--;
                if(tmap[d-'A']!=0&&smap[d-'A']<tmap[d-'A']) formed--;
            }
        }
        return ans[0]==-1 ? "": s.substring(ans[1],ans[2]+1);
    }
    
    //
    public String minWindow4(String s, String t) {
        int[] smap = new int[64],tmap = new int[64];
        for(int i=0;i<t.length();i++) tmap[t.charAt(i)-'A']++;
        int[][] filtered = new int[s.length()][2];
        int uniqt = 0, x=0;;
        for(int i=0;i<64;i++) if(tmap[i]!=0) uniqt++;
        for(int i=0;i<s.length();i++)
            if(tmap[s.charAt(i)-'A']!=0)
                filtered[x++]=new int[]{s.charAt(i), i};
        int formed=0, max=0;
        int[] ans = new int[]{-1,0,0};
        for(int i=0,j=0;j<x;j++){
            char c = (char)filtered[j][0];
            smap[c-'A']++;
            if(tmap[c-'A']==smap[c-'A']) formed++;
            while(formed==uniqt){
                int l = filtered[i][1];
                int r = filtered[j][1];
                int w = r-l+1;
                if(ans[0]==-1||w<ans[0]){
                    ans[0] = w;
                    ans[1] = l;
                    ans[2] = r;
                }
                char d = (char)filtered[i++][0];
                smap[d-'A']--;
                if(smap[d-'A']<tmap[d-'A']) formed--;
            }
        }
        return ans[0]==-1 ? "": s.substring(ans[1],ans[2]+1);
    }
}
