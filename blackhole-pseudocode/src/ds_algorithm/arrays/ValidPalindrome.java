
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */


// https://leetcode.com/problems/valid-palindrome/

public class ValidPalindrome {
    
    //APPROACH 1 skip if letter or digit, then check if chars are same 
    public boolean isPalindrome(String s) {
        for(int i=0,j=s.length()-1;i<j;){
            char l = s.charAt(i);
            char r = s.charAt(j);
            if(!(Character.isLetter(l)||Character.isDigit(l))) {
                i++; continue;
            }
            if(!(Character.isLetter(r)||Character.isDigit(r))){
                j--; continue;
            }
            if(Character.toLowerCase(l)!=Character.toLowerCase(r))
                return false;
            i++;j--;
        }
        return true;
    }
    public static boolean isPalindrome2(String s){
        int i=0,j=s.length()-1;
        while(i<j){
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if(!(Character.isLetter(c1) || Character.isDigit(c1))){
                i++;
            }else if(!(Character.isLetter(c2) || Character.isDigit(c2))){
                j--;
            }else if(Character.toLowerCase(c1)!=Character.toLowerCase(c2))
                return false;
            else{
                i++;j--;
            }
        }
        return true;
    }
    // same as above, just changed or to and
    public boolean isPalindrome3(String s) {
        for(int i=0,j=s.length()-1;i<j;){
            char l = s.charAt(i);
            char r = s.charAt(j);
            if(!Character.isLetter(l)&&!Character.isDigit(l)) i++;
            else if(!Character.isLetter(r)&&!Character.isDigit(r)) j--;
            else if(Character.toLowerCase(l)!=Character.toLowerCase(r)) return false;
            else{
                i++;j--;
            }
        }
        return true;
    }
  
  public static void main(String args[]){
    System.out.println('9' + 0);
  }
  
  //APPROACH 1 use int[] map for letter and digits, skip if letter or digit, then check if chars are same 
  // this actually consumes more space than required by prev solution but its O(1)
    public boolean isPalindrome4(String s) {
        int cmap[] = new int[256];
        for(int i=0;i<10;i++) cmap['0'+i] = 1;
        for(int i=0;i<26;i++){
            cmap['a'+i] = 1;
            cmap['A'+i] = 1;
        }
        for(int i=0,j=s.length()-1;i<j;){
            if(cmap[s.charAt(i)]==0) i++;
            else if(cmap[s.charAt(j)]==0) j--;
            else if(Character.toLowerCase(s.charAt(i++))!=Character.toLowerCase(s.charAt(j--))) 
                return false;
        }
        return true;
    }
}
