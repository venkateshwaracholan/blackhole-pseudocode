/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.strings;
import java.util.*;

/**
 *
 * @author venkateshwarans
 */
public class IsomorphicStrings {
    public static boolean show = false;
    public static boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        return check(s,t) && check(t,s);
    }

    public static boolean check(String s,String t){
        Map<Character,Character> m = new HashMap();
        for(int i=0;i<s.length();i++){
            Character s1 = s.charAt(i);
            Character t1 = t.charAt(i);
            if(m.containsKey(s1)){
                if(t1 != m.get(s1))
                    return false;
            }else{
                m.put(s1, t1);   
            }

        }
        return true;
    }
    
    public static void main(String[] args){
      test(isIsomorphic("egg","add"), true);
      test(isIsomorphic("foo","bar"), false);
      test(isIsomorphic("paper","title"), true);
      test(isIsomorphic("aa","ab"), false);
      test(isIsomorphic("ab","aa"), false);
      test(isIsomorphic("aa","aa"), true);
      test(isIsomorphic("aa","aaa"), false);

    }
    
    public static void test(boolean got, boolean exp){
    System.out.println(got == exp);
    if(show || !(got == exp)){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

