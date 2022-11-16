/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

/**
 *
 * @author vshanmugham
 */


/*
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
 

1. We can try iterative and recursive together 
2. we can use, 2 maps to store node to level and level to nodes to solve.


*/

public class PackageLoadingInParallel {
  
}
