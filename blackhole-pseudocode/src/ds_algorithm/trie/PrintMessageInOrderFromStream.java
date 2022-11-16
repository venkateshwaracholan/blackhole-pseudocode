/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.trie;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/discuss/interview-question/314733/Bloomberg-or-Output-data-from-a-stream-in-order

/*

use trie with maps to find it in depth of the trie
use a field in trie node to know if its a termination
also have the order of the message in the termination 
so that we can construct a message in the same order later



M1 - SDACAS
M2 - VSACS
M3 - ACA
M4 - SADA
.
.
Mn
​

Signals - 
ADADASDACASDGDVFDVD
     s    e


[a-z]
b - b
x - xbc,xa

xbc
b
xa


 Map - Tries


 x
b a


czxbmnxbc
   
   s

potentials

every char on stream * O(n)

'A' - [AB,AD]
'D' - [DA,DXX,DY]

'A' -  A
      B D

'D' -  D
     A X Y - MAP O(1)
        X


M10 - DABRADG
M11 - ABRA
M12 - ABRAD
M13 - DABRADGKASG

Set<>

    Stream - 
... KCUVMGHDABRADGKASGZ
           ab




[B,R,A,D,G,K,A,S,G]
 i 


DABRADG
DABRADGKASG
ABRA
ABRAD




     NULL
  A       D       
  B       A 
  R       B
  A       R
-1  D     A
    -1    D
          G
        -1 KASG-1

Map<character, Trie> outerMap;


Trie{
  Character c;
  Map<Character> children;
}



*/


public class PrintMessageInOrderFromStream {
  public static void main(String args[]){
    String  s= "ASSA";
    System.out.println(s.toLowerCase());
            
  }
}
