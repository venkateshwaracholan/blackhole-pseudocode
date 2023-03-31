/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */
public class Incoming {
  public static void main(String args[]){
    System.out.println("otha");
  }
}

/*

 * Create a “Find” API, that supports finding the following files:
 * Files that have a given size requirement.
 * Files with a certain naming pattern.
 *
 * Let’s focus on 2 uses cases at first:
 * Find all files over 5 MB somewhere under a directory.
 * Find all XML files somewhere under a directory. 
 * 
 * find /home -type XML -size greater 5mb

 inputs - type, directory, value
 
 class File{
     String name;
     int size;
     String ext;
 }
 
public boolean filter(File file,String input){
    // parse
    // 0: <, 1: >, 2: ==, 3: <=, 4: >=
    int size,rel,pattern = parse(input);
    
    boolean ans = true;
    if(size!=null)
    ans = ans && filetBySize(file,size, rel);
    if(pattern!=null)
    ans = ans && filetByExtn(file,size, rel);
    return ans;
}
 
public boolean fileBySize(File file, Integer size, int rel){
    switch(rel){
        case 0: return file.size<size; break;
        case 1: return file.size>size; break;
        case 2: return file.size=size; break;
        case 3: return file.size<=size; break;
        default: return file.size>=size; break;
    }
}

// abcd.xml       
public boolean filetByExtn(File file, String extn){
    return file.extn.equals(exten);
}
 
 
 public List<File> getFiles(String directory){
     // system impl
     // return list of files under directory
 }
 
 public List<File> find(int size, String directory,String input){
     List<File> results = new ArrayList();
     if(!validateDirectoy())
        return results;
     List<File> files = getFiles(directory);
     for(File f: files){
         if(filter(file, input))
            results.add(file);
     }
     return results;
 }
 
 public boolean validateDirectoy(){
     // system impl
 } 
 
 

Dependency Loading

You are writing a program that depends on a package and that depends on another 
package and so on. In what order should we load the packages to avoid missing dependency 
error and which packages we can be loaded in parallel?

# graph in form of map of pkg -> list/tuple of dependent pkgs
Sample Input: {A: (B, C, D), C: (B, E, F), D: (F), E: (G) }

# ordered list of list/tuples of pkgs where each sub list/tuple 
# of pkgs can be loaded in parallel 

Valid Outputs: 
[(B,F,G), (E,D), (C), (A)] 
[(F,G), (E, D, B), (C), (A)] 


Invalid: 
[(F,G), (E), (D), (C), (B)  (A)] 

addedSet

A -> B C D
C -> B E F
B -> []
D -> F
E -> G
F -> []
G -> []

0 : bfg
1 : de
2: []
3: ac

B - a,c
c- a 
d - a
e - c
G - e
f - d




E
C
A


Expected output is an ordered list of list of packages were each nested list/tuple 
can be loaded in parallel.


public List<List<Character>> order_packages(Map<Character, Set<Character>> map){
    List<List<Character>> ans  = new ArrayList();
    // assume
    Set<Characters> all_nodes;
    // i will add them
    reverse_map;
    heap;

    
}
 

 
 
 






*/
