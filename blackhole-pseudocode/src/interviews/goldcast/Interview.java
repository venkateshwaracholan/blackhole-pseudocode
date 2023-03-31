/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author venka
 */

import java.util.*;

public class Interview {
    
    // abcde
    // 
    // 
    public static boolean isPalin(String str){
        if(str==null)
            return false;
        int left = 0, right = str.length()-1;
        while(left<=right){
            if(str.charAt(left++)!=str.charAt(right--))
                return false;
        }
        return true;
    }
    
    // "()[]{}"
    //    x
    // stack ({
    // 
    public static boolean isBalanced(String str){
        if(str==null)
            return false;
        Stack<Character> stack = new Stack(); 
        Map<Character,Character> map = new HashMap();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c))
                stack.add(c);
            else{
                if(stack.isEmpty())
                    return false;
                else if(c!= map.get(stack.pop()))
                    return false;
            }
        }
//        System.out.println(stack.size());
        return stack.isEmpty();
        
        
    }
    
    public static void main(String[] args){
        
//        System.out.println(isPalin("abcba"));
//        System.out.println(isPalin("abcde"));
//        System.out.println(isPalin(""));
//        System.out.println(isPalin(null));
        
//        System.out.println(isBalanced(null));
//        System.out.println(isBalanced(""));

        System.out.println("case1" + isBalanced("(){}[]"));
        System.out.println(isBalanced("({[]})"));
        System.out.println(isBalanced("({)[]}"));
        System.out.println(isBalanced(")(][}{"));
        System.out.println(isBalanced("("));
        System.out.println(isBalanced(")"));
        System.out.println(isBalanced(""));
        
    }
}

/*




*/

