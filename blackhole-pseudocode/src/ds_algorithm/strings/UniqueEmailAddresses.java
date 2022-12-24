/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.strings;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/unique-email-addresses/description/

public class UniqueEmailAddresses {
    
    //Time O(n*l) space: O(n)
    // n num of emails, l length of email string
    // approach: Iteration and if checks with booleans
    // 2 flags write and local, iterate and continue if its dot and local
    //if + encountered turn off write until @ is encountered
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet();
        for(String email: emails)
            set.add(getKey(email));
        return set.size();
    }

    public String getKey(String email){
        StringBuilder sb = new StringBuilder();
        boolean write = true, local=true;
        for(int i=0;i<email.length();i++){
            char c = email.charAt(i);
            if(c=='.'&& local) continue;
            else if(c=='+') write=false;
            else if(c=='@') {
                write = true;
                local = false;
            }
            if(write) sb.append(c);
        }
        return sb.toString();
    }
    
    
    
    //splitting with @ and processing local separately
    public int numUniqueEmailsAlt(String[] emails) {
        Set<String> set = new HashSet();
        for(String email: emails){
            String[] parts = getParts(email);
            set.add(parse(parts[0])+'@'+parts[1]);
        }
        return set.size();
    }

    public String parse(String local){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<local.length();i++){
            char c = local.charAt(i);
            if(c!='.'){
                if(c=='+') return sb.toString();
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String[] getParts(String email){
        int i=0;
        for(;i<email.length();i++){
           if(email.charAt(i)=='@') break;
        }
        return new String[]{email.substring(0,i), email.substring(i+1)};
    }
    
    
    
    // intuitive approach 
    // asdding email to sb first and delete unwanted chars and blocks of chars
    public int numUniqueEmailsDel(String[] emails) {
        Set<String> set = new HashSet();
        for(String email: emails)
            set.add(parseDel(email));
        return set.size();
    }

    public String parseDel(String email){
        StringBuilder sb = new StringBuilder(email);
        for(int i=0;i<sb.length();i++){
            char c = sb.charAt(i);
            if(c=='.') {sb.deleteCharAt(i);}
            else if(c=='+') sb.delete(i, sb.indexOf("@"));
            if(c=='+'||c=='@') break;
        }
        return sb.toString();
    }
    
    //Java Streams Solution
    public int numUniqueEmailsStreams(String[] emails) {
        return Arrays.stream(emails)
        .map(email->email.split("@"))
        .map(arr->new StringBuilder()
            .append((arr[0].contains("+") ? arr[0].substring(0, arr[0].indexOf("+")) : arr[0]).replace(".",""))
            .append("@").append(arr[1]).toString())
        .collect(Collectors.toSet()).size();
    }
}
